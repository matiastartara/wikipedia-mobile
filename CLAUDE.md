# CLAUDE.md

Guidance for Claude Code when working on this repository: a Java/TestNG/Appium
mobile automation suite for the **Wikipedia Android app**, built with the
Page Object Model (POM).

## Stack

- Java 17, Maven
- Appium Java Client 8.6.0 + Selenium 4.15.0 (`UiAutomator2` driver, Android only — no iOS driver is wired up despite the iOS properties in `config.properties`)
- TestNG 7.8.0 + ExtentReports 5.1.1
- App under test: `org.wikipedia` (Android), package `org.wikipedia`, launch activity `org.wikipedia.main.MainActivity`

## Architecture

```
src/main/java/com/automation/
  pages/    Page Objects (POM). Each page extends BasePage.
  utils/    DriverUtils (creates the AppiumDriver from config.properties), WaitUtils
  report/   ExtentReport (BeforeSuite/AfterSuite hooks for the HTML report)
src/test/java/
  test/     TestNG test classes, all extend BaseTest
  suite/    One TestNG suite XML per test class (see below)
src/main/resources/
  config.properties   local run config (device name, app path, appium host/port)
  *.apk               NOT committed — see "APK handling" below
```

### Page Object conventions

- Every page class extends `BasePage` (`src/main/java/com/automation/pages/BasePage.java`), which gives you `click()`, `type()`, `getText()`, `swipe()`, `longPress()`, `scrollTo()`, `isElementPresent()`, plus `getDriver()`/`getWait()` (10s `WebDriverWait`).
- Locate elements with `@AndroidFindBy` and `PageFactory` (`AppiumFieldDecorator`), never raw `driver.findElement` inside a test.
- A page method that navigates to a new screen **returns the new page object**; a method that stays on the same screen **returns `this`** (see `HomePage.clickOnNext()` vs `HomePage.openSearch()` / `HomePage.openMore()`).
- Locator priority, strongest to weakest: `resource-id` (`id = "org.wikipedia:id/..."`) > `accessibility id` / `content-desc` > `-android uiautomator` (`UiSelector`) > `xpath`. Only reach for xpath when nothing stable is available (e.g. `Article.title`, which has no resource-id).
- Tests never hardcode a screen's popup/onboarding handling inline — that logic belongs in the page object (see `SearchPage.closePopupIfPresent()`).

### Test conventions

- Test classes extend `BaseTest` (`src/test/java/test/BaseTest.java`), which creates the driver in `@BeforeMethod` and quits it in `@AfterMethod`, logging pass/fail/skip to ExtentReports.
- Every test starts from a cold app launch. `no.reset=false` in `config.properties` means Appium **resets app data on every session**, so the onboarding screens always appear first — every test that starts from `HomePage` must call `home.clickOnNext()` before anything else.
- Every test class needs its own suite XML under `src/test/java/suite/` mirroring the existing ones (see `searchTest.xml`, `navigationTest.xml`), so it can be run in isolation:
  ```bash
  mvn test -DsuiteXmlFile=src/test/java/suite/<name>.xml
  ```
  Note: `pom.xml` does not wire `suiteXmlFiles` into `maven-surefire-plugin`, so a plain `mvn test` auto-discovers and runs **every** `*Test` class regardless of which suite XML you pass. If you need `-DsuiteXmlFile` to actually restrict execution, add a `suiteXmlFiles` block to the surefire plugin config first.

## Running against a real device/emulator

1. Boot an emulator or connect a device (`adb devices` must show it).
2. Start a real Appium server (`appium`, default `http://127.0.0.1:4723`) — `DriverUtils` connects to whatever `run.ip`/`run.port` say in `config.properties`, it does **not** use the MCP's embedded driver.
3. Put the exact APK version referenced in `config.properties` at `src/main/resources/org.wikipedia_<version>.apk` (see README for the official download link).
4. `mvn test -DsuiteXmlFile=src/test/java/suite/<name>.xml`

## Using the Appium MCP to write/extend tests

This repo is set up to be driven by an **Appium MCP server** for interactive
inspection while writing Page Objects — see README.md for install/setup.
When adding or changing a Page Object:

1. **Never guess a locator.** Create a session with the MCP (`select_device` → `appium_session_management` with `action=create`), drive the app to the target screen, and call `appium_get_page_source` to read the *real* `resource-id`/`content-desc`/text values before writing any `@AndroidFindBy`.
2. Page source dumps are large; when they exceed the tool's inline limit they're saved to a file — grep/`jq` the saved file for the attributes you need (`resource-id=`, `content-desc=`, `text=`) instead of reading the whole thing.
3. Prefer `appium_find_element` with `strategy: id` or `accessibility id` to confirm a locator resolves to exactly one element before committing it to a Page Object.
4. Use `appium_gesture` (`tap`, `scroll_to_element`, etc.) to walk through the flow manually and confirm each step before writing the corresponding Java method.
5. Always end the exploration session (`appium_session_management` `action=delete`) once locators are captured — don't leave sessions dangling.
6. After writing the Page Object + test, run it for real with Maven (see above) against the same emulator, not just through the MCP — the MCP session and the Maven/TestNG run use two different driver instances.

### Known screens/flows already mapped (safe to reuse locators from)

- Onboarding (4 screens): `android.widget.Button` (no resource-id), one tap per screen; last screen tap needs `UiSelector().className("android.widget.Button").instance(0)`.
- Home bottom nav: `nav_tab_home`, `nav_tab_reading_lists` ("Saved"), `nav_tab_search`, `nav_tab_edits` ("Activity"), `nav_tab_more`.
- More menu (bottom sheet): `main_drawer_account_container` (Log in / join Wikipedia — the visible text lives on a non-clickable child `TextView`, tap the container instead), `main_drawer_games_hub_container`, `main_drawer_places_container`, `main_drawer_settings_container`, `main_drawer_donate_container`.
- Create Account screen: root `create_account_primary_container`, fields `create_account_username`, `create_account_password_input`, `create_account_password_repeat`, `create_account_email`, submit `create_account_submit_button`, `create_account_login_button` ("Already have an account?").
- Search: `search_card` → `search_src_text`; results are `TextView[@text='<query>']`; `SearchPage.closePopupIfPresent()` handles an occasional first-run popup via raw tap coordinates (fragile — replace with a real locator if it starts flaking).

## Security / data hygiene

- Never hardcode real Wikipedia account credentials in a test or page object. Login-flow tests should stop at verifying the Create Account / Login screen renders (see `NavigationTest`) unless a disposable test account + secret manager is explicitly set up.
- `config.properties` and `appium-mcp.capabilities.json` hold local machine config (device name, local paths) — keep them free of anything beyond that; they are (or should be) safe to publish, but don't add tokens/URLs pointing at non-public infrastructure.
- `.appium-mcp/screenshots/` is gitignored — screenshots taken during MCP exploration sessions can capture more of the device than intended; don't force-add them.

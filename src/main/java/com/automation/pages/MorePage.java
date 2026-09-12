package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MorePage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/main_drawer_account_container")
    private WebElement loginJoinWikipediaOption;

    public MorePage(AppiumDriver driver) {
        super(driver);
    }

    public CreateAccountPage openLoginJoinWikipedia() {
        click(loginJoinWikipediaOption);
        return new CreateAccountPage(getDriver());
    }
}

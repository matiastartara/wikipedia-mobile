package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class BasePage {

    private AppiumDriver driver;
    private WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected AppiumDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        return wait;
    }

    protected void click(String text) {
        By locator = androidUIAutomator("new UiSelector().text(\"" + text + "\")");
        getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void click(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected String getText(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected void type(WebElement element, String text) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    //Horizontal swipe using PointerInput/Sequence
    protected void swipe(WebElement fromElement, WebElement toElement) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        int startX = fromElement.getLocation().getX() + (fromElement.getSize().getWidth() / 2);
        int startY = fromElement.getLocation().getY() + (fromElement.getSize().getHeight() / 2);
        int endX = toElement.getLocation().getX() + (toElement.getSize().getWidth() / 2);
        int endY = toElement.getLocation().getY() + (toElement.getSize().getHeight() / 2);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    protected void scrollTo(String text) {
        driver.findElement(androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));
    }

    public void longPress(WebElement element) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // hold in place by moving to same coordinates over duration
        longPress.addAction(finger.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), centerX, centerY));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(longPress));
    }

    public void selectOptionSpinner(WebElement spinner, String optionTextSpinner) {
        spinner.click();
        driver.findElement(By.name(optionTextSpinner)).click();
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

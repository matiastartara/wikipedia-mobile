package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.appium.java_client.MobileBy.AndroidUIAutomator;

public class BasePage {

    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public BasePage(AppiumDriver<MobileElement> driver){
        this.driver=driver;
        wait = new WebDriverWait(driver,10);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    protected AppiumDriver<MobileElement> getDriver(){
        return driver;
    }

    protected WebDriverWait getWait(){
        return wait;
    }

    protected void click(String text){
        By locator = AndroidUIAutomator("text(\"" + text + "\")");
        getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void click(MobileElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected String getText(MobileElement element){
        return getWait().until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected void type(MobileElement element, String text){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    //Horizontal scroll
    protected void swipe(WebElement fromElement,WebElement toElement){
        TouchAction tAction = new TouchAction(getDriver());
        tAction.press(PointOption.point(fromElement.getLocation())).waitAction().moveTo(PointOption.point(toElement.getLocation())).release().perform();
    }

    protected void scrollTo(String text){
        ((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))"));
    }
}

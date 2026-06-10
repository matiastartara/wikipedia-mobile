package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wikipedia:id/navigation_bar_item_icon_container\").instance(2)")
    private WebElement searchButton;

    @AndroidFindBy(className = "android.widget.Button")
    private WebElement nextBtn;

    @AndroidFindBy(className = "android.widget.Button")
    private WebElement nextDataBtn;

    @AndroidFindBy(className = "android.widget.Button")
    private WebElement nextLanguageBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(0)")
    private WebElement nextTopicsBtn;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public SearchPage openSearch(){
        getWait().until(ExpectedConditions.visibilityOf(searchButton)).click();
        return new SearchPage(getDriver());
    }

    public HomePage clickOnNext(){
        getWait().until(ExpectedConditions.visibilityOf(nextBtn)).click();
        getWait().until(ExpectedConditions.visibilityOf(nextDataBtn)).click();
        getWait().until(ExpectedConditions.visibilityOf(nextLanguageBtn)).click();
        getWait().until(ExpectedConditions.visibilityOf(nextTopicsBtn)).click();
        return this;
    }

}

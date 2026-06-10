package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Article extends BasePage {

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='pcs']/android.view.View[1]/android.widget.TextView")
    private WebElement title;

    public Article(AppiumDriver driver) {
        super(driver);
    }

    public String getTitle(){
       return getWait().until(ExpectedConditions.visibilityOf(title)).getText();
    }
}

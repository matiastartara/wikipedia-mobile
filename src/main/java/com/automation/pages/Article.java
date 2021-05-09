package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Article extends BasePage {

    @AndroidFindBy(id="org.wikipedia:id/view_page_title_text")
    private AndroidElement title;

    public Article(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public String getTitle(){
       return getWait().until(ExpectedConditions.visibilityOf(title)).getText();
    }
}

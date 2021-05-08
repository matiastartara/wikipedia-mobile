package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @AndroidFindBy(id="org.wikipedia:id/search_container")
    private AndroidElement searchContainer;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SearchPage openSearch(){
        getWait().until(ExpectedConditions.visibilityOf(searchContainer)).click();
        return new SearchPage(getDriver());
    }

}

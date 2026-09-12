package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateAccountPage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/create_account_primary_container")
    private WebElement primaryContainer;

    public CreateAccountPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return getWait().until(ExpectedConditions.visibilityOf(primaryContainer)).isDisplayed();
    }
}

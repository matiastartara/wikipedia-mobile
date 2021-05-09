package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountContainerPage extends BasePage {

    @FindBy(id="org.wikipedia:id/explore_overflow_account_container")
    private AndroidElement accountContainer;

    @FindBy(id="org.wikipedia:id/explore_overflow_configure_cards")
    private AndroidElement customizeFeed;

    @FindBy(id="org.wikipedia:id/explore_overflow_settings")
    private AndroidElement settings;

    @FindBy(id="org.wikipedia:id/explore_overflow_card_container")
    private AndroidElement cardContainer;

    public AccountContainerPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean isAccountContainerDisplayed(){
        return accountContainer.isDisplayed();
    }

    public void waitForLoad(){
        getWait().until(ExpectedConditions.visibilityOf(cardContainer));
    }

    public void clickOnCustomizeFeed(){
        click(customizeFeed);
    }

    public void clickOnSettings(){
        click(settings);
    }
}

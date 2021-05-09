package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SettingsPage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Wikipedia languages\")")
    private AndroidElement languages;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"App theme\")")
    private MobileElement appTheme;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    private AndroidElement title;

    @AndroidFindBy(id="android:id/title")
    private List<AndroidElement> subTitles;

    public SettingsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SettingsPage clickOnLanguages(){
        getWait().until(ExpectedConditions.elementToBeClickable(languages)).click();
        return this;
    }

    public SettingsPage clickOnAppTheme(){
        getWait().until(ExpectedConditions.elementToBeClickable(appTheme));
        appTheme.click();
        return this;
    }

    public String getTitle(){
        try {
            return getWait().until(ExpectedConditions.visibilityOf(title)).getText();
        }
        catch(Exception e){
            title = (AndroidElement) getDriver().findElement(
                    MobileBy.xpath("//android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView"));
            return title.getText();
        }
    }

    public boolean containSubTitle(String subtitle){
        return subTitles.stream().anyMatch(x->x.getText().equals(subtitle));
    }

}

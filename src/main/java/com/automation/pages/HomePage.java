package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @AndroidFindBy(id="org.wikipedia:id/search_container")
    private AndroidElement searchContainer;

    @AndroidFindBy(id="org.wikipedia:id/voice_search_button")
    private AndroidElement voiceSearchBtn;

    @AndroidFindBy(id="menu_overflow_button")
    private AndroidElement menuOverflow;

    @AndroidFindBy(id="org.wikipedia:id/day_header_text")
    private AndroidElement dayText;

    @AndroidFindBy(id="org.wikipedia:id/fragment_main_nav_tab_layout")
    private AndroidElement navigationBar;

    @AndroidFindBy(id="org.wikipedia:id/view_announcement_header_image")
    private AndroidElement announcementHeaderImage;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SearchPage openSearch(){
        getWait().until(ExpectedConditions.visibilityOf(searchContainer)).click();
        return new SearchPage(getDriver());
    }

    public boolean isVoiceSearchBtnDisplayed(){
        return voiceSearchBtn.isDisplayed();
    }

    public boolean isNavigationBarDisplayed(){
        return navigationBar.isDisplayed();
    }

    public boolean isMenuOverflowDisplayed(){
        return menuOverflow.isDisplayed();
    }

    public boolean isAnnouncementImageDisplayed(){
        return announcementHeaderImage.isDisplayed();
    }

    public void openMenu(){
        click(menuOverflow);
    }

    public String getDayHeaderText(){
        return getWait().until(ExpectedConditions.visibilityOf(dayText)).getText();
    }

}

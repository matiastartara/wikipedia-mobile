package test;

import com.automation.pages.AccountContainerPage;
import com.automation.pages.HomePage;
import com.automation.pages.SettingsPage;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeTest extends BaseTest {

    @Test
    public void HomeElementsTest(){
        test = extent.createTest("Home Test", "Verify home elements test");
        test.log(Status.INFO, "Opening app");
        HomePage home = new  HomePage((AppiumDriver<MobileElement>) getDriver());
        test.log(Status.INFO, "Checking elements displayed");
        Assert.assertTrue(home.isNavigationBarDisplayed(),"Navigation bar is not displayed");
        Assert.assertTrue(home.isVoiceSearchBtnDisplayed(),"Voice search button is not displayed");
        Assert.assertTrue(home.isMenuOverflowDisplayed(),"Menu Overflow is not displayed");

        if (home.isAnnouncementImagePresent())
            home.closeAnnouncementImage();

        String day = new SimpleDateFormat("EEEE").format(new Date());
        String month = new SimpleDateFormat("MMMM").format(new Date());
        String day_number = new SimpleDateFormat("d").format(new Date());
        String dayFormat_expected=day+", "+month.substring(0,3)+" "+day_number;
        Assert.assertEquals(home.getDayHeaderText(),dayFormat_expected);
    }

    @Test
    public void SettingsTest(){
        test = extent.createTest("Settings Test", "Verify settings options");
        test.log(Status.INFO, "Opening app");
        HomePage home = new  HomePage((AppiumDriver<MobileElement>) getDriver());
        test.log(Status.INFO, "Opening Menu");
        home.openMenu();
        AccountContainerPage container = new AccountContainerPage((AppiumDriver<MobileElement>) getDriver());
        container.waitForLoad();
        Assert.assertTrue(container.isAccountContainerDisplayed(),"Account login container not displayed");
        test.log(Status.INFO, "Click on settings");
        container.clickOnSettings();
        SettingsPage settings = new SettingsPage((AppiumDriver<MobileElement>) getDriver());
        Assert.assertEquals(settings.getTitle(),"Settings","The title is not Settings");

        Assert.assertTrue(settings.containSubTitle("Wikipedia languages"));
        Assert.assertTrue(settings.containSubTitle("App theme"));
        Assert.assertTrue(settings.containSubTitle("Enable reading list syncing"));
    }
}

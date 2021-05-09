package test;

import com.automation.pages.HomePage;
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
        Assert.assertTrue(home.isAnnouncementImageDisplayed(),"Announcement Image is not displayed");

        String day = new SimpleDateFormat("EEEE").format(new Date());
        String month = new SimpleDateFormat("MMMM").format(new Date());
        String day_number = new SimpleDateFormat("d").format(new Date());
        String dayFormat_expected=day+", "+month+" "+day_number;
        Assert.assertEquals(home.getDayHeaderText(),dayFormat_expected);
    }
}

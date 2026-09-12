package test;

import com.automation.pages.CreateAccountPage;
import com.automation.pages.HomePage;
import com.automation.pages.MorePage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void navigateToLoginTest() {
        test = extent.createTest("Navigation To Login Test", "Navigating from Home to the Create Account page");

        test.log(Status.INFO, "Opening app");
        HomePage home = new HomePage(getDriver());

        test.log(Status.INFO, "Completing onboarding");
        home.clickOnNext();

        test.log(Status.INFO, "Opening More menu");
        MorePage more = home.openMore();

        test.log(Status.INFO, "Tapping Log in / Join Wikipedia");
        CreateAccountPage createAccountPage = more.openLoginJoinWikipedia();

        test.log(Status.INFO, "Checking Create Account page is displayed");
        Assert.assertTrue(createAccountPage.isDisplayed(), "Create Account page was not displayed");
    }
}

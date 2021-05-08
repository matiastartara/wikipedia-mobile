package test;

import com.automation.pages.HomePage;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void SearchTest(){

        test = extent.createTest("Search Article Test", "Searching article");
        test.log(Status.INFO, "Opening app");
        HomePage home = new  HomePage((AppiumDriver<MobileElement>) getDriver());
        home.openSearch()
            .searchText("Roma")
            .OpenItem();

    }

}

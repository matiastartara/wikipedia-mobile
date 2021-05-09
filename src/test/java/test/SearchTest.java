package test;

import com.automation.pages.Article;
import com.automation.pages.HomePage;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void SearchArticleTest(){
        test = extent.createTest("Search Article Test", "Searching article");
        test.log(Status.INFO, "Opening app");
        HomePage home = new  HomePage((AppiumDriver<MobileElement>) getDriver());
        test.log(Status.INFO, "Searching article");
        home.openSearch()
            .searchText("Roma")
            .OpenItem();


        Article article = new Article((AppiumDriver<MobileElement>) getDriver());
        test.log(Status.INFO, "Checking article");
        Assert.assertEquals("Roma",article.getTitle());
    }

}

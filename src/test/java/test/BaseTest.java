package test;

import com.automation.report.ExtentReport;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.utils.DriverUtils;

public class BaseTest extends ExtentReport {

    private AppiumDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = DriverUtils.returnDriver("android");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(MarkupHelper.createLabel(result.getName() + "Test Case Failed", ExtentColor.RED));
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass(MarkupHelper.createLabel(result.getName() + "Test Case Sucess", ExtentColor.GREEN));
        } else {
            test.skip(MarkupHelper.createLabel(result.getName() + "Test Case Sucess", ExtentColor.YELLOW));
            test.skip(result.getThrowable());
        }

        driver.quit();
    }

    protected AppiumDriver<?> getDriver() {
        return driver;
    }
}

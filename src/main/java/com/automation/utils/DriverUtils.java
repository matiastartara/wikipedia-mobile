package com.automation.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class DriverUtils {

    public static String readProperty(String property){
        Properties prop;
        String value = null;
        try {
            prop = new Properties();
            prop.load(new FileInputStream(new File("src/main/resources/config.properties").getAbsolutePath()));

            value = prop.getProperty(property);

            if (value == null || value.isEmpty()) {
                throw new Exception("Value not set or empty");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public static AppiumDriver<?> returnDriver(String platform) throws Exception {
        AppiumDriver<?> driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (Boolean.parseBoolean(DriverUtils.readProperty("run.hybrid"))) {
            capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
        }

        String completeURL = "http://" + DriverUtils.readProperty("run.ip") + ":" + DriverUtils.readProperty("run.port") + "/wd/hub";

        switch (platform.toLowerCase()){

            case "ios":
                capabilities.setCapability(MobileCapabilityType.APP, new File(DriverUtils.readProperty("app.ios.path")).getAbsolutePath());
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DriverUtils.readProperty("device.ios.name"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, DriverUtils.readProperty("platform.ios.version"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.IOS);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(MobileCapabilityType.NO_RESET, "True");
                capabilities.setCapability("udid", "UDID of your test device");
                driver = new IOSDriver<RemoteWebElement>(new URL(completeURL), capabilities);
                break;

            case "android":
                capabilities.setCapability(MobileCapabilityType.APP, new File(DriverUtils.readProperty("app.android.path")).getAbsolutePath());
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DriverUtils.readProperty("device.android.name"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
                driver = new AndroidDriver<RemoteWebElement>(new URL(completeURL), capabilities);
                break;
            default:
                throw new Exception("Platform not supported");
        }
        return driver;
    }
}

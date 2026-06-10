package com.automation.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class DriverUtils {

    public static String readProperty(String property) {
        Properties prop;
        String value = null;
        try {
            prop = new Properties();
            prop.load(new FileInputStream(new File("src/main/resources/config.properties").getAbsolutePath()));

            value = prop.getProperty(property);

            // If property is not present, return null (caller may decide default behavior)
            if (value == null || value.isEmpty()) {
                // don't throw here; return null to allow optional properties
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public static AppiumDriver returnDriver(String platform) throws Exception {

        AppiumDriver driver;
        String completeURL = "http://" + DriverUtils.readProperty("run.ip") + ":" + DriverUtils.readProperty("run.port");

        switch (platform.toLowerCase()){

            case "ios":
                XCUITestOptions iosOptions = new XCUITestOptions()
                        .setApp(new File(DriverUtils.readProperty("app.ios.path")).getAbsolutePath())
                        .setDeviceName(DriverUtils.readProperty("device.ios.name"))
                        .setPlatformVersion(DriverUtils.readProperty("platform.ios.version"))
                        .setAutomationName(AutomationName.IOS_XCUI_TEST)
                        .setNoReset(Boolean.parseBoolean(DriverUtils.readProperty("no.reset")));
                // udid if required
                String udid = DriverUtils.readProperty("device.ios.udid");
                if (udid != null && !udid.isEmpty()) iosOptions.setUdid(udid);
                // hybrid apps: set autoWebview if property set
                if (Boolean.parseBoolean(DriverUtils.readProperty("run.hybrid"))) {
                    iosOptions.setCapability("autoWebview", true);
                }
                driver = new IOSDriver(new URL(completeURL), iosOptions);
                break;

            case "android":
                UiAutomator2Options androidOptions = new UiAutomator2Options()
                        .setApp(new File(DriverUtils.readProperty("app.android.path")).getAbsolutePath())
                        .setDeviceName(DriverUtils.readProperty("device.android.name"))
                        .setAppPackage(DriverUtils.readProperty("app.android.package"))
                        .setAppActivity(DriverUtils.readProperty("app.android.activity"))
                        .setNoReset(Boolean.parseBoolean(DriverUtils.readProperty("no.reset")))
                        .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                        // Allow any activity under the app package to be considered started (onboarding flows)
                        .setAppWaitPackage(DriverUtils.readProperty("app.android.package"))
                        .setAppWaitActivity("*")
                        // auto grant runtime permissions to avoid dialogs blocking startup
                        .setAutoGrantPermissions(true);
                if (Boolean.parseBoolean(DriverUtils.readProperty("run.hybrid"))) {
                    androidOptions.setCapability("autoWebview", true);
                }
                driver = new AndroidDriver(new URL(completeURL), androidOptions);
                break;
            default:
                throw new Exception("Platform not supported");
        }
        return driver;
    }
}

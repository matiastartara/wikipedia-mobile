package com.automation.utils;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitUtils {

    private static WebDriverWait wait;
    private static int maxWait=20;

    public static boolean waitForNotEmptyList(WebDriver driver, List<MobileElement> elements) {
        return new WebDriverWait(driver, maxWait).until((ExpectedCondition<Boolean>) driver1 -> {
            int size = elements.size();
            return size > 0;
        });
    }

    public static boolean waitToContainElement(WebDriver driver, final List<MobileElement> elements, final String optionText) {
        return new WebDriverWait(driver, maxWait).until((ExpectedCondition<Boolean>)
                driver1 -> elements.stream().anyMatch(x->x.getText().equals(optionText)));
    }

}

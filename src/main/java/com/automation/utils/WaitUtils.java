package com.automation.utils;

import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitUtils {

    private static int maxWait=20;

    public static boolean waitForNotEmptyList(WebDriver driver, List<WebElement> elements) {
        return new WebDriverWait(driver, Duration.ofSeconds(maxWait)).until((ExpectedCondition<Boolean>)
                d -> elements.size() >0);
    }

    public static boolean waitToContainElement(WebDriver driver, final List<WebElement> elements, final String optionText) {
        return new WebDriverWait(driver, Duration.ofSeconds(maxWait)).until((ExpectedCondition<Boolean>)
                d -> elements.stream().anyMatch(x->x.getText().equals(optionText)));
    }

}

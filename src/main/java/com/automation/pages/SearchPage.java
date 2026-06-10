package com.automation.pages;

import com.automation.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public class SearchPage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/search_card")
    private WebElement searchCard;

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchInput;

    private static final String SEARCH_RESULT_XPATH = "//android.widget.TextView[@text='%s']";

    private String text;

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    public SearchPage searchText(String text) {
        this.text = text;
        searchCard.click();
        type(searchInput, text);
        return this;
    }

    public void openItem() {
        By locator = AppiumBy.xpath(String.format(SEARCH_RESULT_XPATH, text));
        List<WebElement> results = getDriver().findElements(locator);
        WaitUtils.waitForNotEmptyList(getDriver(), results);

        if (results.isEmpty()) {
            throw new NoSuchElementException("Item not found: " + text);
        }

        results.get(0).click();
        closePopupIfPresent();

    }

    public SearchPage closePopupIfPresent() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.xpath("//*[@package='org.wikipedia' and @focusable='true']")
                    ));
            System.out.println("[PopupHandler] Popup detected - attempting to close");
            ((JavascriptExecutor) getDriver()).executeScript("mobile: clickGesture", Map.of("x", 1013, "y", 203));
            Thread.sleep(1000);
            ((JavascriptExecutor) getDriver()).executeScript("mobile: clickGesture", Map.of("x", 1019, "y", 207));
            Thread.sleep(1000);
            ((JavascriptExecutor) getDriver()).executeScript("mobile: clickGesture", Map.of("x", 917, "y", 833));
            System.out.println("[PopupHandler] Popup closed successfully");
        } catch (TimeoutException e) {
            System.out.println("[PopupHandler] No popup detected - skipping");
        } catch (Exception e) {
            System.out.println("[PopupHandler] Unexpected error while closing popup: " + e.getMessage());
        }
        return this;
    }

}

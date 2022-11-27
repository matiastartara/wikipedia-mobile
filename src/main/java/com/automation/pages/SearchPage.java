package com.automation.pages;

import com.automation.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;


public class SearchPage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private AndroidElement searchField;

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_title")
    private List<MobileElement> titleList;

    private String text;

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SearchPage searchText(String text) {
        this.text = text;
        type(searchField, text);
        return this;
    }

    public void OpenItem() {
        WaitUtils.waitForNotEmptyList(getDriver(), titleList);
        titleList.stream().filter(x -> x.getText().contains(text)).findFirst().orElse(null).click();
    }
}

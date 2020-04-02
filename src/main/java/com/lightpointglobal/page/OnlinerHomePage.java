package com.lightpointglobal.page;

import com.lightpointglobal.framework.ui.Browser;
import org.openqa.selenium.By;

public class OnlinerHomePage {

    private static final String URL = "https://onliner.by/";
    private By mobilePhonesButton = By.xpath("//a[@href='https://catalog.onliner.by/mobile']");

    public OnlinerHomePage openOnlinerHomePage() {
        Browser.getInstance().get(URL);
        return this;
    }

    public MobilePhonesDiv mobilePhonesButtonClick() {
        Browser.getInstance().click(mobilePhonesButton);
        return new MobilePhonesDiv();
    }
}

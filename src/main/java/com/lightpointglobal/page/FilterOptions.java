package com.lightpointglobal.page;

import com.lightpointglobal.framework.ui.Browser;
import org.openqa.selenium.By;

public abstract class FilterOptions {

    private By appleCheckBox = By.xpath("//span[text()='Apple']/ancestor::li//span[@class='i-checkbox__faux']");
    private By nextAfterAppleCheckBox = By.xpath("//span[text()='Nokia']/ancestor::li//span[@class='i-checkbox__faux']");

    /*
     * Here I had to move to another element,
     * to see one that I need,
     * because JS was loading eternally and covering it,
     * if I move to my element directly (same with manual test).
     */
    public MobilePhonesDiv clickAppleCheckBox() {
        Browser.getInstance().moveTo(nextAfterAppleCheckBox);
        Browser.getInstance().click(appleCheckBox);
        return new MobilePhonesDiv();
    }
}

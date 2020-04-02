package com.lightpointglobal.page;

import com.lightpointglobal.framework.ui.Browser;
import org.openqa.selenium.By;

public class MobilePhonesDiv extends FilterOptions {

    // This locator allows to get price from from first phone
    private By firstPhonePriceXpath = By.xpath("//div[@class='schema-product__group' and contains(.,'Apple')][1]//div[@class='schema-product__price-group']//span[@data-bind=\"html: $root.format.minPrice($data.prices, 'BYN')\"]");

    // And this one prevents previous locator from throwing StaleElementReferenceException
    private By firstPhoneDivXpath = By.xpath("//div[@class='schema-product__group' and contains(.,'Apple')][1]");

    public Integer getFirstPhonePrice() {
        //Browser.getInstance().waitForPresenceOfElementLocated(firstPhoneDivXpath);
        return Integer.valueOf(Browser.getInstance().waitForElementFluently(firstPhonePriceXpath).split(",")[0]);
        //return Integer.valueOf(Browser.getInstance().getText(firstPhonePriceXpath).split(",")[0]);
    }
}

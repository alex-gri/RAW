package com.lightpointglobal.page;

import com.lightpointglobal.framework.ui.Browser;
import org.openqa.selenium.By;

public class MobilePhonesDiv extends FilterOptions {

    // This locator allows to get price from first phone
    private By firstPhonePriceXpath = By.xpath("//div[@class='schema-product__group' and contains(.,'Apple')][1]//div[@class='schema-product__price-group']//span[@data-bind=\"html: $root.format.minPrice($data.prices, 'BYN')\"]");

    public Integer getFirstPhonePrice() {
        return Integer.valueOf(Browser.getInstance().getTextAvoidingStaleness(firstPhonePriceXpath).split(",")[0]);
    }
}

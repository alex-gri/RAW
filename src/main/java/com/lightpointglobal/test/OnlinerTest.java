package com.lightpointglobal.test;

import com.lightpointglobal.framework.logger.Log;
import com.lightpointglobal.framework.service.OnlinerMobileService;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class OnlinerTest extends TestBase {

    @Test (description = "Check if the first shown Apple phone costs more than 100 BYN")
    public void firstApplePhonePriceIsMoreThan_100_BYNTest()
    {
        Integer firstShownIPhonePrice = OnlinerMobileService
                .openMobilePhonesPage()
                .clickAppleCheckBox()
                .getFirstPhonePrice();
        Log.info("The first IPhone costs: " + firstShownIPhonePrice.toString());

        assertThat("The price of first shown Apple phone is less than 100",
                  firstShownIPhonePrice, is(greaterThan(100)));
    }
}

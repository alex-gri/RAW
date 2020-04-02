package com.lightpointglobal.framework.service;

import com.lightpointglobal.page.MobilePhonesDiv;
import com.lightpointglobal.page.OnlinerHomePage;

public class OnlinerMobileService {

    private OnlinerMobileService() {}

    public static MobilePhonesDiv openMobilePhonesPage() {
        return new OnlinerHomePage()
                .openOnlinerHomePage()
                .mobilePhonesButtonClick();
    }
}

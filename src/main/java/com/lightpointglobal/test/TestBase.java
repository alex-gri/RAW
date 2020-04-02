package com.lightpointglobal.test;

import com.lightpointglobal.framework.ui.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void setupBrowser() {
        Browser.getInstance();
    }

    @AfterClass
    public void tearDownBrowser() {
        Browser.getInstance().stopBrowser();
    }
}

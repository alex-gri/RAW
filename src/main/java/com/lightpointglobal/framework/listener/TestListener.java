package com.lightpointglobal.framework.listener;

import com.lightpointglobal.framework.logger.Log;
import com.lightpointglobal.framework.ui.Browser;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("[TEST STARTED] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("[TEST FINISHED] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Browser.getInstance().makeScreenshot();
        Log.info("[TEST FAILED] " + result.getName());
    }
}

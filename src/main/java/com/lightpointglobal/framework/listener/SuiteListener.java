package com.lightpointglobal.framework.listener;

import com.lightpointglobal.framework.logger.Log;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        Log.info("[SUITE STARTED] " + suite.getName());
        Log.info("Parallel: " + suite.getXmlSuite().getParallel());
        Log.info("Thread count: " + suite.getXmlSuite().getThreadCount());
    }

    @Override
    public void onFinish(ISuite suite) {
        Log.info("[SUITE FINISHED] " + suite.getName());
    }
}

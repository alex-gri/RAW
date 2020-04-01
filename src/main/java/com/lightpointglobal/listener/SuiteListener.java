package com.lightpointglobal.listener;

import com.lightpointglobal.logger.Log;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        Log.debug("[SUITE STARTED] " + suite.getName());
        Log.debug("Parallel: " + suite.getXmlSuite().getParallel());
        Log.debug("Thread count: " + suite.getXmlSuite().getThreadCount());
    }

    @Override
    public void onFinish(ISuite suite) {
        Log.debug("[SUITE FINISHED] " + suite.getName());
    }
}

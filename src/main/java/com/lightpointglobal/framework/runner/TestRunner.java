package com.lightpointglobal.framework.runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.lightpointglobal.framework.listener.SuiteListener;
import com.lightpointglobal.framework.listener.TestListener;
import com.lightpointglobal.framework.logger.Log;
import org.apache.commons.lang3.StringUtils;
import org.testng.TestNG;

public class TestRunner {

    public static void main(String[] args) {
        parseCli(args);
        Log.info("Starting app with parameters: " + StringUtils.join(args, ' '));
        createTestNG().run();
        Log.info("App finished");
    }

    private static TestNG createTestNG() {
        TestNG testNG = new TestNG();
        testNG.addListener(new SuiteListener());
        testNG.addListener(new TestListener());
        testNG.setTestSuites(Arguments.instance().getSuites());
        return testNG;
    }

    private static void parseCli(String[] args) {
        Log.info("Parsing CLI parameters using JCommander");
        JCommander jCommander = new JCommander();
        try {
            JCommander.newBuilder()
                    .args(args)
                    .addObject(Arguments.instance())
                    .build()
                    .parse();
        } catch (ParameterException e) {
            Log.error(e.getMessage());
            jCommander.usage();
            System.exit(1);
        }
    }
}

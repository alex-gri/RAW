package com.lightpointglobal.runner;

import com.beust.jcommander.Parameter;
import com.lightpointglobal.runner.cli.BrowserType;
import com.lightpointglobal.runner.cli.BrowserTypeConverter;

public class Arguments {

    private static Arguments instance;

    @Parameter(names = {"--browser", "-b"}, description = "Browser type", required = true,
            converter = BrowserTypeConverter.class)
    private BrowserType browserType;

    public Arguments() {
    }

    public static synchronized Arguments instance() {
        if (instance == null) {
            instance = new Arguments();
        }
        return instance;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }
}

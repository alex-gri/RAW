package com.lightpointglobal.framework.runner;

import com.beust.jcommander.Parameter;
import com.lightpointglobal.framework.runner.cli.BrowserType;
import com.lightpointglobal.framework.runner.cli.BrowserTypeConverter;

import java.util.ArrayList;
import java.util.List;

public class Arguments {

    private static Arguments instance;

    @Parameter(names = {"--suites", "-s"}, description = "The list of TestNG suites")
    private List<String> suites = new ArrayList<>();

    @Parameter(names = {"--browser", "-b"}, description = "Browser type", converter = BrowserTypeConverter.class)
    private BrowserType browserType = BrowserType.CHROME;

    private Arguments() {
    }

    public static synchronized Arguments instance() {
        if (instance == null) {
            instance = new Arguments();
        }
        return instance;
    }

    public List<String> getSuites() {
        if (suites.isEmpty()) {
            suites.add("src/main/resources/testng.xml"); // Set default suite if no parameter provided
        }
        return suites;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }
}

package com.lightpointglobal.framework.runner.cli;

import com.beust.jcommander.IStringConverter;

public class BrowserTypeConverter implements IStringConverter<BrowserType> {

    public BrowserType convert(String s) {
        return BrowserType.valueOf(s.toUpperCase());
    }
}

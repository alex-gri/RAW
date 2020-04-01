package com.lightpointglobal.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Log {

    private static final Logger LOGGER = LogManager.getLogger("com.lightpointglobal");

    private Log() {
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void screenshot(File screenshot) {
        LOGGER.info(screenshot);
    }
}

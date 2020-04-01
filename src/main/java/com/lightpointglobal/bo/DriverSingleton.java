package com.lightpointglobal.bo;

import com.lightpointglobal.runner.Arguments;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() throws NotSupportedBrowserException {
        if (driver == null) {
            switch (Arguments.instance().getBrowserType()) {
                case FIREFOX:
                    driver = setupFirefoxDriver();
                    break;
                case CHROME:
                    driver = setupChromeDriver();
                    break;
                default:
                    throw new NotSupportedBrowserException(
                            "Not supported browser" + Arguments.instance().getBrowserType());
            }
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

    private static WebDriver setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors")
                .addArguments("start-maximized")
                .addArguments("enable-automation")
                .addArguments("--no-sandbox")
                .addArguments("--disable-infobars")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-browser-side-navigation")
                .addArguments("--disable-gpu");
        return new ChromeDriver(options);
    }
}

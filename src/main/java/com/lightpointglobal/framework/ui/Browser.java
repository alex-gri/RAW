package com.lightpointglobal.framework.ui;

import com.lightpointglobal.framework.driver.DriverSingleton;
import com.lightpointglobal.framework.driver.NotSupportedBrowserException;
import com.lightpointglobal.framework.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Browser implements WrapsDriver {

    private static final ThreadLocal<Browser> instance = new ThreadLocal<>();
    private WebDriver wrappedDriver;

    private Browser() {
        try {
            wrappedDriver = DriverSingleton.getDriver();
        } catch (NotSupportedBrowserException e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedDriver;
    }

    public static synchronized Browser getInstance() {
        if (instance.get() == null) {
            instance.set(new Browser());
            Log.debug("New browser is running now!");
        }
        return instance.get();
    }

    public void stopBrowser() {
        try {
            if (getWrappedDriver() != null) {
                DriverSingleton.closeDriver();
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
        } finally {
            instance.remove();
            Log.debug("Browser has stopped!");
        }
    }

    public void get(String url) {
        Log.info("Moving to URL: " + url);
        wrappedDriver.get(url);
    }

    public void waitForDOMToBeCompleted() {
        Log.info("Waiting for page to load...");
        new WebDriverWait(wrappedDriver, Constants.DOM_TIMEOUT).until(webDriver ->
                ((JavascriptExecutor) Objects.requireNonNull(wrappedDriver))
                        .executeScript("return document.readyState")
                        .equals("complete"));
        Log.info("Page loading is completed");
    }

    public void click(By by) {
        waitForDOMToBeCompleted();
        WebElement element = waitForVisibilityOfElementLocated(by);
        Log.info("Click: " + by);
        element.click();
    }

    public void moveTo(By by) {
        waitForDOMToBeCompleted();
        WebElement element = waitForVisibilityOfElementLocated(by);
        Actions actions = new Actions(wrappedDriver);
        Log.info("Moving to: " + by);
        actions.moveToElement(element).perform();
    }

    public String getTextAvoidingStaleness(By by) {
        int count = 0;
        boolean successfull = false;
        String price = "";
        Log.info("Trying to get the text avoiding staleness by: " + by);
        while (count < 30 && !successfull) {
            try {
                WebElement element = wrappedDriver.findElement(by);
                price = element.getText();
                successfull = true;
            } catch (StaleElementReferenceException e) {
                Log.error("Stale reference, trying again... " + e.getMessage());
                count++;
            }
        }
        return price;
    }

    public WebElement waitForVisibilityOfElementLocated(By by) {
        Log.debug("Waiting for visibility of element by: " + by);
        return new WebDriverWait(wrappedDriver, Constants.VISIBILITY_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Is used by TestListener to capture the moment of failure.
    public void makeScreenshot() {
        File screenCapture = new File("logs/screenshots/" + getScreenshotName() + ".png");
        try {
            File screenshotAsFile = ((TakesScreenshot) wrappedDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotAsFile, screenCapture);
            Log.screenshot(screenCapture);
        } catch (IOException e) {
            Log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getScreenshotName() {
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd_HH-mm-ss");
        stringBuilder.append(ZonedDateTime.now().format(formatter))
                .append("-")
                .append(Thread.currentThread().getName())
                .append("-")
                .append(System.currentTimeMillis());
        return stringBuilder.toString();
    }
}

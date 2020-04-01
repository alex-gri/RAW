package com.lightpointglobal.ui;

import com.lightpointglobal.bo.DriverSingleton;
import com.lightpointglobal.bo.NotSupportedBrowserException;
import com.lightpointglobal.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Browser implements WrapsDriver {
    private static final ThreadLocal<Browser> instance = new ThreadLocal<>();

    private WebDriver wrappedDriver;

    private Browser() {
        try {
            this.wrappedDriver = DriverSingleton.getDriver();
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
            Log.debug("NEW BROWSER IS RUNNING NOW!");
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
            instance.set(null);
            Log.debug("BROWSER HAS STOPPED!");
        }
    }

    public void get(String url) {
        Log.info("Moving to URL: " + url);
        wrappedDriver.get(url);
    }

    public void click(By by) {
        WebElement element = waitForVisibilityOfElementLocated(by);
        Log.info("Click: " + by);
        element.click();
    }

    public void clickNoWait(By by) {
        WebElement element = wrappedDriver.findElement(by);
        Log.info("Click without wait: " + by);
        element.click();
    }

    public void waitForAttributeToBe(By by, String attributeName, String value) {
        new WebDriverWait(wrappedDriver, Constants.VALUE_TO_BE_TIMEOUT_SECONDS)
                .until(ExpectedConditions.attributeToBe(by, attributeName, value));
    }

    public String getText(WebElement element) {
        waitForVisibilityOf(element);
        Log.debug("Getting text from: " + element);
        return element.getText();
    }

    public String getText(By by) {
        WebElement element = waitForVisibilityOfElementLocated(by);
        Log.debug("Getting text from: " + by);
        return element.getText();
    }

    public void swtichToFrame(By by) {
        if (by == null) {
            Log.debug("Switching to default content");
            wrappedDriver.switchTo().defaultContent();
        } else {
            Log.debug("Switching to frame: " + by);
            wrappedDriver.switchTo().frame(wrappedDriver.findElement(by));
        }
    }

    public WebElement waitForVisibilityOfElementLocated(By by) {
        Log.debug("Waiting for visibility of element by: " + by);
        return new WebDriverWait(wrappedDriver, Constants.VISIBILITY_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForVisibilityOf(WebElement element) {
        new WebDriverWait(wrappedDriver, Constants.VISIBILITY_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForTextToBe(By by, String textToBe) {
        Log.debug("Waiting for: " + by + " to have text: " + textToBe);
        new WebDriverWait(wrappedDriver, Constants.VALUE_TO_BE_TIMEOUT_SECONDS)
                .until(ExpectedConditions.textToBe(by, textToBe));
    }

    public boolean isDisplayed(By by) {
        Log.info("Is element displayed check: " + by);
        try {
            return waitForVisibilityOfElementLocated(by).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public By xpathBuilder(String partialNameXpath, String nameToCheck) {
        return By.xpath(String.format(partialNameXpath, nameToCheck));
    }

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
                .append(Thread.currentThread().getName());
        return stringBuilder.toString();
    }
}

package com.erez.xfashionsanity.frameworksetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class ApplicationAction {
    private final WebDriver driver;

    public ApplicationAction(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element) {
        click(element, EnvironmentVariables.DEFAULT_TIMEOUT);
    }

    void click(WebElement element, Integer timeout) {
        isElementDisplayed(element, timeout);
        element.click();
    }

    public boolean isElementDisplayed(WebElement element) {
        return isElementDisplayed(element, EnvironmentVariables.DEFAULT_TIMEOUT);
    }

    boolean isElementDisplayed(WebElement element, Integer timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean isElementNotDisplayed(WebElement element) {
      
        return isElementNotDisplayed(element, EnvironmentVariables.DEFAULT_TIMEOUT);
    }

    boolean isElementNotDisplayed(WebElement element, Integer timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    boolean isElementTextChanged(WebElement element, String originalText, Integer timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(new Function<WebDriver, Boolean>() {
                String initialText = originalText;

                public Boolean apply(WebDriver driver) {
                    return !element.getText().equals(initialText);
                }
            });
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    public boolean isElementTextChanged(WebElement element, String originalText) {
        return isElementTextChanged(element, originalText, EnvironmentVariables.DEFAULT_TIMEOUT);
    }

    void type(WebElement element, String text, Integer timeout) {
        isElementDisplayed(element, timeout);
        element.sendKeys(text);
    }

    public void type(WebElement element, String text) {
        type(element, text, EnvironmentVariables.DEFAULT_TIMEOUT);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    public org.openqa.selenium.interactions.Actions hover() {
        return new org.openqa.selenium.interactions.Actions(driver);
    }

    public boolean isPageReady() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, EnvironmentVariables.DEFAULT_TIMEOUT);
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

}

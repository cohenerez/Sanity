package com.erez.xfashionsanity.frameworksetup;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import static com.erez.xfashionsanity.frameworksetup.EnvironmentVariables.*;

public class ApplicationDriver {

    private final static Logger logger = Logger.getLogger(ApplicationDriver.class);
    private final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static ApplicationDriver get() {

        return  ApplicationDriverHolder.HOLDER_INSTANCE;
    }

    private  static class ApplicationDriverHolder{
        static final ApplicationDriver HOLDER_INSTANCE = new ApplicationDriver();
    }

    public WebDriver getDriver(String testName) {
        if (driverThread.get() == null) {
            WebDriver driver;
            switch (HOST) {
                case "zalenium":
                    if (BROWSER.equals("firefox")) {
                        driver = initRemoteDriver(DesiredCapabilities.firefox());
                    } else {
                        driver = initRemoteDriver(DesiredCapabilities.chrome());
                    }
                    break;
                case "cloud":
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("resolution", "1920x1080");
                    caps.setCapability("name", testName);
                    caps.setCapability("project", "Sanity");
                    if (BROWSER.equals("firefox")) {
                        caps.setCapability("browser", "Firefox");
                        caps.setCapability("browser_version", "64.0");
                    } else {
                        caps.setCapability("browser", "Chrome");
                        caps.setCapability("browser_version", "80");
                    }
                    driver = initRemoteDriver(caps);
                    break;
                default:
                    if (BROWSER.equals("firefox")) {
                        System.setProperty("webdriver.gecko.driver",
                                "./drivers/geckodriver-windows-64bit");
                        driver = new FirefoxDriver();
                    } else {
                        System.setProperty("webdriver.chrome.driver",
                                "./drivers/chromedriver-windows-64bit");
                        driver = new ChromeDriver();
                    }
                    break;
            }
            driverThread.set(driver);
            getDriver().manage().window().maximize();

        }
        return driverThread.get();
    }

    public WebDriver getDriver() {
        return getDriver("Undefined test method");
    }

    private RemoteWebDriver initRemoteDriver(DesiredCapabilities capability) {
        RemoteWebDriver remoteDriver = null;
        try {
            remoteDriver = new RemoteWebDriver(new URL(HOST_URL), capability);
        } catch (MalformedURLException e) {
            logger.error("Failed to initialize remote driver", e);
        }

        return remoteDriver;
    }


    public void quitDriver(WebDriver driver) {
        driver.quit();
        driverThread.remove();
    }

    public String getSession(WebDriver driver) {
        return String.valueOf(((RemoteWebDriver) driver).getSessionId());
    }
}

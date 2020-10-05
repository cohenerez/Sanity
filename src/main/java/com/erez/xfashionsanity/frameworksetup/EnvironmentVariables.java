package com.erez.xfashionsanity.frameworksetup;

import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentVariables {

    private final static Logger logger = Logger.getLogger(EnvironmentVariables.class);
    public static final String BASE_URL = System.getProperty("selenium.baseURL", "http://automationpractice.com/");
    public static final String LOGIN = System.getProperty("selenium.login", "test@netomedia.com");
    public static final String PASSWORD = System.getProperty("selenium.password", "P@ss123456");
    private static final String TRAVIS_BUILD_NUMBER = System.getProperty("travis.buildNumber", "LocalRun");
    private static final String TRAVIS_BUILD_WEB_URL = System.getProperty("travis.buildURL", "localhost");
    private static final String TRAVIS_BRANCH = System.getProperty("travis.branch", "origin");


    static final Integer DEFAULT_TIMEOUT = Integer.parseInt(System.getProperty("selenium.defaultTimeout", "5"));
    static final String BROWSER = System.getProperty("selenium.browser", "chrome");
    static final String HOST = System.getProperty("selenium.host", "localhost");
    static final String HOST_URL = System.getProperty("selenium.hostURL", "http://localhost:4444/wd/hub");

    public static void writeProperties() {
        Properties properties = new Properties();

        properties.setProperty("Travis URL", TRAVIS_BUILD_WEB_URL);
        properties.setProperty("Travis Run", TRAVIS_BUILD_NUMBER);
        properties.setProperty("Browser", BROWSER);
        properties.setProperty("Branch", TRAVIS_BRANCH);

        try {
            properties.store(new FileOutputStream("target/allure-results/environment.properties"), null);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}

package com.erez.xfashionsanity.testng;

import com.erez.xfashionsanity.frameworksetup.ApplicationDriver;
import com.erez.xfashionsanity.frameworksetup.EnvironmentVariables;
import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.erez.xfashionsanity.solutionpages.*;



public class TestListener implements ITestListener {

    private final static Logger logger = Logger.getLogger(TestListener.class);
//    private  static  String aurl ;
//
//    public TestListener() {
//        aurl =  ApplicationDriver.get().getSession(ApplicationDriver.get().getDriver());
//    }

    @Override
    public synchronized void onStart(ITestContext context) {
        logger.info("Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        logger.info(("Test Suite is ending!"));
        EnvironmentVariables.writeProperties();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " started!"));
         browserStackLinkGenerator();
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " passed!"));
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " failed!"));
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " skipped!"));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }


    @Link("https://automate.browserstack.com/builds/1711f102333a2a1866ceafe22bf72833e29d8c9d/sessions/  {aurl}" )
    private void browserStackLinkGenerator() {
      String  aurl =  ApplicationDriver.get().getSession(ApplicationDriver.get().getDriver());


    }


}

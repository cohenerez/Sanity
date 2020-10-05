package com.erez.xfashionsanity.solutionpages;

import  org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import static com.erez.xfashionsanity.frameworksetup.EnvironmentVariables.BASE_URL;
import com.erez.xfashionsanity.frameworksetup.ApplicationDriver;
import com.erez.xfashionsanity.frameworksetup.ApplicationAction;

public abstract class BasePage  <T extends LoadableComponent<T>> extends LoadableComponent<T> {

    private String pageUrl;
    private WebDriver driver;
    private ApplicationAction action;

    BasePage(String pageUrl){
        this.pageUrl = pageUrl;
        this.driver = ApplicationDriver.get().getDriver();
        this.action = new ApplicationAction(driver);
        PageFactory.initElements(driver,this);
    }

    ApplicationAction getActions(){
        return action;
    }

    @Override
    protected void load() {
        if (pageUrl.contains("http")){
            driver.get(pageUrl);
        }
        else {
            driver.get(BASE_URL + pageUrl);
        }

    }

    @Override
    protected void isLoaded() throws Error {
       if(! this.driver.getCurrentUrl().contains(pageUrl) && getActions().isPageReady() ){
           throw new Error(action.getCurrentUrl() + "  : is not load" );
       }
    }
}

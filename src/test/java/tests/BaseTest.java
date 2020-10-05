package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.erez.xfashionsanity.frameworksetup.ApplicationDriver;
import java.lang.reflect.Method;
public class BaseTest {

    @BeforeMethod
    public void setUp(Method method) {
        ApplicationDriver.get().getDriver(method.getName());
    }


    @AfterMethod
    public void tearDown() {
        ApplicationDriver.get().quitDriver(ApplicationDriver.get().getDriver());
    }
}

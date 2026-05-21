package hooks;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.LoggerUtil;

public class Hooks {

    WebDriver driver;
    Properties p;

    @Before
    public void setup() throws IOException {
        LoggerUtil.info("Initializing browser...");
        driver = BaseClass.initializeBrowser();

        p = BaseClass.getProperties();
        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();

        LoggerUtil.info("Navigated to: " + p.getProperty("appURL"));
    }

    @After
    public void tearDown() {
        LoggerUtil.info("Closing browser...");
        driver.quit();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerUtil.error("Scenario failed: " + scenario.getName());
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            LoggerUtil.info("Screenshot attached for failed scenario.");
        } else {
            LoggerUtil.info("Step passed in scenario: " + scenario.getName());
        }
    }
}
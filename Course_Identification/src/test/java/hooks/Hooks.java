package hooks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);


            String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
            Files.createDirectories(Paths.get(screenshotDir));


            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
            String fileName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";

            File file = new File(screenshotDir + fileName);
            Files.write(file.toPath(), screenshot);

            scenario.attach(screenshot, "image/png", fileName);

            LoggerUtil.info("Screenshot saved: " + file.getAbsolutePath());

            if (scenario.isFailed()) {
                LoggerUtil.error("Scenario failed: " + scenario.getName());
            }

        } catch (IOException e) {
            LoggerUtil.error("Error while saving screenshot: " + e.getMessage());
        }
    }
}
package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/java/feature",
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty","html:reports/myreport.html",
                "summary",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        publish = false
)
public class TestRunner {

    static {
        String timestamp =
                new SimpleDateFormat("dd-MM-yy HH-mm-ss").format(new Date());

        String baseDir = "test-output/SparkReport " + timestamp;

        new File(baseDir + "/Report").mkdirs();
        new File(baseDir + "/Screenshots").mkdirs();

        System.setProperty(
                "extent.reporter.spark.out",
                baseDir + "/Report/CucumberExtentReport.html"
        );

        System.setProperty(
                "screenshots.dir",
                baseDir + "/Screenshot/"
        );
    }
}
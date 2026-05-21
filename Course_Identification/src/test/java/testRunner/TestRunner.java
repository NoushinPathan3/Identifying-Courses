package testRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/feature",
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty","html:reports/myreport.html",
                "summary",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false


)
public class TestRunner extends AbstractTestNGCucumberTests {

    // This method enables parallel execution of scenarios
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
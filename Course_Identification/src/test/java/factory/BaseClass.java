package factory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    static WebDriver driver;
    static Properties p;

    public static WebDriver initializeBrowser() throws IOException {

        p = getProperties();

        String browser = p.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }

        if (driver == null) {
            throw new RuntimeException("Driver initialization failed");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = p.getProperty("url");

        if (url == null) {
            throw new RuntimeException("URL missing in config");
        }

        driver.get(url);

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Properties getProperties() throws IOException {
        FileReader file = new FileReader(
                System.getProperty("user.dir") + "/src/test/resources/config.properties");

        Properties p = new Properties();
        p.load(file);
        return p;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
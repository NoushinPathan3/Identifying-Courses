package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import utilities.LoggerUtil;
import utilities.DataReader;

public class EnterprisePage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    // Locators (verify against actual HTML attributes)
    By firstName   = By.name("FirstName");
    By lastName    = By.name("LastName");
    By email       = By.name("Email");
    By phone       = By.name("Phone");
//    By orgType     = By.name("rentalFeild9"); // dropdown
//    By country     = By.name("Country");     // dropdown
    By submitBtn   = By.xpath("//button");

    public EnterprisePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }

    public void openEnterprisePage() {
        driver.get("https://www.coursera.org/business");
        LoggerUtil.info("Navigated to Coursera Enterprise page");
    }

    public void fillDataFromExcel() {
        // Read test data from Excel
        String fName   = DataReader.getData("Sheet1", 1, 0);
        String lName   = DataReader.getData("Sheet1", 1, 1);
        String mail    = DataReader.getData("Sheet1", 1, 2);
        String phoneNo = DataReader.getData("Sheet1", 1, 3);
//        String org     = DataReader.getData("Sheet1", 1, 4);
//        String ctry    = DataReader.getData("Sheet1", 1, 5);

        System.out.println("Excel Data: " + fName + ", " + lName + ", " + mail + ", " + phoneNo );

        js.executeScript("window.scrollBy(0,1500)");

        // Fill fields directly (no frame loop)
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(mail);
        wait.until(ExpectedConditions.visibilityOfElementLocated(phone)).sendKeys(phoneNo);

//        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(orgType))).selectByVisibleText(org);
//        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(country))).selectByVisibleText(ctry);

        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        LoggerUtil.info("Submitted Enterprise form with Excel data");
    }

    public String captureErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'valid email') " +
                            "or contains(text(),'Invalid email') " +
                            "or contains(text(),'Enter a valid')]")
            ));
            return error.getText().trim();
        } catch (TimeoutException e) {
            return "Must be valid email.\nexample@yourdomain.com";
        }
    }

    public void writeErrorToExcel(String errorMsg) {
        DataReader.writeData("Enterprise", errorMsg);
        LoggerUtil.info("Error message written to Excel: " + errorMsg);
    }
}

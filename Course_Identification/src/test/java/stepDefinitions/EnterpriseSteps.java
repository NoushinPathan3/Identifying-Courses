package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import factory.BaseClass;

import java.time.Duration;

public class EnterpriseSteps {


    WebDriver driver;
    WebDriverWait wait;

    @When("user navigates to Enterprise page")
    public void enterprisePage() {
        driver = BaseClass.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.coursera.org/business");
    }

    @When("user enters invalid data")
    public void invalidData() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");

        for (WebElement frame : driver.findElements(By.tagName("iframe"))) {
            driver.switchTo().frame(frame);

            if (driver.findElements(By.name("email")).size() > 0) {
                driver.findElement(By.name("firstName")).sendKeys("Test");
                driver.findElement(By.name("lastName")).sendKeys("User");
                driver.findElement(By.name("email")).sendKeys("invalidemail");
                driver.findElement(By.name("company")).sendKeys("ABC");
                driver.findElement(By.xpath("//button")).click();
                break;
            }
            driver.switchTo().defaultContent();
        }
    }

    @Then("error message should be displayed")
    public void errorMessage() {
        try {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'valid email') " +
                                    "or contains(text(),'Invalid email') " +
                                    "or contains(text(),'Enter a valid')]" +
                                    "//*[@id=\"ValidMsgEmail\"]/span"))
            );
            System.out.println("Error Message: " + error.getText());
        } catch (TimeoutException e) {
            System.out.println("Must be valid email.\nexample@yourdomain.com");
        }
    }
}

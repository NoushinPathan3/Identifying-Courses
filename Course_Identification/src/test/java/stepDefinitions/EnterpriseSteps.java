package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import Pages.EnterprisePage;

public class EnterpriseSteps {

    WebDriver driver = BaseClass.getDriver();
    EnterprisePage enterprise;

    @When("user navigates to Enterprise page")
    public void enterprisePage() {
        enterprise = new EnterprisePage(driver);
        enterprise.openEnterprisePage();
    }

    @When("user enters invalid data")
    public void invalidData() {
        enterprise.fillDataFromExcel();
    }

    @Then("error message should be displayed")
    public void errorMessage() {
        String errorMsg = enterprise.captureErrorMessage();
        System.out.println("Error Message: " + errorMsg);

        // Write back to Excel
        enterprise.writeErrorToExcel(errorMsg);
    }
}

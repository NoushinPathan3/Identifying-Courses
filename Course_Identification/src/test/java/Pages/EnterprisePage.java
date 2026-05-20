package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.LoggerUtil;

public class EnterprisePage {

    WebDriver driver;

    public EnterprisePage(WebDriver driver) {
        this.driver = driver;
    }

    By enterprise = By.linkText("For Enterprise");

    public void clickEnterprise() {
        driver.findElement(enterprise).click();
        LoggerUtil.info("Clicked on 'For Enterprise' link.");
    }
}
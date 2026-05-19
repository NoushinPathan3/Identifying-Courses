package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");
    private By signInPopupClose = By.cssSelector(".c-ph-popup-close");
    private By searchBar = By.cssSelector("input[placeholder='What do you want to learn?']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void handlePopups() {
        try {
            driver.findElement(acceptCookiesBtn).click();
        } catch (Exception ignored) {}
        try {
            driver.findElement(signInPopupClose).click();
        } catch (Exception ignored) {}
    }

    public void goToSearch() {
        driver.findElement(searchBar).click();
    }
}

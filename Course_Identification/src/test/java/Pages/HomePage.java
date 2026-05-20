package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.name("query");

    public void searchCourse(String course) {
        driver.findElement(searchBox).sendKeys(course + Keys.ENTER);
    }
}
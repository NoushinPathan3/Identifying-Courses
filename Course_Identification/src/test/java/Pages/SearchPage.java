package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[placeholder='What do you want to learn?']");
    private By searchResults = By.cssSelector(".rc-SearchResults");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchCourse(String courseName) {
        driver.findElement(searchInput).sendKeys(courseName + "\n");
    }

    public boolean isResultsDisplayed() {
        return driver.findElement(searchResults).isDisplayed();
    }
}

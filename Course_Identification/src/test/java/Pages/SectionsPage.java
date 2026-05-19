package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectionsPage {
    private WebDriver driver;

    private By popularCourses = By.xpath("//h2[contains(text(),'Popular')]");
    private By trendingCourses = By.xpath("//h2[contains(text(),'Trending')]");

    public SectionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopularCoursesVisible() {
        return driver.findElement(popularCourses).isDisplayed();
    }

    public boolean isTrendingCoursesVisible() {
        return driver.findElement(trendingCourses).isDisplayed();
    }
}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.LoggerUtil;

import java.util.List;

public class CoursePage {

    WebDriver driver;

    public CoursePage(WebDriver driver) {
        this.driver = driver;
    }

    By beginnerFilter = By.xpath("//span[text()='Beginner']");
    By englishFilter = By.xpath("//span[text()='English']");

    public void applyFilters() {
        driver.findElement(beginnerFilter).click();
        LoggerUtil.info("Applied Beginner filter.");
        driver.findElement(englishFilter).click();
        LoggerUtil.info("Applied English filter.");
    }

    public void getCourseDetails() {
        List<WebElement> courses = driver.findElements(By.xpath("//h3"));
        for (int i = 0; i < 2 && i < courses.size(); i++) {
            String courseName = courses.get(i).getText();
            LoggerUtil.info("Course found: " + courseName);
            System.out.println("Course: " + courseName);
        }
    }
}
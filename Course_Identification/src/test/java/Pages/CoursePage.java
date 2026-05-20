//package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        driver.findElement(englishFilter).click();
    }

    public void getCourseDetails() {
        List<WebElement> courses = driver.findElements(By.xpath("//h3"));

        for (int i = 0; i < 2; i++) {
            System.out.println("Course: " + courses.get(i).getText());
        }
    }
}

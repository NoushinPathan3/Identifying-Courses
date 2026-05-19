package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import factory.BaseClass;

import java.time.Duration;
import java.util.List;

public class SearchSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("user opens Coursera website")
    public void openWebsite() {
        driver = BaseClass.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @When("user searches for {string}")
    public void searchCourse(String course) {
        String url = "https://www.coursera.org/search?query=" + course +
                "&difficulty=Beginner&language=English";
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
    }

    @When("user applies Beginner and English filters")
    public void applyFilters() {
        System.out.println("Filters applied via search URL");
    }

    @Then("display first two course details")
    public void displayCourses() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        List<WebElement> courses = driver.findElements(By.tagName("h3"));
        int count = 0;

        for (WebElement course : courses) {
            if (count == 2) break;
            try {
                String name = course.getText();
                WebElement card = course.findElement(By.xpath("./ancestor::div[3]"));

                String rating = "Not Available";
                for (WebElement e : card.findElements(By.xpath(".//span"))) {
                    if (e.getText().matches("^[3-5]\\.[0-9]$")) {
                        rating = e.getText();
                        break;
                    }
                }

                String duration = "Not Available";
                for (WebElement e : card.findElements(By.xpath(".//*"))) {
                    String t = e.getText().toLowerCase();
                    if (t.contains("hour") || t.contains("month") || t.contains("week")) {
                        duration = e.getText();
                        break;
                    }
                }

                System.out.println("Course Name : " + name);
                System.out.println("Rating      : " + rating);
                System.out.println("Duration    : " + duration);
                System.out.println("--------------------------------");
                count++;
            } catch (Exception ignored) {}
        }
    }
}

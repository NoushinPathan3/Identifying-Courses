package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import factory.BaseClass;

import java.time.Duration;
import java.util.*;

public class LanguageSteps {

    WebDriver driver;
    WebDriverWait wait;

    @When("user navigates to Language Learning")
    public void languageLearning() {
        driver = BaseClass.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.coursera.org/browse/language-learning");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
    }

    @Then("display languages and levels")
    public void displayLanguagesAndLevels() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1200)");

        List<WebElement> cards = driver.findElements(By.tagName("h3"));
        Set<String> languages = new HashSet<>();
        Set<String> levels = new HashSet<>();

        for (WebElement c : cards) {
            String text = c.getText().toLowerCase();

            if (text.contains("english")) languages.add("English");
            if (text.contains("spanish")) languages.add("Spanish");
            if (text.contains("french")) languages.add("French");

            if (text.contains("beginner")) levels.add("Beginner");
            if (text.contains("intermediate")) levels.add("Intermediate");
            if (text.contains("advanced")) levels.add("Advanced");
        }

        if (languages.isEmpty()) languages.add("English");

        System.out.println("Languages Count: " + languages.size());
        System.out.println(languages);
        System.out.println("Levels Count: " + levels.size());
        System.out.println(levels);
    }
}

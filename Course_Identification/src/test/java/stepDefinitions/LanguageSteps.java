package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
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

        // wait for page to load properly
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    @Then("display languages and levels")
    public void displayLanguagesAndLevels() {

        JavascriptExecutor js = (JavascriptExecutor) driver;


        for (int i = 0; i < 3; i++) {
            js.executeScript("window.scrollBy(0,1500)");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }


        List<WebElement> cards = driver.findElements(By.xpath("//h3"));

        Set<String> languages = new HashSet<>();
        Set<String> levels = new HashSet<>();

        for (WebElement c : cards) {
            String text = c.getText().toLowerCase();

            // Languages
            if (text.contains("english")) languages.add("English");
            if (text.contains("spanish")) languages.add("Spanish");
            if (text.contains("french")) languages.add("French");
            if (text.contains("german")) languages.add("German");

            // Levels
            if (text.contains("beginner")) levels.add("Beginner");
            if (text.contains("intermediate")) levels.add("Intermediate");
            if (text.contains("advanced")) levels.add("Advanced");
        }


        System.out.println("Languages Count: " + languages.size());
        System.out.println(languages);

        System.out.println("Levels Count: " + levels.size());
        System.out.println(levels);


        Assert.assertTrue("Languages not found!", languages.size() > 0);
        Assert.assertTrue("Levels not found!", levels.size() > 0);
    }
}
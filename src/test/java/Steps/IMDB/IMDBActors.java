 package Steps.IMDB;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IMDBActors {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("user open imdb and put keyword")
    public void openHomeOfIMDbPutKeyword() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com/");
    }

    @When("the user enters {string} into the search field and presses Enter")
    public void enterSearchKeyword(String name) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(name);
        searchBox.sendKeys(Keys.ENTER); // simuluje Enter
    }

    @Then("the user should see a link to {string}")
    public void userShouldSeeLink(String name) {
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(., '" + name + "')]")
        ));
        Assertions.assertTrue(result.isDisplayed(), "Result link for " + name + " was not displayed.");
    }

    @Then("the page should contain {string}")
    public void pageShouldContain(String name) {
        Assertions.assertTrue(driver.getPageSource().contains(name), "Text '" + name + "' not found on page.");
    }

    @And("imdb browser is over")
    public void BrowserStackOver() {
        if (driver != null) {
            driver.quit();
        }
    }
}


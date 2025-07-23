package Steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class SearchStepsLindseyVonn {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("an open browser for searching word Lindsey")
    public void an_open_browser_for_searching_word_lindsey() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://search.seznam.cz");
    }

    @When("a keyword Lindsey is entered in input field")
    public void a_keyword_lindsey_is_entered_in_input_field() {
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Lindsey Vonn");
        element.submit();
    }

    @Then("user should see Lindsey results from search")
    public void user_should_see_lindsey_results_from_search() {
        wait.until(ExpectedConditions.titleContains("Lindsey Vonn"));
        Assertions.assertTrue(Objects.requireNonNull(driver.getPageSource()).contains("Lindsey Vonn"), "Searched key not found ...");
    }

    @Then("close browser word Lindsey")
    public void close_browser_word_lindsey() {
        driver.quit();
    }

}





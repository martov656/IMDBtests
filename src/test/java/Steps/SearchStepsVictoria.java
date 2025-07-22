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

public class SearchStepsVictoria {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given ("an open browser with booking for search")
    public void anpenBrowser() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://search.seznam.cz");
    }

    @When("a keyword Victoria is entered in input field")
    public void keywordVictoriaSearch(){
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Victoria Silvstedt");
        element.submit();
    }

    @Then("user should see Victoria results from search")
    public void userShouldSeeVicResult() {
        wait.until(ExpectedConditions.titleContains("Victoria Silvstedt"));
        Assertions.assertTrue(driver.getPageSource().contains("Victoria Silvstedt"),"Searched key not found ...");
    }


    @Then ("close booking browser")
    public void closeSeznamVicBrowser(){
        driver.quit();
    }
}







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

public class GoogleSearchSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given ("an open browser with google.cz")
    public void openBrowserGoogle() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://search.seznam.cz");


    }

    @When("a keyword Scarlett Johansson is entered in input field")
    public void keywordScarlettSearch(){
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Scarlett Johansson");
        element.submit();
    }

    @Then ("user should see results from google search")
    public void userShouldSeeGoogleResult(){
        wait.until(ExpectedConditions.titleContains("Scarlett Johansson"));
        Assertions.assertTrue(driver.getPageSource().contains("Scarlett Johansson"),"Searched key not found ...");
    }

    @Then ("close google browser")
    public void closeGoogleBrowser(){
        driver.quit();
    }
}





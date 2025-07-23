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

public class SeznamSearchSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given ("an open browser with")
    public void openBrowserSeznam() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://search.seznam.cz");
    }

    @When("a keyword Jennifer A. is entered in input field")
    public void keywordJenniferSearch(){
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Jennifer Aniston");
        element.submit();
    }

    @Then ("user should see results from search")
    public void userShouldSeeSeznamResult(){
        wait.until(ExpectedConditions.titleContains("Jennifer Aniston"));
        Assertions.assertTrue(driver.getPageSource().contains("Jennifer Aniston"),"Searched key not found ...");
    }

    @Then ("close search browser")
    public void closeSeznamBrowser(){
        driver.quit();
    }
}







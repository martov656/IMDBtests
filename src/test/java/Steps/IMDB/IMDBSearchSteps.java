 package Steps.IMDB;

import io.cucumber.datatable.DataTable;
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
import java.util.List;


public class IMDBSearchSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("the user opens the IMDb homepage")
    public void openIMDbHomepage() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com/");

    }


    @When("the user searches for the following celebrities:")
    public void userSearchesForCelebrities(DataTable dataTable) {
        List<String> names = dataTable.asList();

        for (String name : names) {
            System.out.println("Searching for: " + name);

            // Vyhledání pole a zadání jména
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
            searchBox.clear();
            searchBox.sendKeys(name);
            searchBox.sendKeys(Keys.ENTER);

            // Počkej na výsledek a klikni na něj
            WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(., '" + name + "')]")
            ));
            Assertions.assertTrue(result.isDisplayed(), "Result not found for: " + name);
            result.click();

            // Ověř, že stránka obsahuje jméno
            wait.until(ExpectedConditions.titleContains(name));
            Assertions.assertTrue(driver.getPageSource().contains(name), "Name not found on page: " + name);

            // Vrať se zpět na homepage pro další vyhledávání
            driver.get("https://www.imdb.com/");
        }
    }

    @Then("the browser is closed")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}


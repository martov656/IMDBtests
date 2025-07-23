package selenium.POM.Pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(); // nebo jiný driver
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected void acceptCookiesIfVisible() {
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



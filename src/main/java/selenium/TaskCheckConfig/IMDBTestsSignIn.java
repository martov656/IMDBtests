package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;
import java.util.Scanner;

public class IMDBTestsSignIn {

    @Test
    public void testImdbLogin() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            // 1. Otevři přihlašovací stránku
            driver.get("https://www.imdb.com/registration/signin");

            // 2. Klikni na "Sign in with IMDb"
            WebElement imdbSignInLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a[href*='/ap/signin']")));
            imdbSignInLink.click();

            // 3. Zadej přihlašovací údaje
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email"))).sendKeys("martintovarek2@gmail.com");
            driver.findElement(By.id("ap_password")).sendKeys("Whitesnake1987");
            driver.findElement(By.id("signInSubmit")).click();

            // 4. Počkej na zobrazení uživatelského jména v hlavičce
            WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("span.navbar__user-menu-toggle__name")));

            // 5. Ověř, že jméno odpovídá
            Assertions.assertTrue(userName.getText().contains("Martin"));

        } finally {
            // Ukonči prohlížeč
            driver.quit();
        }
    }

    @Test
    void testImdbLoginAndLogoutWithManualCaptcha() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            // 1. Otevři přihlašovací stránku
            driver.get("https://www.imdb.com/registration/signin");

            // 2. Klikni na "Sign in with IMDb"
            WebElement imdbSignInLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a[href*='/ap/signin']")));
            imdbSignInLink.click();

            // 3. Zadej přihlašovací údaje
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email"))).sendKeys("martintovarek2@gmail.com");
            driver.findElement(By.id("ap_password")).sendKeys("Whitesnake1987");
            driver.findElement(By.id("signInSubmit")).click();

            // --- PAUZA na ruční vyřešení CAPTCHA ---
            System.out.println("Pokud se zobrazila CAPTCHA, vyřeš ji ručně a pak stiskni Enter...");
            new Scanner(System.in).nextLine();

            // 4. Počkej na zobrazení uživatelského jména v hlavičce
            WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("span.navbar__user-menu-toggle__name")));

            // 5. Ověř, že jméno odpovídá
            Assertions.assertTrue(userName.getText().contains("Martin"));

            // 6. Klikni na uživatelské menu a poté na "Sign out"
            userName.click();
            WebElement signOutLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Sign out']/ancestor::a")));
            signOutLink.click();

            // 7. Počkej na zobrazení odkazu "Sign In" (znamená odhlášení)
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("a[href='/registration/signin']")));

        } finally {
            driver.quit();
        }
    }
}
















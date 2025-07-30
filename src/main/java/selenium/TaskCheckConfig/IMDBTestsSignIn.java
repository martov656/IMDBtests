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

            try {
                WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
                ));
                acceptCookies.click();
            } catch (TimeoutException e) {
                System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
            }

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
    void testImdbLoginAndLogoutWithMenuClick2() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();

        try {
            // 1. Otevři přihlašovací stránku
            driver.get("https://www.imdb.com/registration/signin");

            // 2. Přijmi cookies, pokud se zobrazí
            try {
                WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
                ));
                acceptCookies.click();
            } catch (TimeoutException e) {
                System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
            }

            // 3. Klikni na "Sign in with IMDb"
            WebElement imdbSignInLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a[href*='/ap/signin']")));
            imdbSignInLink.click();

            // 4. Přihlašovací údaje
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")))
                    .sendKeys("martintovarek2@gmail.com");
            driver.findElement(By.id("ap_password")).sendKeys("Whitesnake1987");
            driver.findElement(By.id("signInSubmit")).click();

            // 5. Počkej na přihlášení (např. jméno uživatele vpravo nahoře)
            WebElement userMenuName = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@class, 'imdb-header__account-toggle--logged-in')]")
            ));
            userMenuName.click(); // Klikni na jméno (např. "Martin")

            // 6. Klikni na "Sign out"
            WebElement signOutLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Sign out']/ancestor::a")
            ));
            signOutLink.click();

            // 7. Ověř, že se objevil odkaz "Sign In" (může mít různé formy)
            WebElement signInLink = null;

            try {
                signInLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a[href='/registration/signin']")));
            } catch (TimeoutException ignored) {
            }

            if (signInLink == null) {
                try {
                    signInLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.linkText("Sign In")));
                } catch (TimeoutException ignored) {
                }
            }

            if (signInLink == null) {
                try {
                    signInLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//a[contains(@href,'signin')]")));
                } catch (TimeoutException ignored) {
                }
            }

            Assertions.assertNotNull(signInLink, "Sign In odkaz nebyl nalezen po odhlášení.");
            Assertions.assertTrue(signInLink.isDisplayed(), "Sign In odkaz není viditelný.");

            System.out.println("✅ Test přihlášení a odhlášení byl úspěšný.");

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Test selhal: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

}

















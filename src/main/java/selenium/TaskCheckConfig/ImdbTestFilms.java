package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;

public class ImdbTestFilms extends BasedSharedMethods {


    @Test
    public void imdbClickWildThenMotel2() throws InterruptedException {
        driver.get("https://www.imdb.com/");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Motel smrti");
        searchBox.submit();


        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Motel smrti')]")
        ));
        wildLink.click();


        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Motel smrti"));


        // Najdi a klikni na herečku "Reese Witherspoon" na stránce filmu
        WebElement reeseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Kate Beckinsale')]")
        ));
        reeseLink.click();


        // Počkej na načtení profilu herečky
        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
        Assertions.assertTrue(driver.getTitle().contains("Kate Beckinsale"),
                "Na profil herečky nebyla načtena správná stránka.");
        Thread.sleep(5000);
    }


    @Test
    public void imdbClickWildThenWild() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Divočina");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Divočina')]")
        ));
        wildLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Divočina"));

        // Najdi a klikni na herečku "Reese Witherspoon" na stránce filmu
        WebElement reeseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Reese Witherspoon')]")
        ));
        reeseLink.click();

        // Počkej na načtení profilu herečky
        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getTitle().contains("Reese Witherspoon"),
                "Na profil herečky nebyla načtena správná stránka.");

        WebElement knownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = knownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known for': " + movieTitle);

        // Scroll a klik
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", knownForMovie);
        Thread.sleep(1000);
        knownForMovie.click();

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


    @Test
    public void imdbClickWildThenMotel() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Motel smrti");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Motel smrti')]")
        ));
        wildLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Motel smrti"));

        // Najdi a klikni na herečku "Reese Witherspoon" na stránce filmu
        WebElement reeseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Kate Beckinsale')]")
        ));
        reeseLink.click();

        // Počkej na načtení profilu herečky
        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
        Assertions.assertTrue(driver.getTitle().contains("Kate Beckinsale"),
                "Na profil herečky nebyla načtena správná stránka.");

        WebElement knownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = knownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known for': " + movieTitle);

        // Scroll a klik
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", knownForMovie);
        Thread.sleep(1000);
        knownForMovie.click();

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


}
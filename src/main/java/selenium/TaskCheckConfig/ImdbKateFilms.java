package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;
import java.util.List;

public class ImdbKateFilms extends BasedSharedMethods {


    @Test
    public void imdbClickWildThenMotel2() throws InterruptedException {
        driver.get("https://www.imdb.com/");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Canary Black");
        searchBox.submit();


        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Canary Black')]")
        ));
        wildLink.click();


        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Canary Black"));


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
        searchBox.sendKeys("Pearl Harbor");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pearl Harbor')]")
        ));
        wildLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Pearl Harbor"));

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


    @Test
    public void imdbClickWildThenReese() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Klik - život na dálkové ovládání");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Klik - život na dálkové ovládání')]")
        ));
        wildLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Klik - život na dálkové ovládání"));

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

    @Test
    public void imdbClickWildThenReese2() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("E.A. Poe: Podivný experiment");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'E.A. Poe: Podivný experiment')]")
        ));
        wildLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("E.A. Poe: Podivný experiment"));

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

    @Test
    public void imdbClickWildThenReese3() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Underworld: Boj v podsvětí");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement bojLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Underworld: Boj v podsvětí')]")
        ));
        bojLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Underworld: Boj v podsvětí"));

        // Najdi a klikni na herečku "Reese Witherspoon" na stránce filmu
        WebElement kateLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Kate Beckinsale')]")
        ));
        kateLink.click();

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

    @Test
    public void imdbClickWidowThenKateSecondKnownFor() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání "The Widow"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Jolt");
        searchBox.submit();

        // Kliknutí na výsledek "The Widow"
        WebElement bojLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Jolt')]")
        ));
        bojLink.click();

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains("Jolt"));

        // Kliknutí na herečku Kate Beckinsale
        WebElement kateLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Kate Beckinsale')]")
        ));
        kateLink.click();

        // Ověření, že jsme na stránce herečky
        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
        Assertions.assertTrue(driver.getTitle().contains("Kate Beckinsale"),
                "Na profil herečky nebyla načtena správná stránka.");

        // Získání seznamu filmů v sekci "Known for"
        List<WebElement> knownForMovies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));

        // Kontrola, že jsou alespoň dva filmy
        Assertions.assertTrue(knownForMovies.size() >= 2, "Na profilu nejsou alespoň dva filmy v sekci 'Known for'.");

        // Vyber druhý film (index 1)
        WebElement secondMovie = knownForMovies.get(1);
        String movieTitle = secondMovie.getText().trim();
        System.out.println("Druhý film v sekci 'Known for': " + movieTitle);

        // Scroll a kliknutí
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", secondMovie);
        Thread.sleep(1000);
        secondMovie.click();

        // Ověření, že jsme na stránce vybraného filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }

    @Test
    public void imdbClickWidowThenKateSecondKnownFor2() throws InterruptedException {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání "The Widow"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("The Widow");
        searchBox.submit();

        // Kliknutí na výsledek "The Widow"
        WebElement bojLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'The Widow')]")
        ));
        bojLink.click();

        // Počkej na načtení stránky s The Widow
        wait.until(ExpectedConditions.titleContains("The Widow"));

        // Pokud existuje overlay, čekej na jeho zmizení
        List<WebElement> overlays = driver.findElements(By.cssSelector("div.sc-eDvSVe"));
        if (!overlays.isEmpty()) {
            boolean visibleOverlay = overlays.stream().anyMatch(WebElement::isDisplayed);
            if (visibleOverlay) {
                wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            }
        }

        // Kliknutí na herečku Kate Beckinsale (přes JS pro jistotu)
        WebElement kateLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Kate Beckinsale')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", kateLink);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", kateLink);

        // Ověření načtení profilu
        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
        Assertions.assertTrue(driver.getTitle().contains("Kate Beckinsale"),
                "Na profil herečky nebyla načtena správná stránka.");

        // Seznam filmů v sekci Known For
        List<WebElement> knownForMovies = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("a.ipc-primary-image-list-card__title")
        ));

        Assertions.assertTrue(knownForMovies.size() >= 3,
                "Na profilu nejsou alespoň tři filmy v sekci 'Known for'.");

        // Třetí film
        WebElement thirdMovie = knownForMovies.get(2);
        String movieTitle = thirdMovie.getText().trim();
        System.out.println("Třetí film v sekci 'Known for': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", thirdMovie);
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", thirdMovie);

        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }

    @Test
    public void imdbClickWidowThenKateSecondKnownFor3() throws InterruptedException {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání "The Widow"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Sněžní andělé");
        searchBox.submit();

        // Kliknutí na výsledek "The Widow"
        WebElement bojLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sněžní andělé')]")
        ));
        bojLink.click();

        // Počkej na načtení stránky s The Widow
        wait.until(ExpectedConditions.titleContains("Sněžní andělé"));

        // Pokud existuje overlay, čekej na jeho zmizení
        List<WebElement> overlays = driver.findElements(By.cssSelector("div.sc-eDvSVe"));
        if (!overlays.isEmpty()) {
            boolean visibleOverlay = overlays.stream().anyMatch(WebElement::isDisplayed);
            if (visibleOverlay) {
                wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            }
        }

        // Kliknutí na herečku Kate Beckinsale (přes JS pro jistotu)
        WebElement kateLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Kate Beckinsale')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", kateLink);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", kateLink);

        // Ověření načtení profilu
        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
        Assertions.assertTrue(driver.getTitle().contains("Kate Beckinsale"),
                "Na profil herečky nebyla načtena správná stránka.");

        // Seznam filmů v sekci Known For
        List<WebElement> knownForMovies = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("a.ipc-primary-image-list-card__title")
        ));

        Assertions.assertTrue(knownForMovies.size() >= 4,
                "Na profilu nejsou alespoň tři filmy v sekci 'Known for'.");

        // Třetí film
        WebElement fourthMovie = knownForMovies.get(3);
        String movieTitle = fourthMovie.getText().trim();
        System.out.println("Čtvrtý film v sekci 'Known for': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", fourthMovie);
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fourthMovie);

        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


    }










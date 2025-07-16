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
import java.util.List;

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

    @Test
    public void imdbClickWildThenallien() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Vetřelec");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Vetřelec')]")
        ));
        wildLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Vetřelec"));

        // Najdi a klikni na herečku "Reese Witherspoon" na stránce filmu
        WebElement reeseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sigourney Weaver')]")
        ));
        reeseLink.click();

        // Počkej na načtení profilu herečky
        wait.until(ExpectedConditions.titleContains("Sigourney Weaver"));
        Assertions.assertTrue(driver.getTitle().contains("Sigourney Weaver"),
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
    public void imdbClickWildThenallienTom() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Vetřelec");
        searchBox.submit();


        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Vetřelec')]")
        ));
        wildLink.click();


        wait.until(ExpectedConditions.titleContains("Vetřelec"));

        WebElement reeseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Tom Skerritt')]")
        ));
        reeseLink.click();

        // Počkej na načtení profilu herečky
        wait.until(ExpectedConditions.titleContains("Tom Skerritt"));
        Assertions.assertTrue(driver.getTitle().contains("Tom Skerritt"),
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
    public void imdbClickWildThenallienCarmen() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pobřežní hlídka')]")
        ));
        wildLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Pobřežní hlídka"));

        // Najdi a klikni na herečku "Reese Witherspoon" na stránce filmu
        WebElement reeseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pamela Anderson')]")
        ));
        reeseLink.click();

        // Počkej na načtení profilu herečky
        wait.until(ExpectedConditions.titleContains("Pamela Anderson"));
        Assertions.assertTrue(driver.getTitle().contains("Pamela Anderson"),
                "Na profil herečky nebyla načtena správná stránka.");

        WebElement knownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = knownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known for': " + movieTitle);

        // Scroll a klik
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", knownForMovie);
        Thread.sleep(5000);
        knownForMovie.click();

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


  // Test - najít herečku uprostřed pole
    @Test
    public void imdbClickBaywatchThenGenaLeeNolin() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání "Pobřežní hlídka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka");
        searchBox.submit();

        // Kliknutí na film
        WebElement baywatchLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pobřežní hlídka')]")
        ));
        baywatchLink.click();

        // Ověření načtení stránky
        wait.until(ExpectedConditions.titleContains("Pobřežní hlídka"));

        // Najdi herečku "Gena Lee Nolin" přímo na hlavní stránce filmu
        WebElement genaLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Gena Lee Nolin')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", genaLink);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genaLink);

        // Ověření, že se načetl její profil
        wait.until(ExpectedConditions.titleContains("Gena Lee Nolin"));
        Assertions.assertTrue(driver.getTitle().contains("Gena Lee Nolin"),
                "Na profil herečky nebyla načtena správná stránka.");

        // Kliknutí na první film v sekci "Known for"
        WebElement knownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = knownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known for': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", knownForMovie);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }

    // Test s delším čekáním
    @Test
    public void imdbClickAndFindTraciBingham() throws InterruptedException {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Najdi film "Baywatch" (Pobřežní hlídka)
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka");
        searchBox.submit();

        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pobřežní hlídka')]")));
        filmLink.click();

        wait.until(ExpectedConditions.titleContains("Pobřežní hlídka"));

        // 2. Zkus najít Traci Bingham na hlavní stránce
        List<WebElement> traciLinks = driver.findElements(
                By.xpath("//a[contains(text(),'Traci Bingham')]"));

        if (traciLinks.isEmpty()) {
            // 3. Přejdi na "All cast & crew"
            WebElement fullCast = wait.until(ExpectedConditions.elementToBeClickable(
                    By.partialLinkText("All cast & crew")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", fullCast);
            Thread.sleep(300);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCast);

// Opravený title check
            wait.until(ExpectedConditions.titleContains("cast & crew"));
            // 4. Najdi Traci Bingham v seznamu
            traciLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[contains(text(),'Traci Bingham')]")));
        }

        // 5. Klikni na Traci Bingham
        WebElement traci = traciLinks.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", traci);
        Thread.sleep(300);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", traci);

        // 6. Ověření profilu herečky
        wait.until(ExpectedConditions.titleContains("Traci Bingham"));
        Assertions.assertTrue(driver.getTitle().contains("Traci Bingham"),
                "Nepodařilo se načíst profil Traci Bingham");

    }




    // rovnou na All Cast
    @Test
    public void imdbClickBaywatchThenTraciBingham() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání filmu "Pobřežní hlídka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka");
        searchBox.submit();

        // Kliknutí na odkaz filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pobřežní hlídka')]")
        ));
        filmLink.click();

        // Počkáme, až se načte stránka filmu (title obsahuje název)
        wait.until(ExpectedConditions.titleContains("Pobřežní hlídka"));

        // Najdeme odkaz "All cast & crew"
        WebElement fullCastLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.partialLinkText("All cast & crew")
        ));

        // Scroll k odkazu a kliknutí pomocí JS (někdy to funguje spolehlivěji)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fullCastLink);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCastLink);

        // Počkáme, až se načte stránka s kompletním castem (title obsahuje "Full cast & crew")
        wait.until(ExpectedConditions.titleContains("Full cast & crew"));

        // Najdeme herce podle jména "Traci Bingham"
        WebElement actorLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Traci Bingham')]")
        ));

        // Scroll na herečku a kliknutí (opět přes JS, aby se vyřešilo případné překrytí)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorLink);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Počkáme, až se načte profil herečky (title obsahuje její jméno)
        wait.until(ExpectedConditions.titleContains("Traci Bingham"));
        Assertions.assertTrue(driver.getTitle().contains("Traci Bingham"),
                "Na profil herečky nebyla načtena správná stránka.");
    }

    // Test s delším čekáním - stejná jména
    @Test
    public void imdbClickAndFindTraciBingham2() throws InterruptedException {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Najdi film "Baywatch" (Pobřežní hlídka)
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka");
        searchBox.submit();

        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pobřežní hlídka')]")));
        filmLink.click();

        wait.until(ExpectedConditions.titleContains("Pobřežní hlídka"));

        // 2. Zkus najít Traci Bingham na hlavní stránce
        List<WebElement> traciLinks = driver.findElements(
                By.xpath("//a[contains(text(),'Pamela Anderson')]"));

        if (traciLinks.isEmpty()) {
            // 3. Přejdi na "All cast & crew"
            WebElement fullCast = wait.until(ExpectedConditions.elementToBeClickable(
                    By.partialLinkText("All cast & crew")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", fullCast);
            Thread.sleep(300);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCast);

// Opravený title check
            wait.until(ExpectedConditions.titleContains("cast & crew"));
            // 4. Najdi Traci Bingham v seznamu
            traciLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[contains(text(),'Pamela Anderson')]")));
        }

        // 5. Klikni na Traci Bingham
        WebElement traci = traciLinks.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", traci);
        Thread.sleep(300);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", traci);

        // 6. Ověření profilu herečky
        wait.until(ExpectedConditions.titleContains("Pamela Anderson"));
        Assertions.assertTrue(driver.getTitle().contains("Pamela Anderson"),
                "Nepodařilo se načíst profil Pamela Anderson");
        WebElement knownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = knownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known for': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", knownForMovie);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");

    }

    @Test
    public void imdbClickBaywatchThenCarmen() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání filmu "Pobřežní hlídka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka");
        searchBox.submit();

        // Kliknutí na odkaz filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pobřežní hlídka')]")
        ));
        filmLink.click();

        // Počkáme, až se načte stránka filmu (title obsahuje název)
        wait.until(ExpectedConditions.titleContains("Pobřežní hlídka"));

        // Najdeme odkaz "All cast & crew"
        WebElement fullCastLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.partialLinkText("All cast & crew")
        ));

        // Scroll k odkazu a kliknutí pomocí JS (někdy to funguje spolehlivěji)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fullCastLink);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCastLink);

        // Počkáme, až se načte stránka s kompletním castem (title obsahuje "Full cast & crew")
        wait.until(ExpectedConditions.titleContains("Full cast & crew"));

        // Najdeme herce podle jména "Traci Bingham"
        WebElement actorLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Carmen Electra')]")
        ));

        // Scroll na herečku a kliknutí (opět přes JS, aby se vyřešilo případné překrytí)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorLink);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Počkáme, až se načte profil herečky (title obsahuje její jméno)
        wait.until(ExpectedConditions.titleContains("Carmen Electra"));
        Assertions.assertTrue(driver.getTitle().contains("Carmen Electra"),
                "Na profil herečky nebyla načtena správná stránka.");
        WebElement knownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = knownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known for': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", knownForMovie);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


}


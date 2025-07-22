package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;
import java.util.List;

public class ImdbBaywatchserie extends BasedSharedMethods {


    @Test
    public void imdbClickWildThenallienCarmen() {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }



        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 1989");
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);



        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


    // Test - najít herečku uprostřed pole
    @Test
    public void imdbClickBaywatchThenGenaLeeNolin()  {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání "Pobřežní hlídka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 1989");
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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");



    }

    // Test s delším čekáním
    @Test
    public void imdbClickAndFindTraciBingham() {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // 1. Najdi film "Baywatch" (Pobřežní hlídka)
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 1989");
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

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCast);

// Opravený title check
            wait.until(ExpectedConditions.titleContains("cast & crew"));
            // 4. Najdi Traci Bingham v seznamu
            traciLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[contains(text(),'Traci Bingham')]")));


            // 5. Klikni na Traci Bingham
            WebElement traci = traciLinks.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", traci);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", traci);

            // 6. Ověření profilu herečky
            wait.until(ExpectedConditions.titleContains("Traci Bingham"));
            Assertions.assertTrue(driver.getTitle().contains("Traci Bingham"),
                    "Nepodařilo se načíst profil Traci Bingham");
            WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
            ));

// Scroll a klik přes JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


        }

    }
    // rovnou na All Cast
    @Test
    public void imdbClickBaywatchThenTraciBingham()  {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání filmu "Pobřežní hlídka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 1989");
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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCastLink);

        // Počkáme, až se načte stránka s kompletním castem (title obsahuje "Full cast & crew")
        wait.until(ExpectedConditions.titleContains("Full cast & crew"));

        // Najdeme herce podle jména "Traci Bingham"
        WebElement actorLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Traci Bingham')]")
        ));

        // Scroll na herečku a kliknutí (opět přes JS, aby se vyřešilo případné překrytí)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Počkáme, až se načte profil herečky (title obsahuje její jméno)
        wait.until(ExpectedConditions.titleContains("Traci Bingham"));
        Assertions.assertTrue(driver.getTitle().contains("Traci Bingham"),
                "Na profil herečky nebyla načtena správná stránka.");
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

// Scroll a klik přes JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

    }

    // Test s delším čekáním - stejná jména
    @Test
    public void imdbClickAndFindTraciBingham2() throws InterruptedException {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Najdi film "Baywatch" (Pobřežní hlídka)
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 1989");
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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");

        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

// Scroll a klik přes JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }

    @Test
    public void imdbClickBaywatchThenCarmen()  {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání filmu "Pobřežní hlídka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 1989");
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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCastLink);

        // Počkáme, až se načte stránka s kompletním castem (title obsahuje "Full cast & crew")
        wait.until(ExpectedConditions.titleContains("Full cast & crew"));

        // Najdeme herce podle jména "Traci Bingham"
        WebElement actorLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Carmen Electra')]")
        ));

        // Scroll na herečku a kliknutí (opět přes JS, aby se vyřešilo případné překrytí)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorLink);

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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }

    @Test
    public void imdbClickBaywatchThenBrande()  {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání filmu "Pobřežní hlídka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 1989");
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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCastLink);

        // Počkáme, až se načte stránka s kompletním castem (title obsahuje "Full cast & crew")
        wait.until(ExpectedConditions.titleContains("Full cast & crew"));

        // Najdeme herce podle jména "Traci Bingham"
        WebElement actorLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Brande Roderick')]")
        ));

        // Scroll na herečku a kliknutí (opět přes JS, aby se vyřešilo případné překrytí)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Počkáme, až se načte profil herečky (title obsahuje její jméno)
        wait.until(ExpectedConditions.titleContains("Brande Roderick"));
        Assertions.assertTrue(driver.getTitle().contains("Brande Roderick"),
                "Na profil herečky nebyla načtena správná stránka.");
        WebElement knownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = knownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known for': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", knownForMovie);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }


    // rovnou na All Cast
    @Test
    public void imdbClickBaywatchThenKelly() {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Vyhledání filmu "Pobřežní hlídka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 1989");
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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCastLink);

        // Počkáme, až se načte stránka s kompletním castem (title obsahuje "Full cast & crew")
        wait.until(ExpectedConditions.titleContains("Full cast & crew"));

        // Najdeme herce podle jména "Traci Bingham"
        WebElement actorLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Kelly Monaco')]")
        ));

        // Scroll na herečku a kliknutí (opět přes JS, aby se vyřešilo případné překrytí)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Počkáme, až se načte profil herečky (title obsahuje její jméno)
        wait.until(ExpectedConditions.titleContains("Kelly Monaco"));
        Assertions.assertTrue(driver.getTitle().contains("Kelly Monaco"),
                "Na profil herečky nebyla načtena správná stránka.");

    }

    @Test
    public void imdbClickWildThenallien2017() {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pobřežní hlídka 2017");
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
                By.xpath("//a[contains(text(),'Alexandra Daddario')]")
        ));
        reeseLink.click();

        // Počkej na načtení profilu herečky
        wait.until(ExpectedConditions.titleContains("Alexandra Daddario"));
        Assertions.assertTrue(driver.getTitle().contains("Alexandra Daddario"),
                "Na profil herečky nebyla načtena správná stránka.");

        WebElement knownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = knownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known for': " + movieTitle);

        // Scroll a klik
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", knownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);


        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");

    }






        }





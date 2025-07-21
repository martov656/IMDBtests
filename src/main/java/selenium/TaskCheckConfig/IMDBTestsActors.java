package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IMDBTestsActors extends BasedSharedMethods {


    @Test
    public void imdbTestClickFirstKnownForMovieMel() throws InterruptedException {
        String actressName = "Mel Gibson"; //
        driver.get("https://www.imdb.com/");


        // Vyhledávání
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();


        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověření, že jsme na správném profilu
        wait.until(ExpectedConditions.titleContains(actressName));

        // Kliknutí na první film z části "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));

        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


    @Test
    public void imdbTestClickFirstKnownForMovieMichael() throws InterruptedException {
        String actressName = "Michael Douglas"; //
        driver.get("https://www.imdb.com/");


        // Vyhledávání
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověření, že jsme na správném profilu
        wait.until(ExpectedConditions.titleContains(actressName));

        // Kliknutí na první film z části "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));

        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


    @Test
    public void imdbTestClickFirstKnownForMovieClint() throws InterruptedException {
        String actressName = "Clint Eastwood"; //
        driver.get("https://www.imdb.com/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Skryj modální GDPR dialog, pokud je
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledávání
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověření, že jsme na správném profilu
        wait.until(ExpectedConditions.titleContains(actressName));

        // Kliknutí na první film z části "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));

        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        Thread.sleep(1000);
        firstKnownForMovie.click();

        // Ověření, že jsme na stránce filmu
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
    public void imdbTestClickFirstKnownForJohnny() throws InterruptedException {
        String actressName = "Johnny Depp"; //
        driver.get("https://www.imdb.com/");


        // Vyhledávání
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();


        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověření, že jsme na správném profilu
        wait.until(ExpectedConditions.titleContains(actressName));

        // Kliknutí na první film z části "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));

        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }

    @Test
    public void imdbTestClickFirstKnownForMovieAndPlayTrailer() throws InterruptedException {
        String actorName = "Mel Gibson";
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Odstranit GDPR modální dialog (pokud je)
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledávání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actorName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actorName + "')]")
        ));
        profileLink.click();

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains(actorName));

        // Klik na první film v sekci "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");

        // Klik na tlačítko traileru (ikona ▶ nebo text "Play trailer")
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

// Scroll a klik přes JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }

    @Test
    public void imdbTestClickFirstKnownForMovieAndPlayTrailerNichola() throws InterruptedException {
        String actorName = "Sylvester Stallone";
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Odstranit GDPR modální dialog (pokud je)
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledávání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actorName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actorName + "')]")
        ));
        profileLink.click();

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains(actorName));

        // Klik na první film v sekci "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");

        // Klik na tlačítko traileru (ikona ▶ nebo text "Play trailer")
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

// Scroll a klik přes JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }

    @Test
    public void imdbTestClickFirstKnownForMovieAndPlayTrailerCharles() throws InterruptedException {
        String actorName = "Charles Bronson";
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Odstranit GDPR modální dialog (pokud je)
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledávání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actorName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actorName + "')]")
        ));
        profileLink.click();

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains(actorName));

        // Klik na první film v sekci "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");

        // Klik na tlačítko traileru (ikona ▶ nebo text "Play trailer")
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

// Scroll a klik přes JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }

    @Test
    public void imdbTestClickFirstKnownForMovieAndPlayTrailerPaul() throws InterruptedException {
        String actorName = "Paul Newman";
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Odstranit GDPR modální dialog (pokud je)
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledávání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actorName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actorName + "')]")
        ));
        profileLink.click();

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains(actorName));

        // Klik na první film v sekci "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");

        // Klik na tlačítko traileru (ikona ▶ nebo text "Play trailer")
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

// Scroll a klik přes JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }

    @Test
    public void imdbTestClickFirstKnownForMovieAndPlayTrailerRutger() {
        String actorName = "Rutger Hauer";
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Odstranit GDPR modální dialog (pokud je)
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledávání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actorName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actorName + "')]")
        ));
        profileLink.click();

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains(actorName));

        // Klik na první film v sekci "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));
        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");

        // Klik na tlačítko traileru (ikona ▶ nebo text "Play trailer")
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

// Scroll a klik přes JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }

    @Test
    public void imdbTestClickFirstKnownForMovieMichae() throws InterruptedException {
        String actressName = "Michael J. Fox"; //
        driver.get("https://www.imdb.com/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Skryj modální GDPR dialog, pokud je
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledávání
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověření, že jsme na správném profilu
        wait.until(ExpectedConditions.titleContains(actressName));

        // Kliknutí na první film z části "Known For"
        WebElement firstKnownForMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ipc-primary-image-list-card__title")
        ));

        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        // Ověření, že jsme na stránce filmu
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
    public void imdbTestClickAllKnownForMovies() {
        String actressName = "Chuck Norris";
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Přijmout cookies
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledávání herce
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Klik na profil
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověření, že jsme na správném profilu
        wait.until(ExpectedConditions.titleContains(actressName));

        // Načti názvy všech filmů v "Known for" (max 4)
        List<WebElement> knownForElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("a.ipc-primary-image-list-card__title")
        ));

        Assertions.assertTrue(knownForElements.size() >= 4,
                "Na profilu nejsou alespoň 4 filmy v sekci 'Known for'.");

        // Ulož si názvy filmů do seznamu
        List<String> movieTitles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            movieTitles.add(knownForElements.get(i).getText().trim());
        }

        // Smyčka přes 4 filmy
        for (String movieTitle : movieTitles) {
            // Znovu načti profil herečky (při první iteraci už jsme na něm, jinak se vracíme z filmu)
            wait.until(ExpectedConditions.titleContains(actressName));

            // Načti znovu prvky
            List<WebElement> knownFor = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("a.ipc-primary-image-list-card__title")
            ));

            // Najdi konkrétní film podle názvu
            Optional<WebElement> movieElement = knownFor.stream()
                    .filter(el -> el.getText().trim().equalsIgnoreCase(movieTitle))
                    .findFirst();

            Assertions.assertTrue(movieElement.isPresent(), "Film '" + movieTitle + "' nebyl nalezen.");

            // Scroll a klik
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", movieElement.get());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", movieElement.get());

            // Ověření načtení stránky filmu
            wait.until(ExpectedConditions.titleContains(movieTitle));
            Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                    "Načtená stránka neodpovídá filmu '" + movieTitle + "'.");

            System.out.println("Úspěšně zobrazen film: " + movieTitle);

            // Návrat zpět na profil
            driver.navigate().back();

        }

    }

        @Test
        public void imdbTestClickAllKnownForMovie() {
            String actressName = "Sylvester Stallone";
            driver.get("https://www.imdb.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Přijmout cookies
            try {
                WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
                ));
                acceptCookies.click();
            } catch (TimeoutException e) {
                System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
            }

            // Vyhledávání herce
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
            searchBox.clear();
            searchBox.sendKeys(actressName);
            searchBox.submit();

            // Klik na profil
            WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(),'" + actressName + "')]")
            ));
            profileLink.click();

            // Ověření, že jsme na správném profilu
            wait.until(ExpectedConditions.titleContains(actressName));

            // Načti názvy všech filmů v "Known for" (max 4)
            List<WebElement> knownForElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("a.ipc-primary-image-list-card__title")
            ));

            Assertions.assertTrue(knownForElements.size() >= 4,
                    "Na profilu nejsou alespoň 4 filmy v sekci 'Known for'.");

            // Ulož si názvy filmů do seznamu
            List<String> movieTitles = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                movieTitles.add(knownForElements.get(i).getText().trim());
            }

            // Smyčka přes 4 filmy
            for (String movieTitle : movieTitles) {
                // Znovu načti profil herečky (při první iteraci už jsme na něm, jinak se vracíme z filmu)
                wait.until(ExpectedConditions.titleContains(actressName));

                // Načti znovu prvky
                List<WebElement> knownFor = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("a.ipc-primary-image-list-card__title")
                ));

                // Najdi konkrétní film podle názvu
                Optional<WebElement> movieElement = knownFor.stream()
                        .filter(el -> el.getText().trim().equalsIgnoreCase(movieTitle))
                        .findFirst();

                Assertions.assertTrue(movieElement.isPresent(), "Film '" + movieTitle + "' nebyl nalezen.");

                // Scroll a klik
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", movieElement.get());
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", movieElement.get());

                // Ověření načtení stránky filmu
                wait.until(ExpectedConditions.titleContains(movieTitle));
                Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                        "Načtená stránka neodpovídá filmu '" + movieTitle + "'.");

                System.out.println("Úspěšně zobrazen film: " + movieTitle);

                // Návrat zpět na profil
                driver.navigate().back();

            }

    }


}





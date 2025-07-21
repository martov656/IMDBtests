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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseImdbTests extends BasedSharedMethods {


    // vyhledá čtcrtý film sekce KnownFor
    @Test
    public void imdbTestClickFirstKnownForMovieDeborah2() {
        String actressName = "Deborah Ann Woll"; // Nebo "Kate Beckinsale"
        driver.get("https://www.imdb.com/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }


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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fourthMovie);

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

    // vyhledá třetí film sekce KnownFor
    @Test
    public void imdbTestClickFirstKnownForMovieDeborah3() {
        String actressName = "Deborah Ann Woll"; // Nebo "Kate Beckinsale"
        driver.get("https://www.imdb.com/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }


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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", thirdMovie);

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

    // vyhledá druhý film sekce KnownFor
    @Test
    public void imdbTestClickFirstKnownForMovieDeborah4() {
        String actressName = "Deborah Ann Woll"; // Nebo "Kate Beckinsale"
        driver.get("https://www.imdb.com/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }


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
        List<WebElement> knownForMovies = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("a.ipc-primary-image-list-card__title")
        ));

        Assertions.assertTrue(knownForMovies.size() >= 2,
                "Na profilu nejsou alespoň tři filmy v sekci 'Known for'.");

        // Třetí film
        WebElement secondMovie = knownForMovies.get(1);
        String movieTitle = secondMovie.getText().trim();
        System.out.println("Druhý film v sekci 'Known for': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", secondMovie);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondMovie);

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

    // vyhledá druhý film sekce KnownFor
    @Test
    public void imdbTestClickFirstKnownForMovieDeborah5() {
        String actressName = "Deborah Ann Woll"; // Nebo "Kate Beckinsale"
        driver.get("https://www.imdb.com/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }


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
        List<WebElement> knownForMovies = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("a.ipc-primary-image-list-card__title")
        ));

        Assertions.assertTrue(knownForMovies.size() >= 1,
                "Na profilu nejsou alespoň tři filmy v sekci 'Known for'.");

        // První film
        WebElement firstMovie = knownForMovies.get(0);
        String movieTitle = firstMovie.getText().trim();
        System.out.println("Druhý film v sekci 'Known for': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstMovie);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstMovie);

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

    // vyhledá všechny filmy v sekci KnownFor
    @Test
    public void imdbTestClickAllKnownForMovies() {
        String actressName = "Deborah Ann Woll";
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

        // Vyhledávání herečky
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

            // Návrat zpět na profil herečky
            driver.navigate().back();
        }
    }

    // Test - najít herečku uprostřed pole
    @Test
    public void imdbClickEmilyBlunt() {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Přijmout cookies
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání "Soukromá válka pana Wilsona"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Soukromá válka pana Wilsona");
        searchBox.submit();

        // Kliknutí na film
        WebElement baywatchLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Soukromá válka pana Wilsona')]")
        ));
        baywatchLink.click();

        // Ověření načtení stránky
        wait.until(ExpectedConditions.titleContains("Soukromá válka pana Wilsona"));

        // Najdi herečku "Emily Blunt" přímo na hlavní stránce filmu
        WebElement genaLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Emily Blunt')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", genaLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genaLink);

        // Ověření, že se načetl její profil
        wait.until(ExpectedConditions.titleContains("Emily Blunt"));
        Assertions.assertTrue(driver.getTitle().contains("Emily Blunt"),
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


    @Test
    public void imdbClickAndFindVic() {
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

        // 1. Najdi film "(K)lamač srdcí"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("(K)lamač srdcí");
        searchBox.submit();

        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'(K)lamač srdcí')]")));
        filmLink.click();

        wait.until(ExpectedConditions.titleContains("(K)lamač srdcí"));

        // 2. Zkus najít Victoria Silvstedt na hlavní stránce
        List<WebElement> traciLinks = driver.findElements(
                By.xpath("//a[contains(text(),'Victoria Silvstedt')]"));

        if (traciLinks.isEmpty()) {
            // 3. Přejdi na "All cast & crew"
            WebElement fullCast = wait.until(ExpectedConditions.elementToBeClickable(
                    By.partialLinkText("All cast & crew")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", fullCast);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCast);

// Opravený title check
            wait.until(ExpectedConditions.titleContains("cast & crew"));
            // 4. Najdi Victoria Silvstedt v seznamu
            traciLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[contains(text(),'Victoria Silvstedt')]")));


            // 5. Klikni na Victoria Silvstedt
            WebElement traci = traciLinks.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", traci);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", traci);

            // 6. Ověření profilu herečky
            wait.until(ExpectedConditions.titleContains("Victoria Silvstedt"));
            Assertions.assertTrue(driver.getTitle().contains("Victoria Silvstedt"),
                    "Nepodařilo se načíst profil Victoria Silvstedt");


        }

    }

    @Test
    public void imdbTestClickFirstKnownForMovieAndPlayTrailer() {
        String actorName = "Mel Gibson";
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

        // Vyhledávání herce
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actorName);
        searchBox.submit();

        // Klik na profil herce
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
    public void imdbClickWildThenMotel3() {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", acceptCookies);
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Do vyhledávání napiš "Návrat do budoucnosti"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", searchBox);
        searchBox.clear();
        searchBox.sendKeys("Návrat do budoucnosti");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Návrat do budoucnosti')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Návrat do budoucnosti"));

        // Najdi trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

        // Návrat zpět na stránku filmu
        driver.navigate().back();

        // Klikni na herce "Michael J. Fox"
        WebElement actorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Michael J. Fox')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Michael J. Fox"));
        Assertions.assertTrue(driver.getTitle().contains("Michael J. Fox"),
                "Na profil herce nebyla načtena správná stránka.");
    }

    @Test
    public void imdbClick() {
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

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", searchBox);
        searchBox.clear();
        searchBox.sendKeys("Šílený Max 2: Bojovník silnic");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Šílený Max 2: Bojovník silnic')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Šílený Max"));

        // Najdi trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

        // Návrat zpět na stránku filmu
        driver.navigate().back();

        // Klikni na herce "Michael J. Fox"
        WebElement actorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Mel Gibson')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Mel Gibson"));
        Assertions.assertTrue(driver.getTitle().contains("Mel Gibson"),
                "Na profil herce nebyla načtena správná stránka.");

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
            wait.until(ExpectedConditions.titleContains("Mel Gibson"));

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

            driver.navigate().back();

        }


    }

}








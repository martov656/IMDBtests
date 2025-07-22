package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImdbTestFilms extends BasedSharedMethods {



    @Test
    public void imdbClickWildThenallien() {
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
        searchBox.sendKeys("Vetřelec 1979");
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);


        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }

    @Test
    public void imdbClickWildThenallienTom() {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Vetřelec 1979");
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", knownForMovie);

        // Ověření titulku stránky
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }


    @Test
    public void imdbClickAndFindTraciVic()  {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Najdi film "Baywatch" (Pobřežní hlídka)
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Šílenci na prknech");
        searchBox.submit();

        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Šílenci na prknech')]")));
        filmLink.click();

        wait.until(ExpectedConditions.titleContains("Šílenci na prknech"));

        // 2. Zkus najít Traci Bingham na hlavní stránce
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
            // 4. Najdi Traci Bingham v seznamu
            traciLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[contains(text(),'Victoria Silvstedt')]")));
        }

        // 5. Klikni na Traci Bingham
        WebElement traci = traciLinks.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", traci);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", traci);

        // 6. Ověření profilu herečky
        wait.until(ExpectedConditions.titleContains("Victoria Silvstedt"));
        Assertions.assertTrue(driver.getTitle().contains("Victoria Silvstedt"),
                "Nepodařilo se načíst profil Victoria Silvstedt");
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
    public void imdbClickAndFindTraciVi2()  {
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
        searchBox.sendKeys("Plnou parou vzad");
        searchBox.submit();

        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Plnou parou vzad')]")));
        filmLink.click();

        wait.until(ExpectedConditions.titleContains("Plnou parou vzad"));

        // 2. Zkus najít Traci Bingham na hlavní stránce
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
            // 4. Najdi Traci Bingham v seznamu
            traciLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[contains(text(),'Victoria Silvstedt')]")));
        }

        // 5. Klikni na Traci Bingham
        WebElement traci = traciLinks.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", traci);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", traci);

        // 6. Ověření profilu herečky
        wait.until(ExpectedConditions.titleContains("Victoria Silvstedt"));
        Assertions.assertTrue(driver.getTitle().contains("Victoria Silvstedt"),
                "Nepodařilo se načíst profil Victoria Silvstedt");
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
    public void imdbClickWildThendared() {
        driver.get("https://www.imdb.com/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Do vyhledávání napiš "Wild"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Daredevil");
        searchBox.submit();

        // Klikni na film "Wild"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Daredevil')]")
        ));
        wildLink.click();

        // Počkej, až se načte stránka filmu (titul by měl obsahovat "Wild")
        wait.until(ExpectedConditions.titleContains("Daredevil"));

        // Najdi a klikni na herečku "Reese Witherspoon" na stránce filmu
        WebElement reeseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Deborah Ann Woll')]")
        ));
        reeseLink.click();

        // Počkej na načtení profilu herečky
        wait.until(ExpectedConditions.titleContains("Deborah Ann Woll"));
        Assertions.assertTrue(driver.getTitle().contains("Deborah Ann Woll"),
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




    @Test
    public void openFirstKnownForMovieSafely()  {
        String actressName = "Jennifer Aniston";
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

        // Skryj GDPR dialog
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Klik na profil
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        wait.until(ExpectedConditions.titleContains(actressName));

        // Pokus o získání filmu ze sekce Known For
        WebElement firstKnownForMovie = null;
        try {
            firstKnownForMovie = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".ipc-primary-image-list-card__title")
            ));
        } catch (TimeoutException e) {
            System.out.println("Žádný film v sekci 'Known for' nebyl nalezen.");
        }

        // Ověření, že jsme něco našli
        Assertions.assertNotNull(firstKnownForMovie, "'Known For' sekce je prázdná nebo nebyla načtena správně.");

        // Scroll a klik
        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstKnownForMovie);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);

        // Ověření stránky filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()));
    }

    @Test
    public void openKnownForMovieWithSleepHandled() {
        String actressName = "Jennifer Aniston";
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // GDPR banner
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();
        wait.until(ExpectedConditions.titleContains(actressName));

        WebElement firstKnownForMovie = null;
        try {
            firstKnownForMovie = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".ipc-primary-image-list-card__title")
            ));
        } catch (TimeoutException e) {
            System.out.println("Žádný film v sekci 'Known for' nebyl nalezen.");
        }

        Assertions.assertNotNull(firstKnownForMovie, "Sekce 'Known for' je prázdná nebo nebyla načtena správně.");

        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstKnownForMovie);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);


        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()));

        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

    }

    @Test
    public void openPreviousMovieRobust()  {
        String actressName = "Dakota Johnson";


        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Přijmout cookies, pokud jsou
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Odebrat GDPR dialog, pokud existuje
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledat herečku
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Kliknout na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověřit, že jsme na stránce herečky
        wait.until(ExpectedConditions.titleContains(actressName));
        Assertions.assertTrue(driver.getTitle().contains(actressName));

        // Najít a rozbalit sekci "Previous" (předchozí projekty)


        // Najít odkaz na požadovaný film
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

// Scroll a klik přes JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

    }

    @Test
    public void openShowFromJohnsonProfile2() {
        String actressName = "Dakota Johnson";
        String showTitle = "Dokonalá shoda";

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


        // Skryj GDPR dialog
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověř, že jsme na stránce herečky
        wait.until(ExpectedConditions.titleContains(actressName));
        Assertions.assertTrue(driver.getTitle().contains(actressName));

        // Najdi odkaz na "The Morning Show" – hledání podle částečného textu (větší šance úspěchu)
        WebElement showLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.partialLinkText("Dokonalá shoda")
        ));

        // Scroll na rodičovský element, aby se lazy-load načetl správně
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", showLink);


        // Klik přes JavaScript (lepší proti překrytým prvkům)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", showLink);

        // Ověření, že jsme na stránce seriálu
        wait.until(ExpectedConditions.titleContains(showTitle));
        Assertions.assertTrue(driver.getTitle().contains(showTitle), "Nebyla načtena stránka pro U tebe nebo u mě?");

        // Trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

    }

    @Test
    public void imdbClickWildThenallienTom2() {
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
    public void imdbClickWildThenallien3() {
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
        searchBox.sendKeys("Vetřelec 1979");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Vetřelec')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Vetřelec"));

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
                By.xpath("//a[contains(text(),'Tom Skerritt')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Tom Skerritt"));
        Assertions.assertTrue(driver.getTitle().contains("Tom Skerritt"),
                "Na profil herce nebyla načtena správná stránka.");


    }

    @Test
    public void imdbClickMadMax() {
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
        searchBox.sendKeys("Šílený Max 1979");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Šílený Max')]")
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

            // Návrat zpět na profil herečky
            driver.navigate().back();
        }

    }


    @Test
    public void openFirstKnownForMovieSafely2() {
        String actressName = "Jennifer Aniston";
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

        // Skryj GDPR dialog
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Klik na profil
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        wait.until(ExpectedConditions.titleContains(actressName));

        // Pokus o získání filmu ze sekce Known For
        WebElement firstKnownForMovie = null;
        try {
            firstKnownForMovie = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".ipc-primary-image-list-card__title")
            ));
        } catch (TimeoutException e) {
            System.out.println("Žádný film v sekci 'Known for' nebyl nalezen.");
        }

        // Ověření, že jsme něco našli
        Assertions.assertNotNull(firstKnownForMovie, "'Known For' sekce je prázdná nebo nebyla načtena správně.");

        // Scroll a klik
        String movieTitle = firstKnownForMovie.getText().trim();
        System.out.println("První film v sekci 'Known For': " + movieTitle);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstKnownForMovie);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstKnownForMovie);

        // Ověření stránky filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(movieTitle.toLowerCase()));
    }

    // Hledání filmu na hlavní straně
    @Test
    public void openShowFromJohnsonProfile3() {
        String actressName = "Jennifer Connelly";
        String showTitle = "The Dilemma";

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


        // Skryj GDPR dialog
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Klik na profil herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověř, že jsme na stránce herečky
        wait.until(ExpectedConditions.titleContains(actressName));
        Assertions.assertTrue(driver.getTitle().contains(actressName));

        // Najdi odkaz na "The Morning Show" – hledání podle částečného textu (větší šance úspěchu)
        WebElement showLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.partialLinkText("The Dilemma")
        ));

        // Scroll na rodičovský element, aby se lazy-load načetl správně
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", showLink);


        // Klik přes JavaScript (lepší proti překrytým prvkům)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", showLink);

        // Ověření, že jsme na stránce seriálu
        wait.until(ExpectedConditions.titleContains(showTitle));
        Assertions.assertTrue(driver.getTitle().contains(showTitle), "Nebyla načtena stránka pro Je prostě báječná");

        // Trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }

}



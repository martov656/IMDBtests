package selenium.POM.Pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImdbTestsDemos extends BaseTest {

    @Test
    public void openImdbHomePage() {
        driver.get("https://www.imdb.com/");
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains("imdb"), "IMDb stránka nebyla načtena.");
    }


    @Test
    public void openImdbHomePage2() throws InterruptedException {
        String actressName = "Jennifer Aniston"; // Nebo "Kate Beckinsale"
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
        Thread.sleep(500);
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
        Thread.sleep(4000);


    }


    @Test
    public void openTheMorningShowAndPlayTrailer() throws InterruptedException {
        String movieTitle = "The Morning Show";
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

        // GDPR dialog
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledávání
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(movieTitle);
        searchBox.submit();

        // Klik na odkaz s přesným názvem
        WebElement resultLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., '" + movieTitle + "')]")
        ));
        resultLink.click();

        // Ověření, že jsme na stránce seriálu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().contains("The Morning Show"));

        // Najdi a klikni na trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);
        Thread.sleep(4000);

    }


    @Test
    public void openMorningShowFromAnistonProfile() throws InterruptedException {
        String actressName = "Jennifer Aniston";
        String showTitle = "Murder Mystery 2";

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
                By.partialLinkText("Murder Mystery 2")
        ));

        // Scroll na rodičovský element, aby se lazy-load načetl správně
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", showLink);

        // Pár ms počkej, než stránka doroluje
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Klik přes JavaScript (lepší proti překrytým prvkům)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", showLink);

        // Ověření, že jsme na stránce seriálu
        wait.until(ExpectedConditions.titleContains(showTitle));
        Assertions.assertTrue(driver.getTitle().contains(showTitle), "Nebyla načtena stránka pro The Morning Show");

        // Trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);
        Thread.sleep(4000);

    }

    @Test
    public void openMorningShowFromAnistonProfile2() throws InterruptedException {
        String actressName = "Reese Witherspoon";
        String showTitle = "You're Cordially Invited";

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
                By.partialLinkText("You're Cordially Invited")
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
        Thread.sleep(4000);


    }

    @Test
    public void openPreviousMovieRobust() throws InterruptedException {
        String actressName = "Reese Witherspoon";


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
        Thread.sleep(4000);


    }

    @Test
    public void imdbClickWildThenMotel3() throws InterruptedException {
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
        Thread.sleep(4000);


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
    public void imdbClickBigLittle() throws InterruptedException {
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
        searchBox.sendKeys("Sedmilhářky");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sedmilhářky')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Sedmilhářky"));

        // Najdi trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);
        Thread.sleep(4000);


        // Návrat zpět na stránku filmu
        driver.navigate().back();

        // Klikni na herce "Michael J. Fox"
        WebElement actorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Reese Witherspoon')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getTitle().contains("Reese Witherspoon"),
                "Na profil herce nebyla načtena správná stránka.");

    }

    @Test
    public void imdbClickAllien3() throws InterruptedException {
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
        searchBox.sendKeys("Vetřelec 3");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Vetřelec 3')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Vetřelec 3"));

        // Najdi trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);
        Thread.sleep(4000);

        // Návrat zpět na stránku filmu
        driver.navigate().back();

        // Klikni na herce "Michael J. Fox"
        WebElement actorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sigourney Weaver')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Sigourney Weaver"));
        Assertions.assertTrue(driver.getTitle().contains("Sigourney Weaver"),
                "Na profil herce nebyla načtena správná stránka.");

    }

    @Test
    public void imdbClickAllien4() throws InterruptedException {
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
        searchBox.sendKeys("Vetřelec 3");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Vetřelec 3')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Vetřelec 3"));

        // Klikni na herce "Michael J. Fox"
        WebElement actorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Sigourney Weaver')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Sigourney Weaver"));
        Assertions.assertTrue(driver.getTitle().contains("Sigourney Weaver"),
                "Na profil herce nebyla načtena správná stránka.");

        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);
        Thread.sleep(4000);


    }

    @Test
    public void imdbClickDare() throws InterruptedException {
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
        searchBox.sendKeys("Daredevil");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Daredevil')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Daredevil"));

        // Klikni na herce "Michael J. Fox"
        WebElement actorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Charlie Cox')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Charlie Cox"));
        Assertions.assertTrue(driver.getTitle().contains("Charlie Cox"),
                "Na profil herce nebyla načtena správná stránka.");

        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);
        Thread.sleep(4000);


    }

    // Jde hned na All Cast
    @Test
    public void imdbClickAnnieHall() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner už byl potvrzen nebo se nezobrazil.");
        }
        // Vyhledání filmu
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Annie Hallová");
        searchBox.submit();

        // Kliknutí na odkaz filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Annie Hallová')]")
        ));
        filmLink.click();

        // Počkáme, až se načte stránka filmu (title obsahuje název)
        wait.until(ExpectedConditions.titleContains("Annie Hallová"));

        // Najdeme odkaz "All cast & crew"
        WebElement fullCastLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.partialLinkText("All cast & crew")
        ));

        // Scroll k odkazu a kliknutí pomocí JS (někdy to funguje spolehlivěji)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fullCastLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullCastLink);

        // Počkáme, až se načte stránka s kompletním castem (title obsahuje "Full cast & crew")
        wait.until(ExpectedConditions.titleContains("Full cast & crew"));
        Thread.sleep(4000);
        // Najdeme herce podle jména
        WebElement actorLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(),'Sigourney Weaver')]")
        ));

        // Scroll na herečku a kliknutí (opět přes JS, aby se vyřešilo případné překrytí)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorLink);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Počkáme, až se načte profil herečky (title obsahuje její jméno)
        wait.until(ExpectedConditions.titleContains("Sigourney Weaver"));
        Assertions.assertTrue(driver.getTitle().contains("Sigourney Weaver"),
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
        Thread.sleep(4000);

    }

    @Test
    public void imdbClick() throws InterruptedException {
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

        // Klikni na film
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
        Thread.sleep(4000);
        // Návrat zpět na stránku filmu
        driver.navigate().back();

        // Klikni na herce
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

            // Návrat zpět na profil
            driver.navigate().back();
        }


    }
}
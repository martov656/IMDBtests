package selenium.POM.Pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ImdbTests2 extends BaseTest {

    @Test
    public void openImdbHomePage() {
        driver.get("https://www.imdb.com/");
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains("imdb"), "IMDb stránka nebyla načtena.");
    }


    @Test
    public void openImdbHomePage2() throws InterruptedException {
        String actressName = "Jennifer Aniston"; // Nebo "Kate Beckinsale"
        driver.get("https://www.imdb.com/");


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

    }


    @Test
    public void openTheMorningShowAndPlayTrailer() {
        String movieTitle = "The Morning Show";
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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
        Assertions.assertTrue(driver.getTitle().contains(showTitle), "Nebyla načtena stránka pro U tebe nebo u mě?");

        // Trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

    }

    @Test
    public void openPreviousMovieRobust() throws InterruptedException {
        String actressName = "Reese Witherspoon";
        String movieTitle = "The Morning Show";

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
        WebElement accordionLabel = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("label[data-testid='accordion-item-actress-previous-projects']")
        ));

        // Kliknout na rozbalení, pokud není otevřená
        if (!"true".equals(accordionLabel.getAttribute("aria-expanded"))) {
            accordionLabel.click();
            Thread.sleep(1000);
        }

        WebElement container = driver.findElement(By.id("accordion-item-actress-previous-projects"));

        // Scrollovat v containeru postupně dolů, aby se načetly všechny položky (lazy loading)
        long lastHeight = (long)((JavascriptExecutor)driver).executeScript("return arguments[0].scrollHeight;", container);
        while (true) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight);", container);
            Thread.sleep(1000); // Počkat načtení
            long newHeight = (long)((JavascriptExecutor)driver).executeScript("return arguments[0].scrollHeight;", container);
            if (newHeight == lastHeight) {
                break; // Už nic dalšího se nenačetlo
            }
            lastHeight = newHeight;
        }

        // Najít odkaz na požadovaný film
        WebElement movieLink = container.findElement(By.xpath(".//a[contains(text(),'" + movieTitle + "')]"));

        // Scrollnout na odkaz a kliknout přes JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", movieLink);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", movieLink);

        // Ověřit načtení stránky filmu
        wait.until(ExpectedConditions.titleContains(movieTitle));
        Assertions.assertTrue(driver.getTitle().contains(movieTitle));
    }


    }






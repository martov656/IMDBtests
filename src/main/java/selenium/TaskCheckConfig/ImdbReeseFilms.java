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

public class ImdbReeseFilms extends BasedSharedMethods {


    @Test
    public void imdbWatchTrailerThenGoBackAndClickActor() throws InterruptedException {
        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Divočina");
        searchBox.submit();

        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Divočina')]")
        ));
        wildLink.click();

        wait.until(ExpectedConditions.titleContains("Divočina"));

        // Spuštění traileru
        WebElement trailerLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

        // Počkej chvíli, např. 3 sekundy pro efekt (nečekáš na konkrétní stav přehrávače)
        Thread.sleep(3000);

        // Zpět na stránku filmu
        driver.navigate().back();
        wait.until(ExpectedConditions.titleContains("Divočina"));

        // Klik na herečku
        WebElement reeseLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Reese Witherspoon')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reeseLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reeseLink);

        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getTitle().contains("Reese Witherspoon"),
                "Na profil herečky nebyla načtena správná stránka.");
    }


    @Test
    public void imdbTestClickAllKnownForMoviesReese() {
        String actressName = "Reese Witherspoon";
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

    //Film - Trailer - Herečka
    @Test
    public void imdbClicReese3() {
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

        // Do vyhledávání napiš "Pravá blondýnka"
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", searchBox);
        searchBox.clear();
        searchBox.sendKeys("Pravá blondýnka");
        searchBox.submit();

        // Klikni na film "Pravá blondýnka"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pravá blondýnka')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Pravá blondýnka"));

        // Najdi trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);

        // Návrat zpět na stránku filmu
        driver.navigate().back();

        // Klikni na Reese Witherspoon
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
    public void imdbClickWildThenMotel4() {
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
        searchBox.sendKeys("Walk the Line");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Walk the Line')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Walk the Line"));

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
                By.xpath("//a[contains(text(),'Reese Witherspoon')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getTitle().contains("Reese Witherspoon"),
                "Na profil herce nebyla načtena správná stránka.");
    }

    //Film - Herečka
    @Test
    public void imdbClickReese() {
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
        searchBox.sendKeys("Pravá blondýnka");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Pravá blondýnka')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Pravá blondýnka"));


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
        searchBox.sendKeys("Tohle je válka!");
        searchBox.submit();

        // Klikni na film "Návrat do budoucnosti"
        WebElement wildLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Tohle je válka!')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", wildLink);
        wildLink.click();

        // Počkej, až se načte stránka filmu
        wait.until(ExpectedConditions.titleContains("Tohle je válka!"));

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
                By.xpath("//a[contains(text(),'Reese Witherspoon')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", actorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actorLink);

        // Ověření profilu
        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getTitle().contains("Reese Witherspoon"),
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
            wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));

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

    // Hledání Filmu na hlavní straně
    @Test
    public void openShowFromJohnsonProfile2() {
        String actressName = "Reese Witherspoon";
        String showTitle = "The Last Thing He Told Me";

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
                By.partialLinkText("The Last Thing He Told Me")
        ));

        // Scroll na rodičovský element, aby se lazy-load načetl správně
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", showLink);


        // Klik přes JavaScript (lepší proti překrytým prvkům)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", showLink);

        // Ověření, že jsme na stránce seriálu
        wait.until(ExpectedConditions.titleContains(showTitle));
        Assertions.assertTrue(driver.getTitle().contains(showTitle), "Nebyla načtena stránka pro The Last Thing He Told Me");

        // Trailer
        WebElement trailerLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.ipc-lockup-overlay[aria-label^='Watch']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trailerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", trailerLink);


    }

  // Najde film v sekci "Actress" a oddílu "Previous"
    @Test
    public void openPreviousMovieFromActressProfile3() throws InterruptedException {
        String actressName = "Reese Witherspoon";
        String movieTitle = "Sami v pousti"; // bez diakritiky

        driver.get("https://www.imdb.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Přijmi cookies
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies banner už byl potvrzen nebo se nezobrazil.");
        }

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

        // Ověření, že jsme na profilu herečky
        wait.until(ExpectedConditions.titleContains(actressName));
        Assertions.assertTrue(driver.getTitle().contains(actressName));

        // Scroll + klik na sekci "Previous"
        WebElement previousSection = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("label[data-testid='accordion-item-actress-previous-projects']"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", previousSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", previousSection);
        Thread.sleep(1000); // pomůže načíst obsah accordionu

        // Najdi film podle názvu
        List<WebElement> films = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//ul[contains(@class, 'ipc-metadata-list')]/li//a")
        ));

        System.out.println("Nalezené filmy:");
        for (WebElement film : films) {
            System.out.println(film.getText());
        }
        // Vyhledej film podle názvu bez diakritiky
        WebElement targetFilm = null;
        for (WebElement film : films) {
            String title = film.getText().toLowerCase().replaceAll("[áéěíóúůýžčřďťň]", "");
            if (title.contains("sami v pousti")) {
                targetFilm = film;
                break;
            }
        }

        Assertions.assertNotNull(targetFilm, "Film 'Sami v pousti' nebyl nalezen.");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetFilm);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetFilm);

        // Ověř název v titulku stránky
        wait.until(ExpectedConditions.titleContains("Sami v pousti"));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains("sami v pousti"));
    }

}






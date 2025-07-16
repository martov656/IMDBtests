package selenium.Functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;

public class ImdbKnownForTest extends BasedSharedMethods {



    @Test
    public void imdbSearchTestReese() throws InterruptedException {
        driver.get("https://www.imdb.com/");
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript(
                "const el = document.querySelector('div[role=dialog], iframe'); if(el) el.style.display='none';"
        );
        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Keira Knightly");
        Thread.sleep(5000);
        searchBox.submit();
        Thread.sleep(5000);
        // počkej, až se výsledky načtou (hledání přes selector nebo text)
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Keira Knightly')]")
        ));

        Assertions.assertTrue(result.isDisplayed(), "Keira Knightly  nebyla nalezena ve výsledcích hledání.");
    }


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
    public void imdbSearchTestSuperman() {
        driver.get("https://www.imdb.com/");

        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Superman");
        searchBox.submit();

        // počkej, až se výsledky načtou (hledání přes selector nebo text)
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Superman')]")
        ));

        Assertions.assertTrue(result.isDisplayed(), "Superman nebyl nalezen ve výsledcích hledání.");
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
        Thread.sleep(5000);
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
    public void imdbSearchTestKate() throws InterruptedException {
        driver.get("https://www.imdb.com/");
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript(
                "const el = document.querySelector('div[role=dialog], iframe'); if(el) el.style.display='none';"
        );
        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Kate Beckinsale");
        Thread.sleep(5000);
        searchBox.submit();
        Thread.sleep(5000);
        // počkej, až se výsledky načtou (hledání přes selector nebo text)
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Kate Beckinsale')]")
        ));

        Assertions.assertTrue(result.isDisplayed(), "Kate Beckinsale nebyla nalezena ve výsledcích hledání.");
    }

    @Test
    public void imdbSearchTestKateProfileClick() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        // Odstraní modální dialog, pokud se objeví (např. GDPR, cookies)
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript(
                "const el = document.querySelector('div[role=dialog], iframe'); if(el) el.style.display='none';"
        );

        // Najdi vyhledávací pole a zadej hledaný výraz
        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Kate Beckinsale");
        Thread.sleep(5000);
        searchBox.submit();
        Thread.sleep(5000);
        // Počkej, než se zobrazí odkaz s herečkou
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Kate Beckinsale')]")
        ));

        Assertions.assertTrue(result.isDisplayed(), "Kate Beckinsale nebyla nalezena ve výsledcích hledání.");

        // Klikni na odkaz
        result.click();
        Thread.sleep(5000);
        // Počkej, až se načte profil herečky
        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
        Thread.sleep(5000);
        // Ověř, že stránka profilu obsahuje jméno herečky
        Assertions.assertTrue(driver.getPageSource().contains("Kate Beckinsale"),
                "Stránka profilu herečky nebyla správně načtena.");
    }

    @Test
    public void imdbSearchTestKateProfileNoThreads() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        // Odstraní modální dialog, pokud se objeví (např. GDPR, cookies)

        ((JavascriptExecutor) driver).executeScript(
                "const el = document.querySelector('div[role=dialog], iframe'); if(el) el.style.display='none';"
        );

        // Najdi vyhledávací pole a zadej hledaný výraz
        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Kate Beckinsale");
        searchBox.submit();

        // Počkej, než se zobrazí odkaz s herečkou
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Kate Beckinsale')]")
        ));

        Assertions.assertTrue(result.isDisplayed(), "Kate Beckinsale nebyla nalezena ve výsledcích hledání.");

        // Klikni na odkaz
        result.click();

        // Počkej, až se načte profil herečky
        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));

        // Ověř, že stránka profilu obsahuje jméno herečky
        Assertions.assertTrue(driver.getPageSource().contains("Kate Beckinsale"),
                "Stránka profilu herečky nebyla správně načtena.");
    }

    @Test
    public void imdbSearchTestKateProfileMetaKnownFor() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        Thread.sleep(4000);
        ((JavascriptExecutor) driver).executeScript(
                "const el = document.querySelector('div[role=dialog], iframe'); if(el) el.style.display='none';"
        );

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Kate Beckinsale");
        searchBox.submit();

        WebElement resultLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Kate Beckinsale')]")
        ));
        resultLink.click();

        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));

        // Získání obsahu meta tagu twitter:description
        WebElement metaTag = driver.findElement(By.cssSelector("meta[property='twitter:description']"));
        String metaContent = metaTag.getAttribute("content");
        System.out.println("Meta twitter:description: " + metaContent);

        Assertions.assertTrue(metaContent.contains("Love & Friendship"), "Meta tag neobsahuje očekávaný film.");
        Assertions.assertTrue(metaContent.contains("Underworld"), "Meta tag neobsahuje očekávaný film.");
    }

    @Test
    public void imdbSearchTesReeseProfileMetaKnownFor() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        Thread.sleep(4000);
        ((JavascriptExecutor) driver).executeScript(
                "const el = document.querySelector('div[role=dialog], iframe'); if(el) el.style.display='none';"
        );

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Reese Witherspoon");
        searchBox.submit();

        WebElement resultLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Reese Witherspoon')]")
        ));
        resultLink.click();

        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));

        // Získání obsahu meta tagu twitter:description
        WebElement metaTag = driver.findElement(By.cssSelector("meta[property='twitter:description']"));
        String metaContent = metaTag.getAttribute("content");
        System.out.println("Meta twitter:description: " + metaContent);

        Assertions.assertTrue(metaContent.contains("Walk the Line"), "Meta tag neobsahuje očekávaný film.");
        Assertions.assertTrue(metaContent.contains("Pravá blondýnka"), "Meta tag neobsahuje očekávaný film.");
    }


    @Test
    public void celebSearchTestScarlett() {
        driver.get("https://imdb.com/");

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Scarlett Johansson");
        searchBox.sendKeys(Keys.ENTER); // simuluje odeslání enterem

        // Počkej na výsledek, např. nadpis výsledku nebo obrázek herečky
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(., 'Scarlett Johansson')]")
        ));
        Assertions.assertTrue(result.isDisplayed());


        Assertions.assertTrue(driver.getPageSource().contains("Scarlett Johansson"), "Searched name not found in page source.");
    }

    @Test
    public void imdbSearchTestKateProfileAndClickMovie() throws InterruptedException {
        driver.get("https://www.imdb.com/");

        // Skrytí modálního okna
        Thread.sleep(4000);
        ((JavascriptExecutor) driver).executeScript(
                "const el = document.querySelector('div[role=dialog], iframe'); if(el) el.style.display='none';"
        );

        // Hledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Kate Beckinsale");
        searchBox.submit();

        // Kliknutí na výsledek
        WebElement resultLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Kate Beckinsale')]")
        ));
        resultLink.click();

        // Ověření, že jsme na profilu herečky
        wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));

        // Najdi a vypiš meta tag
        WebElement metaTag = driver.findElement(By.cssSelector("meta[property='twitter:description']"));
        String metaContent = metaTag.getAttribute("content");
        System.out.println("Meta twitter:description: " + metaContent);

        Assertions.assertTrue(metaContent.contains("Underworld"), "Meta tag neobsahuje očekávaný film.");

        // Klikni na film "Underworld: Boj v podsvětí"
        WebElement movieLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Underworld') or contains(text(), 'Boj v podsvětí')]")
        ));

// Scroll k němu
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", movieLink);
        Thread.sleep(1000); // nebo použij WebDriverWait na jiný element pod ním

        movieLink.click();

        // Počkej na načtení stránky filmu a ověř titulek
        wait.until(ExpectedConditions.titleContains("Underworld"));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains("underworld"),
                "Po kliknutí na film nebyla načtena správná stránka.");
    }




    @Test
    public void imdbTestKnownForXPathClick() throws InterruptedException {
        String actressName = "Kate Beckinsale"; // nebo Reese Witherspoon
        driver.get("https://www.imdb.com/");
        Thread.sleep(3000);

        // Skryj modální GDPR dialog
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Kliknutí na odkaz herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověření načtení profilu
        wait.until(ExpectedConditions.titleContains(actressName));

        // Najdi první film podle třídy používané IMDb
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
    public void imdbTestKnownForXPathClickReese() throws InterruptedException {
        String actressName = "Reese Witherspoon"; // nebo Reese Witherspoon
        driver.get("https://www.imdb.com/");
        Thread.sleep(3000);

        // Skryj modální GDPR dialog
        ((JavascriptExecutor) driver).executeScript(
                "let dialog = document.querySelector('div[role=dialog]'); if (dialog) dialog.remove();"
        );

        // Vyhledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(actressName);
        searchBox.submit();

        // Kliknutí na odkaz herečky
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'" + actressName + "')]")
        ));
        profileLink.click();

        // Ověření načtení profilu
        wait.until(ExpectedConditions.titleContains(actressName));

        // Najdi první film podle třídy používané IMDb
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
    public void imdbTestClickFirstKnownForMovie() throws InterruptedException {
        String actressName = "Scarlett Johansson"; // Nebo "Kate Beckinsale"
        driver.get("https://www.imdb.com/");
        Thread.sleep(3000);

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
    }

    @Test
    public void imdbTestClickFirstKnownForMovieJen() throws InterruptedException {
        String actressName = "Jennifer Aniston"; // Nebo "Kate Beckinsale"
        driver.get("https://www.imdb.com/");
        Thread.sleep(3000);

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
    }

    @Test
    public void imdbTestClickFirstKnownForMovieJes() throws InterruptedException {
        String actressName = "Jessica Alba"; // Nebo "Kate Beckinsale"
        driver.get("https://www.imdb.com/");
        Thread.sleep(3000);

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
    }


}



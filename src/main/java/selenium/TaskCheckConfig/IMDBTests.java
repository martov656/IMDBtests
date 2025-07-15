package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;

public class IMDBTests extends BasedSharedMethods {

    @Test
    public void bingSearchTest() {

        driver.get("https://bing.com");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Coderslab");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Coderslab"));
        Assertions.assertTrue(driver.getPageSource().contains("Coderslab"), "Searched key not found ...");

    }

    @Test
    public void seznamSearchTests() {
        driver.manage().window().maximize();
        driver.get("https://search.seznam.cz/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Coderslab");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Coderslab"));
        Assertions.assertTrue(driver.getPageSource().contains("Coderslab"), "Searched key not found ...");

    }


    @Test
    public void googleTests() {
        driver.manage().window().maximize();
        driver.get("https://google.com/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Coderslab");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Coderslab"));
        Assertions.assertTrue(driver.getPageSource().contains("Coderslab"), "Searched key not found ...");

    }

    @Test
    public void googleTests2() {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Coderslab");
        searchBox.submit();

        wait.until(ExpectedConditions.titleContains("Coderslab"));
        Assertions.assertTrue(driver.getPageSource().contains("Coderslab"), "Searched key not found ...");
    }

    @Test
    public void seznamSearchTests2() {
        driver.manage().window().maximize();
        driver.get("https://search.seznam.cz/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Reese Witherspoon");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Searched key not found ...");

    }


    @Test
    public void bingSearchTest2() {

        driver.get("https://bing.com");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Reese Witherspoon");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Searched key not found ...");


    }

    @Test
    public void bingSearchTest3() {

        driver.get("https://www.csfd.cz/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Reese Witherspoon");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Searched key not found ...");

    }

    @Test
    public void bingSearchTest4() {

        driver.get("https://www.csfd.cz/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Jason Robards");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Jason Robards"));
        Assertions.assertTrue(driver.getPageSource().contains("Jason Robards"), "Searched key not found ...");


    }

    @Test
    public void googleSearchWithCookieAccept() throws InterruptedException {
        driver.get("https://www.bing.com/");

        // počkej, a skryj případné overlaye
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript(
                "const el = document.querySelector('div[role=dialog], iframe'); if(el) el.style.display='none';"
        );

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
            WebElement cookieButton = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(), 'Souhlasím')] | //button[contains(text(), 'Accept all')] | //button[contains(text(), 'Přijmout vše')]")
            ));
            cookieButton.click();
            System.out.println("Cookie lišta byla odkliknuta.");
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Cookie lišta nebyla nalezena nebo je jiná varianta.");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));

        System.out.println("Search input displayed: " + searchInput.isDisplayed());
        System.out.println("Search input enabled: " + searchInput.isEnabled());

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchInput);
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys("Jason Robards");


        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
        searchButton.click();

        wait.until(ExpectedConditions.titleContains("Jason Robards"));
        Assertions.assertTrue(driver.getPageSource().contains("Jason Robards"), "Search result doesn't contain the keyword.");
    }


    @Test
    public void divSearchTest5() {

        driver.get("https://div.cz/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Prolhané krásky: Prvotní hřích");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Prolhané krásky: Prvotní hřích"));
        Assertions.assertTrue(driver.getPageSource().contains("Prolhané krásky: Prvotní hřích"), "Searched key not found ...");


    }

    @Test
    public void divSearchTest6() {

        driver.get("https://div.cz/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Scarlett Johansson");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Scarlett Johansson"));
        Assertions.assertTrue(driver.getPageSource().contains("Scarlett Johansson"), "Searched key not found ...");


    }

    @Test
    public void divSearchTest7() {

        driver.get("https://div.cz/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Stephen King");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Stephen King"));
        Assertions.assertTrue(driver.getPageSource().contains("Stephen King"), "Searched key not found ...");


    }

    @Test
    public void allSearchTest9() {

        driver.get("https://bing.com");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Judas Priest");
        element.submit();
        wait.until(ExpectedConditions.titleContains("Judas Priest"));
        Assertions.assertTrue(driver.getPageSource().contains("Judas Priest"), "Searched key not found ...");


    }

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
        searchBox.sendKeys("Reese Witherspoon");
        Thread.sleep(5000);
        searchBox.submit();
        Thread.sleep(5000);
        // počkej, až se výsledky načtou (hledání přes selector nebo text)
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Reese Witherspoon')]")
        ));

        Assertions.assertTrue(result.isDisplayed(), "Reese Witherspoon nebyla nalezena ve výsledcích hledání.");
    }





    @Test
    public void imdbSearchTestElvis() {
        driver.get("https://www.imdb.com/");

        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Elvis Presley");
        searchBox.submit();

        // počkej, až se výsledky načtou (hledání přes selector nebo text)
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Elvis Presley')]")
        ));

        Assertions.assertTrue(result.isDisplayed(), "Elvis Presley nebyl nalezen ve výsledcích hledání.");
    }

    @Test
    public void fdbSearchTestElvis() {
        driver.get("https://www.fdb.cz/");
        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Elvis Presley");
        searchBox.submit();

        // počkej, až se výsledky načtou (hledání přes selector nebo text)
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Elvis Presley')]")
        ));

        Assertions.assertTrue(result.isDisplayed(), "Elvis Presley nebyl nalezen ve výsledcích hledání.");
    }

    @Test
    public void fdbSearchTestElvis2() {
        driver.get("https://www.fdb.cz/");

        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Souhlasím')]")));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        WebElement searchBox = driver.findElement(By.name("search_text"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Elvis Presley");
        searchBox.submit();

        // čekej na výsledek, například že stránka obsahuje "Elvis Presley"
        wait.until(ExpectedConditions.titleContains("Elvis Presley"));

        Assertions.assertTrue(driver.getPageSource().contains("Elvis Presley"), "Elvis Presley nebyl nalezen.");
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


}
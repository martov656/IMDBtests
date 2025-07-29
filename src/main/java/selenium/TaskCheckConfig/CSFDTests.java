package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;
import java.util.List;

public class CSFDTests extends BasedSharedMethods {


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
    public void divSearchTest6() throws InterruptedException {

        driver.get("https://csfd.cz/");
        try {
            // Přijetí cookies – podle ID a textu
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Scarlett Johansson");
        element.submit();

        wait.until(ExpectedConditions.titleContains("Scarlett Johansson"));
        Assertions.assertTrue(driver.getPageSource().contains("Scarlett Johansson"), "Searched key not found ...");


    }


    @Test
    public void divSearchTestsc2() {
        driver.get("https://csfd.cz/");

        try {
            // Přijetí cookies přes ID
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("didomi-notice-agree-button")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání herečky
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Scarlett Johansson");
        element.submit();

        // Ověření výsledků
        wait.until(ExpectedConditions.titleContains("Scarlett Johansson"));
        Assertions.assertTrue(driver.getPageSource().contains("Scarlett Johansson"), "Herečka nebyla nalezena.");

        // Debug
        System.out.println("Herečka byla nalezena.");
    }


    @Test
    public void testClickActressFromCreatorsSection() {
        driver.get("https://csfd.cz/");

        // Přijetí cookies
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("didomi-notice-agree-button")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner nebyl zobrazen nebo už byl přijat.");
        }

        // Vyhledání filmu
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Black Widow");
        searchBox.submit();

        // Kliknutí na výsledek filmu

        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/film/') and contains(.,'Black Widow')]")
        ));
        filmLink.click();


        // Scroll dolů – polovina stránky
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");

        // Kliknutí na herečku ve tvůrcích
        try {
            WebElement actressLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//section[contains(@class,'creators')]//a[contains(.,'Scarlett Johansson')]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actressLink);
            wait.until(ExpectedConditions.elementToBeClickable(actressLink)).click();
            System.out.println("Kliknuto na profil herečky.");

            // Ověření přesměrování
            wait.until(ExpectedConditions.titleContains("Scarlett Johansson"));
            System.out.println("Profil byl načten.");
            Assertions.assertTrue(driver.getPageSource().contains("Scarlett Johansson"), "Profil herečky nebyl načten.");
        } catch (Exception e) {

        }
    }

    @Test
    public void testClickActressFromCreatorsSection2() {
        driver.get("https://csfd.cz/");

        // Přijetí cookies
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("didomi-notice-agree-button")
            ));
            acceptCookies.click();
        } catch (TimeoutException ignored) {
        }

        // Vyhledání filmu
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Jurský park");
        searchBox.submit();

        // Kliknutí na výsledek filmu
        try {
            WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'/film/') and contains(.,'Jurský park')]")
            ));
            filmLink.click();
        } catch (Exception e) {
            System.out.println("Film nebyl nalezen nebo kliknutí selhalo: " + e.getMessage());
            return;
        }

        // Scroll až na konec stránky kvůli lazy-load sekci "Tvůrci"
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Počkáme na načtení sekce „Tvůrci“
        try {
            WebElement creatorsSection = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h2[contains(text(),'Tvůrci')]")
            ));

            // Najdeme herečku a klikneme
            WebElement actressLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//section//a[contains(.,'Laura Dern')]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actressLink);
            actressLink.click();

            // Ověříme, že jsme na profilu herečky
            wait.until(ExpectedConditions.titleContains("Laura Dern"));
            Assertions.assertTrue(driver.getPageSource().contains("Laura Dern"));
        } catch (Exception e) {

        }
    }

    @Test
    public void testClickActressFromCreatorsSection3() {
        driver.get("https://csfd.cz/");

        // Přijetí cookies
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("didomi-notice-agree-button")
            ));
            acceptCookies.click();
        } catch (TimeoutException ignored) {
        }

        // Vyhledání filmu
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Šílený Max");
        searchBox.submit();

        // Kliknutí na výsledek filmu
        try {
            WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'/film/') and contains(.,'Šílený Max')]")
            ));
            filmLink.click();
        } catch (Exception e) {
            System.out.println("Film nebyl nalezen nebo kliknutí selhalo: " + e.getMessage());
            return;
        }

        // Scroll až na konec stránky kvůli lazy-load sekci "Tvůrci"
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Počkáme na načtení sekce „Tvůrci“
        try {
            WebElement creatorsSection = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h2[contains(text(),'Tvůrci')]")
            ));

            // Najdeme herce a klikneme
            WebElement actressLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//section//a[contains(.,'Mel Gibson')]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actressLink);
            actressLink.click();

            // Ověříme, že jsme na profilu
            wait.until(ExpectedConditions.titleContains("Mel Gibson"));
            Assertions.assertTrue(driver.getPageSource().contains("Mel Gibson"));
        } catch (Exception e) {

        }
    }

    @Test
    public void testClickOnActressProfile() throws InterruptedException {
        driver.get("https://www.csfd.cz/");

        // Přijetí cookies
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("didomi-notice-agree-button")
            ));
            acceptCookies.click();
        } catch (TimeoutException ignored) {
        }

        // Vyhledání herece podle jména
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Chuck Norris ");
        searchBox.submit();

        // Kliknutí na odkaz vedoucí na profil herce
        try {
            WebElement actressProfileLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, '/tvurce/') and contains(.,'Chuck Norris')]")
            ));
            actressProfileLink.click();

            // Ověříme, že jsme na profilu herce
            wait.until(ExpectedConditions.titleContains("Chuck Norris"));
            Assertions.assertTrue(driver.getPageSource().contains("Chuck Norris"));
            System.out.println("Test úspěšně prošel – profil herce otevřen.");
        } catch (Exception e) {

            System.out.println("Nepodařilo se najít nebo otevřít profil herece: " + e.getMessage());
        }
    }


    @Test
    public void divSearchTestCS2() {
        driver.get("https://csfd.cz/");

        try {
            // Přijetí cookies – podle ID a textu
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Vetřelec");
        element.submit();

        wait.until(ExpectedConditions.titleContains("Vetřelec"));
        Assertions.assertTrue(driver.getPageSource().contains("Vetřelec"), "Searched key not found ...");

    }

    @Test
    public void divSearchTestCS3() {
        driver.get("https://csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Hra s nevěrou ");
        element.submit();

        wait.until(ExpectedConditions.titleContains("Hra s nevěrou "));
        Assertions.assertTrue(driver.getPageSource().contains("Hra s nevěrou "), "Searched key not found ...");

        // Klikni na název filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'Hra s nevěrou')]")
        ));
        filmLink.click();

        System.out.println("Kliknutí na odkaz filmu bylo úspěšné.");

        // Ověř, že se stránka načetla
        wait.until(ExpectedConditions.titleContains("Hra s nevěrou"));
    }


    @Test
    public void divSearchTestCS4() {
        driver.get("https://csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání filmu
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Pravá blondýnka");
        element.submit();

        // Ověření, že jsme ve výsledcích
        wait.until(ExpectedConditions.titleContains("Pravá blondýnka"));
        Assertions.assertTrue(driver.getPageSource().contains("Pravá blondýnka"), "Searched key not found ...");

        // Kliknutí na odkaz filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'Pravá blondýnka')]")
        ));
        filmLink.click();
        System.out.println("Kliknutí na odkaz filmu bylo úspěšné.");

        // Ověření, že stránka je načtená
        wait.until(ExpectedConditions.titleContains("Pravá blondýnka"));

        // Scroll ke sekci "Hrají"
        WebElement actorsHeader = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h4[contains(text(),'Hrají:')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorsHeader);

        // Kliknutí na herečku (např. Kate Beckinsale)

    }

    // Fumkční test
    @Test
    public void clickOnActorInCastSection() {
        driver.get("https://www.csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání filmu
        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Canary Black");
        searchBox.submit();

        wait.until(ExpectedConditions.titleContains("Canary Black"));
        Assertions.assertTrue(driver.getPageSource().contains("Canary Black"), "Film nebyl nalezen ve výsledcích vyhledávání.");

        // Kliknutí na název filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'Canary Black')]")
        ));
        filmLink.click();
        System.out.println("Kliknutí na odkaz filmu bylo úspěšné.");

        // Počkáme na načtení detailu filmu
        wait.until(ExpectedConditions.titleContains("Canary Black"));

        // Mírný scroll dolů, protože sekce „Hrají“ je níže
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");


        // Kliknutí na herečku Kate Beckinsale
        try {
            WebElement actorLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//h4[text()='Hrají:']/following-sibling::a[contains(text(),'Kate Beckinsale')]")
            ));
            actorLink.click();

            wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
            Assertions.assertTrue(driver.getPageSource().contains("Kate Beckinsale"));
            System.out.println("Profil herečky byl úspěšně otevřen.");
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Herečka nebyla nalezena nebo kliknutí selhalo: " + e.getMessage());
        } finally {

            driver.quit();
        }

    }

    // Fumkční test - režie
    @Test
    public void clickOnActorInCastSection2() {
        driver.get("https://www.csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání filmu
        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Canary Black");
        searchBox.submit();

        wait.until(ExpectedConditions.titleContains("Canary Black"));
        Assertions.assertTrue(driver.getPageSource().contains("Canary Black"), "Film nebyl nalezen ve výsledcích vyhledávání.");

        // Kliknutí na název filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'Canary Black')]")
        ));
        filmLink.click();
        System.out.println("Kliknutí na odkaz filmu bylo úspěšné.");

        // Počkáme na načtení detailu filmu
        wait.until(ExpectedConditions.titleContains("Canary Black"));

        // Mírný scroll dolů, protože sekce „Hrají“ je níže
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");


        // Kliknutí na režiséra
        try {
            WebElement actorLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//h4[text()='Režie:']/following-sibling::a[contains(text(),'Pierre Morel')]")
            ));
            actorLink.click();

            wait.until(ExpectedConditions.titleContains("Pierre Morel"));
            Assertions.assertTrue(driver.getPageSource().contains("Pierre Morel"));
            System.out.println("Profil režiséra byl úspěšně otevřen.");
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Režisér nebyl nalezena nebo kliknutí selhalo: " + e.getMessage());
        } finally {

            driver.quit();
        }

    }

    @Test
    public void testDirectorFindAndClickCanaryBlackByIteration() {
        driver.get("https://www.csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání režiséra
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pierre Morel");
        searchBox.submit();

        wait.until(ExpectedConditions.titleContains("Pierre Morel"));
        Assertions.assertTrue(driver.getPageSource().contains("Pierre Morel"), "Režisér nebyl nalezen ve výsledcích vyhledávání.");

        // Kliknutí na odkaz profilu režiséra
        WebElement directorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/tvurce/') and contains(text(),'Pierre Morel')]")
        ));
        directorLink.click();
        System.out.println("Kliknutí na profil režiséra bylo úspěšné.");

        wait.until(ExpectedConditions.titleContains("Pierre Morel"));

        // Iterace přes filmy režiséra
        List<WebElement> filmLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("td.name a.film-title-name")
        ));

        boolean found = false;

        for (WebElement filmLink : filmLinks) {
            String filmTitle = filmLink.getText().trim();
            if (filmTitle.equalsIgnoreCase("Canary Black")) {
                filmLink.click();
                found = true;
                System.out.println("Film 'Canary Black' nalezen a kliknuto.");
                break;
            }
        }

        Assertions.assertTrue(found, "Film 'Canary Black' nebyl nalezen mezi filmy režiséra.");

        // Ověření, že jsme na správné stránce filmu
        wait.until(ExpectedConditions.titleContains("Canary Black"));
        Assertions.assertTrue(driver.getPageSource().contains("Canary Black"), "Detail filmu se nezobrazil.");

        driver.quit();
    }

    //prochází více stránek na profilu režiséra (např. když je film Canary Black na druhé nebo další stránce),

    //zastaví se, jakmile film najde,

    //jinak vypíše, že nebyl nalezen.

    @Test
    public void testDirectorFindAndClickCanaryBlackWithPagination() {
        driver.get("https://www.csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání režiséra
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Pierre Morel");
        searchBox.submit();

        wait.until(ExpectedConditions.titleContains("Pierre Morel"));
        Assertions.assertTrue(driver.getPageSource().contains("Pierre Morel"), "Režisér nebyl nalezen ve výsledcích vyhledávání.");

        // Kliknutí na profil režiséra
        WebElement directorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/tvurce/') and contains(text(),'Pierre Morel')]")
        ));
        directorLink.click();
        System.out.println("Profil režiséra byl úspěšně otevřen.");

        wait.until(ExpectedConditions.titleContains("Pierre Morel"));

        boolean found = false;
        int currentPage = 1;

        while (true) {
            System.out.println("Hledám film na stránce " + currentPage);

            List<WebElement> filmLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("td.name a.film-title-name")
            ));

            for (WebElement filmLink : filmLinks) {
                String filmTitle = filmLink.getText().trim();
                if (filmTitle.equalsIgnoreCase("Canary Black")) {
                    filmLink.click();
                    found = true;
                    System.out.println("Film 'Canary Black' nalezen a kliknuto.");
                    break;
                }
            }

            if (found) {
                break;
            }

            // Zkusíme najít další stránku
            try {
                WebElement nextButton = driver.findElement(By.xpath("//a[contains(@class,'page') and contains(text(), '»')]"));
                nextButton.click();
                wait.until(ExpectedConditions.stalenessOf(filmLinks.get(0))); // čekáme, než se stránka přenačte
                currentPage++;
            } catch (NoSuchElementException e) {
                System.out.println("Další stránka neexistuje.");
                break;
            }
        }

        Assertions.assertTrue(found, "'Canary Black' nebyl nalezen mezi filmy režiséra.");

        if (found) {
            wait.until(ExpectedConditions.titleContains("Canary Black"));
            Assertions.assertTrue(driver.getPageSource().contains("Canary Black"), "Detail filmu se nezobrazil.");
        }

        driver.quit();
    }

    @Test
    public void testDirectorFindAndClickCanaryBlackWithPagination2() {
        driver.get("https://www.csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Reese Witherspoon");
        searchBox.submit();

        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Herečka nebyla nalezena ve výsledcích vyhledávání.");

        // Kliknutí na profil
        WebElement directorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/tvurce/') and contains(text(),'Reese Witherspoon')]")
        ));
        directorLink.click();
        System.out.println("Profil herečky byl úspěšně otevřen.");

        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));

        boolean found = false;
        int currentPage = 1;

        while (true) {
            System.out.println("Hledám film na stránce " + currentPage);

            List<WebElement> filmLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("td.name a.film-title-name")
            ));

            for (WebElement filmLink : filmLinks) {
                String filmTitle = filmLink.getText().trim();
                if (filmTitle.equalsIgnoreCase("Pravá blondýnka")) {
                    filmLink.click();
                    found = true;
                    System.out.println("Film 'Pravá blondýnka' nalezen a kliknuto.");
                    break;
                }
            }

            if (found) {
                break;
            }

            // Zkusíme najít další stránku
            try {
                WebElement nextButton = driver.findElement(By.xpath("//a[contains(@class,'page') and contains(text(), '»')]"));
                nextButton.click();
                wait.until(ExpectedConditions.stalenessOf(filmLinks.get(0))); // čekáme, než se stránka přenačte
                currentPage++;
            } catch (NoSuchElementException e) {
                System.out.println("Další stránka neexistuje.");
                break;
            }
        }

        Assertions.assertTrue(found, "'Pravá blondýnka' nebyl nalezen mezi filmy herečky.");

        if (found) {
            wait.until(ExpectedConditions.titleContains("Pravá blondýnka"));
            Assertions.assertTrue(driver.getPageSource().contains("Pravá blondýnka"), "Detail filmu se nezobrazil.");
        }

        driver.quit();

    }

    @Test
    public void testDirectorFindAndClickCanaryBlackWithPagination3() {
        driver.get("https://www.csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies banner se nezobrazil nebo už byl potvrzen.");
        }

        // Vyhledání herečky
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Reese Witherspoon");
        searchBox.submit();

        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
        Assertions.assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Herečka nebyla nalezena ve výsledcích vyhledávání.");

        // Kliknutí na profil
        WebElement directorLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/tvurce/') and contains(text(),'Reese Witherspoon')]")
        ));
        directorLink.click();
        System.out.println("Profil režiséra byl úspěšně otevřen.");

        wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));

        boolean found = false;
        int currentPage = 1;

        while (true) {
            System.out.println("Hledám film na stránce " + currentPage);

            List<WebElement> filmLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("td.name a.film-title-name")
            ));

            for (WebElement filmLink : filmLinks) {
                String filmTitle = filmLink.getText().trim();
                if (filmTitle.equalsIgnoreCase("The Morning Show")) {
                    filmLink.click();
                    found = true;
                    System.out.println("Film 'The Morning Show' nalezen a kliknuto.");
                    break;
                }
            }

            if (found) {
                break;
            }

            // Zkusíme najít další stránku
            try {
                WebElement nextButton = driver.findElement(By.xpath("//a[contains(@class,'page') and contains(text(), '»')]"));
                nextButton.click();
                wait.until(ExpectedConditions.stalenessOf(filmLinks.get(0))); // čekáme, než se stránka přenačte
                currentPage++;
            } catch (NoSuchElementException e) {
                System.out.println("Další stránka neexistuje.");
                break;
            }
        }

        Assertions.assertTrue(found, "'The Morning Show' nebyl nalezen mezi filmy herečky.");

        if (found) {
            wait.until(ExpectedConditions.titleContains("The Morning Show"));
            Assertions.assertTrue(driver.getPageSource().contains("The Morning Show"), "Detail filmu se nezobrazil.");
        }

        driver.quit();


    }


        @Test
        public void openTopMoviesAndClickOnFilm2() throws InterruptedException {
            driver.get("https://www.csfd.cz/");

            try {
                WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
                ));
                acceptCookies.click();
                System.out.println("Cookies byly přijaty.");
            } catch (TimeoutException e) {
                System.out.println("Cookies již byly přijaty.");
            }

            // Klik na „Žebříčky“
            WebElement zebrickyzalozka = wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText("Žebříčky")
            ));
            zebrickyzalozka.click();

            // Počkáme na načtení sekce
            wait.until(ExpectedConditions.titleContains("Žebříčky"));

            // Klik na první žebříček obsahující "Filmy"
            WebElement topMovies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.partialLinkText("Filmy")
            ));
            topMovies.click();

            String hledanyFilm = "Vykoupení z věznice Shawshank";
            boolean nalezeno = false;

            for (int posun = 1; posun <= 400; posun += 100) {
                String url = "https://www.csfd.cz/zebricky/filmy/nejlepsi/?from=" + posun;
                driver.get(url);
                System.out.println("Hledám film na stránce od " + posun);
                Thread.sleep(6000);
                List<WebElement> filmy = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("h3.film-title-norating a.film-title-name")
                ));

                for (WebElement film : filmy) {
                    if (film.getText().trim().equalsIgnoreCase(hledanyFilm)) {
                        film.click();
                        nalezeno = true;
                        System.out.println("Film nalezen a otevřen.");
                        break;
                    }
                }
                if (nalezeno) break;
            }
            Thread.sleep(4000);
            Assertions.assertTrue(nalezeno, "'" + hledanyFilm + "' nebyl nalezen v žebříčcích.");
        }

    @Test
    public void openTopMoviesAndClickOnFilm3() throws InterruptedException {
        driver.get("https://www.csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies již byly přijaty.");
        }

        // Klik na „Žebříčky“
        WebElement zebrickyzalozka = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Žebříčky")
        ));
        zebrickyzalozka.click();

        // Počkáme na načtení sekce
        wait.until(ExpectedConditions.titleContains("Žebříčky"));

        // Klik na první žebříček obsahující "Filmy"
        WebElement topMovies = wait.until(ExpectedConditions.elementToBeClickable(
                By.partialLinkText("Filmy")
        ));
        topMovies.click();

        String hledanyFilm = "Interstellar";
        boolean nalezeno = false;

        for (int posun = 1; posun <= 400; posun += 100) {
            String url = "https://www.csfd.cz/zebricky/filmy/nejlepsi/?from=" + posun;
            driver.get(url);
            System.out.println("Hledám film na stránce od " + posun);
            Thread.sleep(6000);
            List<WebElement> filmy = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("h3.film-title-norating a.film-title-name")
            ));

            for (WebElement film : filmy) {
                if (film.getText().trim().equalsIgnoreCase(hledanyFilm)) {
                    film.click();
                    nalezeno = true;
                    System.out.println("Film nalezen a otevřen.");
                    break;
                }
            }
            if (nalezeno) break;
        }
        Thread.sleep(4000);
        Assertions.assertTrue(nalezeno, "'" + hledanyFilm + "' nebyl nalezen v žebříčcích.");
    }

    @Test
    public void openTopMoviesAndClickOnFilm() {
        driver.get("https://www.csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='didomi-notice-agree-button' or span[contains(text(),'Rozumím a přijímám')]]")
            ));
            acceptCookies.click();
            System.out.println("Cookies byly přijaty.");
        } catch (TimeoutException e) {
            System.out.println("Cookies již byly přijaty.");
        }

        // Klik na „Žebříčky“
        WebElement zebrickyzalozka = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Žebříčky")
        ));
        zebrickyzalozka.click();

        wait.until(ExpectedConditions.titleContains("Žebříčky"));

        // Klik na první žebříček obsahující "Filmy"
        WebElement topMovies = wait.until(ExpectedConditions.elementToBeClickable(
                By.partialLinkText("Filmy")
        ));
        topMovies.click();

        String hledanyFilm = "Poslední skaut";
        boolean nalezeno = false;

        for (int posun = 1; posun <= 400; posun += 100) {
            String url = "https://www.csfd.cz/zebricky/filmy/nejlepsi/?from=" + posun;
            driver.get(url);
            System.out.println("Hledám film na stránce od " + posun);

            List<WebElement> filmy = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("h3.film-title-norating a.film-title-name")
            ));

            for (WebElement film : filmy) {
                String nazevFilmu = film.getText().toLowerCase().trim();
                if (nazevFilmu.contains(hledanyFilm)) {
                    System.out.println("Film nalezen: " + nazevFilmu);
                    film.click();
                    nalezeno = true;
                    break;
                }
            }
            if (nalezeno) break;
        }

        Assertions.assertTrue(nalezeno, "'" + hledanyFilm + "' nebyl nalezen v žebříčcích.");
    }


    }


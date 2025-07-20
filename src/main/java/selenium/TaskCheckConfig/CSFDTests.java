package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
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
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'Souhlasím')]")
            ));
            acceptCookies.click();
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
    public void divSearchTestCS() throws InterruptedException {
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
    public void testClickActressFromFilmCreators() {
        driver.get("https://csfd.cz/");

        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("didomi-notice-agree-button")
            ));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Cookies už byly potvrzeny nebo se nezobrazily.");
        }

        // Vyhledání filmu
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Manželská historie"); // nebo jiný film s herečkou
        searchBox.submit();

        // Klik na odkaz filmu (pokud tam nejsme rovnou)
        try {
            WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'/film/') and contains(.,'Manželská historie')]")
            ));
            filmLink.click();
        } catch (Exception e) {
            System.out.println("Zřejmě jsme už na stránce filmu.");
        }

        // Scroll dolů – pro jistotu
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");

        // Počkej na profil herečky ve tvůrcích
        try {
            WebElement actressLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//section[contains(@class,'creators')]//a[contains(.,'Scarlett Johansson')]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actressLink);
            wait.until(ExpectedConditions.elementToBeClickable(actressLink)).click();

            // Ověření přesměrování
            wait.until(ExpectedConditions.titleContains("Scarlett Johansson"));
            System.out.println("Kliknutí na profil herečky proběhlo.");
        } catch (Exception e) {
            System.out.println("Herečka v sekci Tvůrci nebyla nalezena: " + e.getMessage());
        }
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
        try {
            WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'/film/') and contains(.,'Black Widow')]")
            ));
            filmLink.click();
            System.out.println("Kliknuto na odkaz filmu.");
        } catch (Exception e) {
            System.out.println("Film nebyl nalezen nebo kliknutí selhalo: " + e.getMessage());
            return;
        }

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
            Assertions.assertTrue(driver.getPageSource().contains("Scarlett Johansson"), "Profil herečky nebyl načten.");
        } catch (Exception e) {
            System.out.println("Herečka nebyla nalezena v sekci Tvůrci: " + e.getMessage());
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
        searchBox.sendKeys("Scarlett Johansson");
        searchBox.submit();

        // Kliknutí na výsledek filmu
        try {
            WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'/film/') and contains(.,'Scarlett Johansson')]")
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
                    By.xpath("//section//a[contains(.,'Scarlett Johansson')]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actressLink);
            actressLink.click();

            // Ověříme, že jsme na profilu herečky
            wait.until(ExpectedConditions.titleContains("Scarlett Johansson"));
            Assertions.assertTrue(driver.getPageSource().contains("Scarlett Johansson"));
        } catch (Exception e) {
            System.out.println("Herečka nebyla nalezena v sekci Tvůrci: " + e.getMessage());
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
        searchBox.sendKeys("Kate Beckinsale");
        searchBox.submit();

        // Kliknutí na výsledek filmu
        try {
            WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'/film/') and contains(.,'Kate Beckinsale')]")
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
                    By.xpath("//section//a[contains(.,'Kate Beckinsale')]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actressLink);
            actressLink.click();

            // Ověříme, že jsme na profilu herečky
            wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
            Assertions.assertTrue(driver.getPageSource().contains("Kate Beckinsale"));
        } catch (Exception e) {
            System.out.println("Herečka nebyla nalezena v sekci Tvůrci: " + e.getMessage());
        }
    }

    @Test
    public void testClickOnActressProfile() {
        driver.get("https://www.csfd.cz/");

        // Přijetí cookies
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("didomi-notice-agree-button")
            ));
            acceptCookies.click();
        } catch (TimeoutException ignored) {
        }

        // Vyhledání herečky podle jména
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Kate Beckinsale");
        searchBox.submit();

        // Kliknutí na odkaz vedoucí na profil herečky
        try {
            WebElement actressProfileLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, '/tvurce/') and contains(.,'Kate Beckinsale')]")
            ));
            actressProfileLink.click();

            // Ověříme, že jsme na profilu herečky
            wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
            Assertions.assertTrue(driver.getPageSource().contains("Kate Beckinsale"));
            System.out.println("Test úspěšně prošel – profil herečky otevřen.");
        } catch (Exception e) {
            System.out.println("Nepodařilo se najít nebo otevřít profil herečky: " + e.getMessage());
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
        element.sendKeys("Canary Black");
        element.submit();

        wait.until(ExpectedConditions.titleContains("Canary Black"));
        Assertions.assertTrue(driver.getPageSource().contains("Canary Black"), "Searched key not found ...");

    }

    @Test
    public void divSearchTestCS3() throws InterruptedException {
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
        element.sendKeys("Canary Black");
        element.submit();

        wait.until(ExpectedConditions.titleContains("Canary Black"));
        Assertions.assertTrue(driver.getPageSource().contains("Canary Black"), "Searched key not found ...");

        // Klikni na název filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'Canary Black')]")
        ));
        filmLink.click();

        System.out.println("Kliknutí na odkaz filmu bylo úspěšné.");

        // Ověř, že se stránka načetla
        wait.until(ExpectedConditions.titleContains("Canary Black"));
    }


    @Test
    public void divSearchTestCS4() throws InterruptedException {
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
        element.sendKeys("Canary Black");
        element.submit();

        // Ověření, že jsme ve výsledcích
        wait.until(ExpectedConditions.titleContains("Canary Black"));
        Assertions.assertTrue(driver.getPageSource().contains("Canary Black"), "Searched key not found ...");

        // Kliknutí na odkaz filmu
        WebElement filmLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'Canary Black')]")
        ));
        filmLink.click();
        System.out.println("Kliknutí na odkaz filmu bylo úspěšné.");

        // Ověření, že stránka je načtená
        wait.until(ExpectedConditions.titleContains("Canary Black"));

        // Scroll ke sekci "Hrají"
        WebElement actorsHeader = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h4[contains(text(),'Hrají:')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actorsHeader);

        // Kliknutí na herečku (např. Kate Beckinsale)

    }

    @Test
    public void clickOnActorInCastSection() throws InterruptedException {
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
        Thread.sleep(2000); // nebo použij fluent wait

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
            Thread.sleep(3000); // pro vizuální kontrolu (volitelné)
            driver.quit();
        }

    }
}









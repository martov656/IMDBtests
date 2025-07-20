package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.BasedSharedMethods;

import java.util.List;

public class SearchDivTests extends BasedSharedMethods {


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
    public void divSearchTest6() throws InterruptedException {

        driver.get("https://div.cz/");
        WebElement element = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys("Scarlett Johansson");
        element.submit();
        Thread.sleep(3000);
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
    public void divPartialSearchTest() throws InterruptedException {

        driver.get("https://div.cz/");
        WebElement searchBox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys("Scarlett");
        searchBox.submit();

        Thread.sleep(5000); // jen pro debug – později nahradit wait()

        System.out.println(driver.getPageSource()); // uvidíš výstup

        List<WebElement> headers = driver.findElements(By.xpath("//h2"));
        for (WebElement h : headers) {
            System.out.println("H2: " + h.getText());
        }

        List<WebElement> results = driver.findElements(
                By.xpath("//*[contains(text(), 'Scarlett Johansson')]"));

        Assertions.assertFalse(results.isEmpty(), "Scarlett Johansson not found among search results.");
        Thread.sleep(5000);
    }

    @Test
    public void loginTest() throws InterruptedException {

        // 1. Hlavní stránka
        driver.get("https://div.cz/");

        // 2. Kliknutí na "Přihlásit"
        WebElement loginLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[contains(text(),'Přihlásit')]")));
        loginLink.click();

        // 3. Počkej na přihlašovací formulář (name="email")
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));

        // 4. Vyplnění údajů
        driver.findElement(By.name("login")).sendKeys("martinx");     // <-- zadej správné testovací údaje
        driver.findElement(By.name("password")).sendKeys("Whitesnake1987");   // <-- zadej správné heslo

        // 5. Klikni na tlačítko "Přihlásit"
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Přihlásit')]"));
        loginButton.click();

        // 6. Ověření, že jsme přihlášeni
        WebElement logoutLink = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Odhlásit')]")));

        Assertions.assertTrue(logoutLink.isDisplayed(), "Přihlášení se nezdařilo – 'Odhlásit' nebylo nalezeno.");

    }

    @Test
    public void logoutTest() throws InterruptedException {

        // 1. Hlavní stránka
        driver.get("https://div.cz/");

        // 2. Kliknutí na "Přihlásit"
        WebElement loginLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[contains(text(),'Přihlásit')]")));
        loginLink.click();

        // 3. Počkej na přihlašovací formulář (name="login")
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));

        // 4. Vyplnění údajů
        driver.findElement(By.name("login")).sendKeys("martinx");
        driver.findElement(By.name("password")).sendKeys("Whitesnake1987");
        Thread.sleep(2000);
        // 5. Přihlášení
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Přihlásit')]"));
        loginButton.click();

        // 6. Ověření přihlášení – přítomnost odkazu "Odhlásit"
        WebElement logoutLink = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Odhlásit')]")));
        Assertions.assertTrue(logoutLink.isDisplayed(), "'Odhlásit' nebylo nalezeno – přihlášení selhalo.");
        Thread.sleep(2000);
        // 7. Kliknutí na "Odhlásit"
        logoutLink.click();
        Thread.sleep(2000);

        // 8. Ověření odhlášení – opětovná přítomnost odkazu "Přihlásit"
        WebElement loginAgainLink = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Přihlásit')]")));
        Assertions.assertTrue(loginAgainLink.isDisplayed(), "Po odhlášení se nezobrazil odkaz 'Přihlásit'.");
        Thread.sleep(2000);
        driver.get("https://div.cz/filmy/");
        Thread.sleep(2000);

// Najdi nadpis sekce "V kinech"
        WebElement vKinechHeader = driver.findElement(By.xpath("//h3[text()='V kinech']"));

// Najdi první film, který následuje po této sekci
// Předpokládáme, že filmy jsou v kartách (např. <div class="card">) po tomto nadpisu
        WebElement prvniFilmVKinech = vKinechHeader.findElement(
                By.xpath("following::a[contains(@href, '/film/')][1]"));

// Klikni na první film
        prvniFilmVKinech.click();
        Thread.sleep(2000);
    }

    @Test
    public void logoutTest2() {

        // 1. Hlavní stránka
        driver.get("https://div.cz/");

        // 2. Kliknutí na "Přihlásit"
        WebElement loginLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[contains(text(),'Přihlásit')]")));
        loginLink.click();

        // 3. Počkej na přihlašovací formulář (name="login")
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));

        // 4. Vyplnění údajů
        driver.findElement(By.name("login")).sendKeys("martinx");
        driver.findElement(By.name("password")).sendKeys("Whitesnake1987");

        // 5. Přihlášení
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Přihlásit')]"));
        loginButton.click();

        // 6. Ověření přihlášení – přítomnost odkazu "Odhlásit"
        WebElement logoutLink = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Odhlásit')]")));
        Assertions.assertTrue(logoutLink.isDisplayed(), "'Odhlásit' nebylo nalezeno – přihlášení selhalo.");

        // 7. Kliknutí na "Odhlásit"
        logoutLink.click();


        // 8. Ověření odhlášení – opětovná přítomnost odkazu "Přihlásit"
        WebElement loginAgainLink = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Přihlásit')]")));
        Assertions.assertTrue(loginAgainLink.isDisplayed(), "Po odhlášení se nezobrazil odkaz 'Přihlásit'.");

        driver.get("https://div.cz/filmy/");


// Najdi nadpis sekce "V kinech"
        WebElement vKinechHeader = driver.findElement(By.xpath("//h3[text()='V kinech']"));

// Najdi první film, který následuje po této sekci
// Předpokládáme, že filmy jsou v kartách (např. <div class="card">) po tomto nadpisu
        WebElement prvniFilmVKinech = vKinechHeader.findElement(
                By.xpath("following::a[contains(@href, '/film/')][1]"));

// Klikni na první film
        prvniFilmVKinech.click();



    }

    @Test
    public void invalidUsernameLoginTest() {

        // 1. Hlavní stránka
        driver.get("https://div.cz/");

        // 2. Kliknutí na "Přihlásit"
        WebElement loginLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[contains(text(),'Přihlásit')]")));
        loginLink.click();

        // 3. Počkej na přihlašovací formulář
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));

        // 4. Vyplnění špatného uživatelského jména a správného hesla
        driver.findElement(By.name("login")).sendKeys("spatneJmeno");
        driver.findElement(By.name("password")).sendKeys("Whitesnake1987");

        // 5. Kliknutí na tlačítko "Přihlásit"
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Přihlásit')]"));
        loginButton.click();

        // 6. Krátké čekání kvůli možnému přesměrování nebo zobrazení chyby


// Kontrola, že uživatel není přihlášen
        boolean odhlaseniExistuje = driver.findElements(By.xpath("//a[contains(text(),'Odhlásit')]")).size() > 0;
        Assertions.assertFalse(odhlaseniExistuje, "Uživatel byl přihlášen i se špatným jménem.");

// Ověření, že se zobrazila chybová hláška nebo že jsme stále na přihlašovací stránce
        List<WebElement> chyba = driver.findElements(By.className("invalid-feedback"));
        Assertions.assertTrue(chyba.size() > 0 || driver.getCurrentUrl().contains("login"),
                "Přihlášení selhalo, ale nezůstali jsme na přihlašovací stránce a žádná chyba se nezobrazila.");

    }

    @Test
    public void invalidUsernameLoginTest2()  {

        // 1. Hlavní stránka
        driver.get("https://div.cz/");

        // 2. Kliknutí na "Přihlásit"
        WebElement loginLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[contains(text(),'Přihlásit')]")));
        loginLink.click();

        // 3. Počkej na přihlašovací formulář
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));

        // 4. Vyplnění špatného uživatelského jména a správného hesla
        driver.findElement(By.name("login")).sendKeys("martinx");
        driver.findElement(By.name("password")).sendKeys("Whitesnake");

        // 5. Kliknutí na tlačítko "Přihlásit"
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Přihlásit')]"));
        loginButton.click();

        // 6. Krátké čekání kvůli možnému přesměrování nebo zobrazení chyby


// Kontrola, že uživatel není přihlášen
        boolean odhlaseniExistuje = driver.findElements(By.xpath("//a[contains(text(),'Odhlásit')]")).size() > 0;
        Assertions.assertFalse(odhlaseniExistuje, "Uživatel byl přihlášen i se špatným heslem.");

// Ověření, že se zobrazila chybová hláška nebo že jsme stále na přihlašovací stránce
        List<WebElement> chyba = driver.findElements(By.className("invalid-feedback"));
        Assertions.assertTrue(chyba.size() > 0 || driver.getCurrentUrl().contains("login"),
                "Přihlášení selhalo, ale nezůstali jsme na přihlašovací stránce a žádná chyba se nezobrazila.");

    }

}




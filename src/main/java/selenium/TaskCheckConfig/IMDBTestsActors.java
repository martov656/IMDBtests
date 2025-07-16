package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.BasedSharedMethods;

public class IMDBTestsActors extends BasedSharedMethods {





@Test
public void imdbTestClickFirstKnownForMovieMel() throws InterruptedException {
    String actressName = "Mel Gibson"; //
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
public void imdbTestClickFirstKnownForMovieMichael() throws InterruptedException {
    String actressName = "Michael Douglas"; //
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
public void imdbTestClickFirstKnownForMovieClint() throws InterruptedException {
    String actressName = "Clint Eastwood"; //
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








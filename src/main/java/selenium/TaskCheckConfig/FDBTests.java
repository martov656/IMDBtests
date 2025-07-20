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

public class FDBTests extends BasedSharedMethods {


    @Test
    public void fdbSearchTestReese2() {
        driver.get("https://www.fdb.cz/");

        try {
            // Přijetí cookies
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Přijmout vše')]")
            ));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        try {
            // Vyhledání herečky Reese Witherspoon
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Reese Witherspoon");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
            Assertions.assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Voda pro slony')]")
            ));

            // Zvýrazni a scrollni na něj
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'; arguments[0].scrollIntoView(true);", filmHeading);
            System.out.println("Text filmu nalezen: " + filmHeading.getText());

            // Najdi wrapper <a> a klikni
            try {
                WebElement parentLink = filmHeading.findElement(By.xpath("./ancestor::a[1]"));
                System.out.println("Href odkazu: " + parentLink.getAttribute("href"));
                wait.until(ExpectedConditions.elementToBeClickable(parentLink));
                parentLink.click();
                System.out.println("Kliknutí na odkaz proběhlo.");
            } catch (Exception e) {
                System.out.println("Kliknutí na wrapper <a> selhalo, zkusíme JavaScript klik...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmHeading);
            }

            // Počkej, až se načte stránka filmu
            wait.until(ExpectedConditions.titleContains("Voda pro slony"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }
    }

    @Test
    public void fdbSearchTestReese3() {
        driver.get("https://www.fdb.cz/");

        try {
            // Přijetí cookies
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Přijmout vše')]")
            ));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        try {
            // Vyhledání herečky Reese Witherspoon
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Reese Witherspoon");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Reese Witherspoon"));
            Assertions.assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Divoká dvojka')]")
            ));

            // Zvýrazni a scrollni na něj
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'; arguments[0].scrollIntoView(true);", filmHeading);
            System.out.println("Text filmu nalezen: " + filmHeading.getText());

            // Najdi wrapper <a> a klikni
            try {
                WebElement parentLink = filmHeading.findElement(By.xpath("./ancestor::a[1]"));
                System.out.println("Href odkazu: " + parentLink.getAttribute("href"));
                wait.until(ExpectedConditions.elementToBeClickable(parentLink));
                parentLink.click();
                System.out.println("Kliknutí na odkaz proběhlo.");
            } catch (Exception e) {
                System.out.println("Kliknutí na wrapper <a> selhalo, zkusíme JavaScript klik...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmHeading);
            }

            // Počkej, až se načte stránka filmu
            wait.until(ExpectedConditions.titleContains("Divoká dvojka"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }
    }

    @Test
    public void fdbSearchTestKate() {
        driver.get("https://www.fdb.cz/");

        try {
            // Přijetí cookies
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Přijmout vše')]")
            ));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        try {
            // Vyhledání herečky Reese Witherspoon
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Kate Beckinsale");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
            Assertions.assertTrue(driver.getPageSource().contains("Kate Beckinsale"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Pearl Harbor')]")
            ));

            // Zvýrazni a scrollni na něj
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'; arguments[0].scrollIntoView(true);", filmHeading);
            System.out.println("Text filmu nalezen: " + filmHeading.getText());

            // Najdi wrapper <a> a klikni
            try {
                WebElement parentLink = filmHeading.findElement(By.xpath("./ancestor::a[1]"));
                System.out.println("Href odkazu: " + parentLink.getAttribute("href"));
                wait.until(ExpectedConditions.elementToBeClickable(parentLink));
                parentLink.click();
                System.out.println("Kliknutí na odkaz proběhlo.");
            } catch (Exception e) {
                System.out.println("Kliknutí na wrapper <a> selhalo, zkusíme JavaScript klik...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmHeading);
            }

            // Počkej, až se načte stránka filmu
            wait.until(ExpectedConditions.titleContains("Pearl Harbor"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }
    }

    @Test
    public void fdbSearchTestKate2() {
        driver.get("https://www.fdb.cz/");

        try {
            // Přijetí cookies
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Přijmout vše')]")
            ));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        try {
            // Vyhledání herečky Reese Witherspoon
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Kate Beckinsale");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));
            Assertions.assertTrue(driver.getPageSource().contains("Kate Beckinsale"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Kate Beckinsale')]")
            ));

            // Zvýrazni a scrollni na něj
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'; arguments[0].scrollIntoView(true);", filmHeading);
            System.out.println("Text filmu nalezen: " + filmHeading.getText());

            // Najdi wrapper <a> a klikni
            try {
                WebElement parentLink = filmHeading.findElement(By.xpath("./ancestor::a[1]"));
                System.out.println("Href odkazu: " + parentLink.getAttribute("href"));
                wait.until(ExpectedConditions.elementToBeClickable(parentLink));
                parentLink.click();
                System.out.println("Kliknutí na odkaz proběhlo.");
            } catch (Exception e) {
                System.out.println("Kliknutí na wrapper <a> selhalo, zkusíme JavaScript klik...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmHeading);
            }

            // Počkej, až se načte stránka filmu
            wait.until(ExpectedConditions.titleContains("Kate Beckinsale"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }
    }

    @Test
    public void fdbSearchTestKate3() {
        driver.get("https://www.fdb.cz/");

        try {
            // Přijetí cookies
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Přijmout vše')]")
            ));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        try {
            // Vyhledání herečky Reese Witherspoon
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Kate Winslet");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Kate Winslet"));
            Assertions.assertTrue(driver.getPageSource().contains("Kate Winslet"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Kate Winslet')]")
            ));

            // Zvýrazni a scrollni na něj
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'; arguments[0].scrollIntoView(true);", filmHeading);
            System.out.println("Text filmu nalezen: " + filmHeading.getText());

            // Najdi wrapper <a> a klikni
            try {
                WebElement parentLink = filmHeading.findElement(By.xpath("./ancestor::a[1]"));
                System.out.println("Href odkazu: " + parentLink.getAttribute("href"));
                wait.until(ExpectedConditions.elementToBeClickable(parentLink));
                parentLink.click();
                System.out.println("Kliknutí na odkaz proběhlo.");
            } catch (Exception e) {
                System.out.println("Kliknutí na wrapper <a> selhalo, zkusíme JavaScript klik...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmHeading);
            }

            // Počkej, až se načte stránka filmu
            wait.until(ExpectedConditions.titleContains("Kate Winslet"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }
    }

    @Test
    public void fdbSearchTestJen() {
        driver.get("https://www.fdb.cz/");

        try {
            // Přijetí cookies
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Přijmout vše')]")
            ));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        try {
            // Vyhledání herečky Reese Witherspoon
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Jennifer Aniston");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Jennifer Aniston"));
            Assertions.assertTrue(driver.getPageSource().contains("Jennifer Aniston"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Přátelé')]")
            ));

            // Zvýrazni a scrollni na něj
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'; arguments[0].scrollIntoView(true);", filmHeading);
            System.out.println("Text filmu nalezen: " + filmHeading.getText());

            // Najdi wrapper <a> a klikni
            try {
                WebElement parentLink = filmHeading.findElement(By.xpath("./ancestor::a[1]"));
                System.out.println("Href odkazu: " + parentLink.getAttribute("href"));
                wait.until(ExpectedConditions.elementToBeClickable(parentLink));
                parentLink.click();
                System.out.println("Kliknutí na odkaz proběhlo.");
            } catch (Exception e) {
                System.out.println("Kliknutí na wrapper <a> selhalo, zkusíme JavaScript klik...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmHeading);
            }

            // Počkej, až se načte stránka filmu
            wait.until(ExpectedConditions.titleContains("Přátelé"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }
    }

    @Test
    public void fdbSearchTestJen2() {
        driver.get("https://www.fdb.cz/");

        try {
            // Přijetí cookies
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Přijmout vše')]")
            ));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        try {
            // Vyhledání herečky Reese Witherspoon
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Jennifer Aniston");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Jennifer Aniston"));
            Assertions.assertTrue(driver.getPageSource().contains("Jennifer Aniston"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'The Morning Show')]")
            ));

            // Zvýrazni a scrollni na něj
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'; arguments[0].scrollIntoView(true);", filmHeading);
            System.out.println("Text filmu nalezen: " + filmHeading.getText());

            // Najdi wrapper <a> a klikni
            try {
                WebElement parentLink = filmHeading.findElement(By.xpath("./ancestor::a[1]"));
                System.out.println("Href odkazu: " + parentLink.getAttribute("href"));
                wait.until(ExpectedConditions.elementToBeClickable(parentLink));
                parentLink.click();
                System.out.println("Kliknutí na odkaz proběhlo.");
            } catch (Exception e) {
                System.out.println("Kliknutí na wrapper <a> selhalo, zkusíme JavaScript klik...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmHeading);
            }

            // Počkej, až se načte stránka filmu
            wait.until(ExpectedConditions.titleContains("The Morning Show"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }

    }

    @Test
    public void fdbSearchTestJen3() {
        driver.get("https://www.fdb.cz/");

        try {
            // Přijetí cookies
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Přijmout vše')]")
            ));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie lišta se nezobrazila nebo už byla skrytá.");
        }

        try {
            // Vyhledání herečky Reese Witherspoon
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Jennifer Aniston");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Jennifer Aniston"));
            Assertions.assertTrue(driver.getPageSource().contains("Jennifer Aniston"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Zkus mě rozesmát')]")
            ));

            // Zvýrazni a scrollni na něj
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'; arguments[0].scrollIntoView(true);", filmHeading);
            System.out.println("Text filmu nalezen: " + filmHeading.getText());

            // Najdi wrapper <a> a klikni
            try {
                WebElement parentLink = filmHeading.findElement(By.xpath("./ancestor::a[1]"));
                System.out.println("Href odkazu: " + parentLink.getAttribute("href"));
                wait.until(ExpectedConditions.elementToBeClickable(parentLink));
                parentLink.click();
                System.out.println("Kliknutí na odkaz proběhlo.");
            } catch (Exception e) {
                System.out.println("Kliknutí na wrapper <a> selhalo, zkusíme JavaScript klik...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filmHeading);
            }

            // Počkej, až se načte stránka filmu
            wait.until(ExpectedConditions.titleContains("Zkus mě rozesmát"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }

    }

}







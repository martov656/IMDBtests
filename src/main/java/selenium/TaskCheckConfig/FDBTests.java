package selenium.TaskCheckConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.BasedSharedMethods;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
            assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Herečka nebyla nalezena.");

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
            assertTrue(driver.getPageSource().contains("Reese Witherspoon"), "Herečka nebyla nalezena.");

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
            assertTrue(driver.getPageSource().contains("Kate Beckinsale"), "Herečka nebyla nalezena.");

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
            assertTrue(driver.getPageSource().contains("Kate Beckinsale"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Pravda je jen slovo')]")
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
            wait.until(ExpectedConditions.titleContains("Pravda je jen slovo"));

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
            assertTrue(driver.getPageSource().contains("Kate Winslet"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Titanic')]")
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
            wait.until(ExpectedConditions.titleContains("Titanic"));

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
            assertTrue(driver.getPageSource().contains("Jennifer Aniston"), "Herečka nebyla nalezena.");

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
            assertTrue(driver.getPageSource().contains("Jennifer Aniston"), "Herečka nebyla nalezena.");

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
            assertTrue(driver.getPageSource().contains("Jennifer Aniston"), "Herečka nebyla nalezena.");

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


    @Test
    public void fdbSearchTestChuck() {
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
            // Vyhledání umělce
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Chuck Norris");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků
            wait.until(ExpectedConditions.titleContains("Chuck Norris"));
            assertTrue(driver.getPageSource().contains("Chuck Norris"), "Herec nebyl nalezen.");

            // Najdi film podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Expendables: Postradatelní 2')]")
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
            wait.until(ExpectedConditions.titleContains("Expendables: Postradatelní 2"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }

    }

    @Test
    public void fdbSearchTestChuck2() {
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
            // Vyhledání umělce
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchBox.clear();
            searchBox.sendKeys("Chuck Norris");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků
            wait.until(ExpectedConditions.titleContains("Chuck Norris"));
            assertTrue(driver.getPageSource().contains("Chuck Norris"), "Herec nebyl nalezen.");

            // Najdi film podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Walker, Texas Ranger')]")
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
            wait.until(ExpectedConditions.titleContains("Walker, Texas Ranger"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());
        }

    }

    @Test
    public void fdbSearchTestVic() {
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
            searchBox.sendKeys("Victoria Silvstedt");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Victoria Silvstedt"));
            assertTrue(driver.getPageSource().contains("Victoria Silvstedt"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Šílenci na prknech')]")
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
            wait.until(ExpectedConditions.titleContains("Šílenci na prknech"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());


        }


    }

    @Test
    public void fdbSearchTestVic2() {
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
            searchBox.sendKeys("Victoria Silvstedt");
            searchBox.submit();

            // Počkej, až se načte stránka výsledků s Reese Witherspoon v titulku
            wait.until(ExpectedConditions.titleContains("Victoria Silvstedt"));
            assertTrue(driver.getPageSource().contains("Victoria Silvstedt"), "Herečka nebyla nalezena.");

            // Najdi film „Voda pro slony“ podle <h3>
            WebElement filmHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(),'Plnou parou vzad')]")
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
            wait.until(ExpectedConditions.titleContains("Plnou parou vzad"));

            // Scroll dolů, aby byl odkaz viditelný
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        } catch (Exception e) {
            Assertions.fail("Test selhal: " + e.getMessage());


        }

    }


    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.fdb.cz");
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Přijmout cookies, pokud je tlačítko dostupné
            try {
                WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Přijmout vše')]")));
                cookiesButton.click();
            } catch (TimeoutException ignored) {
            }

            // Kliknutí na „Můj účet“
            WebElement myAccountButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.flex.items-center.justify-center.gap-1")));
            myAccountButton.click();

            // Vyplnění přihlašovacího formuláře
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='E-mail']")));
            emailInput.sendKeys("martin.tovarek@seznam.cz");

            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Heslo']")));
            passwordInput.sendKeys("Whitesnake1987");

            // Odeslat přihlášení (dle aktuálního formuláře – tady příklad s Enter)
            passwordInput.sendKeys(Keys.ENTER);


            System.out.println("Přihlášení proběhlo úspěšně.");

        } catch (TimeoutException e) {
            System.err.println("Některý z kroků se nepodařilo provést včas.");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void logoutFromFdb() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.fdb.cz");
            driver.manage().window().maximize();

            // Přeskočíme cookies
            try {
                WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Přijmout vše')]")));
                cookiesButton.click();
            } catch (TimeoutException ignored) {
            }

            // Přihlášení (přeskočeno - předpokládám, že už jsi přihlášen)
            WebElement myAccountButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.flex.items-center.justify-center.gap-1")));
            myAccountButton.click();

            // Vyplnění přihlašovacího formuláře
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='E-mail']")));
            emailInput.sendKeys("martin.tovarek@seznam.cz");

            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Heslo']")));
            passwordInput.sendKeys("Whitesnake1987");

            passwordInput.sendKeys(Keys.ENTER);

            // Klikni na profil (přezdívku)
            WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a[href='/muj-ucet']")));
            profileLink.click();

            // Klikni na tlačítko „Odhlásit se“
            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Odhlásit se')]")));
            logoutButton.click();

            System.out.println("Úspěšně odhlášeno.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }


    @Test
    public void testOpenZebrickyAndClickSawFilm() {
        String filmTitle = "Pretty Woman";
        driver.get("https://www.fdb.cz/");


        try {
            WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Přijmout vše')]")));
            cookiesButton.click();
        } catch (TimeoutException ignored) {
        }

        // Kliknutí na „Žebříčky“ v menu
        WebElement zebrickymenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Žebříčky')]")
        ));
        zebrickymenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Žebříčky') or contains(text(),'filmy')]")
        ));


        boolean found = false;

        // Procházení jednotlivých stránek žebříčků (max 5)
        for (int i = 1; i <= 5; i++) {
            String url = "https://www.fdb.cz/zebricky?stranka=" + i;
            driver.get(url);
            System.out.println("Hledám film na stránce " + i + ": " + url);


            List<WebElement> films = driver.findElements(By.xpath("//p[contains(text(),'" + filmTitle + "')]"));

            if (!films.isEmpty()) {
                System.out.println("Film nalezen na stránce " + i);
                WebElement filmBlock = films.get(0);
                WebElement clickable = filmBlock.findElement(By.xpath("./ancestor::a"));

                ((JavascriptExecutor) driver).executeScript(
                        "window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY - 150);", clickable
                );


                wait.until(ExpectedConditions.visibilityOf(clickable));
                wait.until(ExpectedConditions.elementToBeClickable(clickable));

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickable);
                found = true;
                break;
            } else {
                System.out.println("Film nenalezen na stránce " + i);
            }
        }

    }



    @Test
    public void testOpenZebrickyAndClickSawFilm2()  {
        String filmTitle = "Pád letu A800";
        driver.get("https://www.fdb.cz/");


        try {
            WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Přijmout vše')]")));
            cookiesButton.click();
        } catch (TimeoutException ignored) {
        }

        // Kliknutí na „Žebříčky“ v menu
        WebElement zebrickymenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Žebříčky')]")
        ));
        zebrickymenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Žebříčky') or contains(text(),'filmy')]")
        ));


        boolean found = false;

        // Procházení jednotlivých stránek žebříčků (max 5)
        for (int i = 1; i <= 5; i++) {
            String url = "https://www.fdb.cz/zebricky?stranka=" + i;
            driver.get(url);
            System.out.println("Hledám film na stránce " + i + ": " + url);


            List<WebElement> films = driver.findElements(By.xpath("//p[contains(text(),'" + filmTitle + "')]"));

            if (!films.isEmpty()) {
                System.out.println("Film nalezen na stránce " + i);
                WebElement filmBlock = films.get(0);
                WebElement clickable = filmBlock.findElement(By.xpath("./ancestor::a"));

                ((JavascriptExecutor) driver).executeScript(
                        "window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY - 150);", clickable
                );


                wait.until(ExpectedConditions.visibilityOf(clickable));
                wait.until(ExpectedConditions.elementToBeClickable(clickable));

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickable);
                found = true;
                break;
            } else {
                System.out.println("Film nenalezen na stránce " + i);
            }
        }

    }

}


















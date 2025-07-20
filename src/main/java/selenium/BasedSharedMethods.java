package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class BasedSharedMethods {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Inicializace driveru před každým testem
        System.setProperty("webdriver.gecko.driver", "C:\\CodersLabSelenium\\geckodriver-v0.36.0-win64\\geckodriver.exe");
        driver = new ChromeDriver();
        //driver = new EdgeDriver();
        //driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        openOnSecondScreen_ForLecturerDemoOnly();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        waitBeforeClosing_ForLecturerDemoOnly();
        // Ukončit prohlížeč po testu
        if (driver != null)
            driver.quit();
    }

    // *********************** helper methods *************************

    protected void openOnSecondScreen_ForLecturerDemoOnly() {
        if (IsOnLecturerPc()) {
            driver.manage().window().setPosition(new Point(-2000, 0));
        }
    }

    protected void waitBeforeClosing_ForLecturerDemoOnly() {
        if (IsOnLecturerPc()) {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void wait2Sec_ForLecturerDemoOnly() {
        if (IsOnLecturerPc()) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean IsOnLecturerPc() {
        boolean inLecturerPc = false;
        File file = new File(System.getProperty("user.dir") + File.separator + "runInLecturerPc.txt");
        if (file.exists()) {
            inLecturerPc = true;
        }
        return inLecturerPc;
    }
}




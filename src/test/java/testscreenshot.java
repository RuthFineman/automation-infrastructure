import driver.MyDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import screenshot.TakeScreenshotOnFailure;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TakeScreenshotOnFailure(screenshot = true, pageSource = true)
public class testscreenshot {
    static WebDriver driver;

    @BeforeEach
    public void setup() {

        driver = new ChromeDriver();
        MyDriverManager.setDriver(driver);
        driver.get("https://demoqa.com/");
    }

    @AfterAll
    public static void teardown() {
        MyDriverManager.quitDriver();
    }

    @Test
    public void testFailingExample() {
        // טסט מכוון כישלון
           assertTrue(MyDriverManager.getDriver().findElement(By.id("nonexistent")).isDisplayed());
      //  assertTrue(true);
    }
}

import grid.GridDriverManager;
import grid.SeleniumGridConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

@Execution(ExecutionMode.CONCURRENT)
public class GridTest {

    @Test
    void testChrome() throws MalformedURLException {
        // קונפיגורציה ל‑Chrome
        SeleniumGridConfig gridConfig = new SeleniumGridConfig("chrome");
        WebDriver driver = GridDriverManager.getDriver();
        System.out.println("Chrome Thread: " + Thread.currentThread().getId());

        driver.get("https://www.google.com");
        System.out.println("Chrome Title: " + driver.getTitle());

        // סגירת הדרייבר אחרי הבדיקה
        GridDriverManager.quitDriver();
    }

    @Test
    void testEdge() throws MalformedURLException {
        // קונפיגורציה ל‑Edge
        SeleniumGridConfig gridConfig = new SeleniumGridConfig("edge");
        WebDriver driver = GridDriverManager.getDriver();
        System.out.println("Edge Thread: " + Thread.currentThread().getId());

        driver.get("https://www.google.com");
        System.out.println("Edge Title: " + driver.getTitle());

        // סגירת הדרייבר אחרי הבדיקה
        GridDriverManager.quitDriver();
    }
//    @Test
//    void testFirefox() throws MalformedURLException {
//        // קונפיגורציה ל‑Firefox
//        SeleniumGridConfig gridConfig = new SeleniumGridConfig("firefox");
//        WebDriver driver = GridDriverManager.getDriver();
//        System.out.println("Firefox Thread: " + Thread.currentThread().getId());
//
//        driver.get("https://www.google.com");
//        System.out.println("Firefox Title: " + driver.getTitle());
//
//        // סגירת הדרייבר אחרי הבדיקה
//        GridDriverManager.quitDriver();
//    }

}

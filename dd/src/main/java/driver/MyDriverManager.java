package driver;
import org.openqa.selenium.WebDriver;

public class MyDriverManager {
    //כדי ליצור מקביליות
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    // קבלת הדרייבר
    public static WebDriver getDriver() {
        return driver.get();
    }

    // סגירת הדפדפן
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            //driver.remove();
        }
    }
}

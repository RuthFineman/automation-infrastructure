package grid;

import driver.MyDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridConfig {

    private WebDriver driver;

    // Constructor שמקבל את שם הדפדפן
    public SeleniumGridConfig(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setAcceptInsecureCerts(true);

        if (browser.equalsIgnoreCase("chrome")) {
            capabilities.setBrowserName("chrome");
        } else if (browser.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName("firefox");
        }else if (browser.equalsIgnoreCase("edge")) {
            capabilities.setBrowserName("MicrosoftEdge");
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // התחברות ל־Selenium Grid
        URL hubUrl = new URL("http://localhost:4444");
        driver = new RemoteWebDriver(hubUrl, capabilities);
        driver.manage().window().maximize(); // הגדרת גודל חלון
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10)); // המתנה משתמעת

// שים את הדרייבר ב־MyDriverManager כדי לנהל אותו
        GridDriverManager.setDriver(driver);
        // שים את הדרייבר ב־MyDriverManager כדי לנהל אותו
        GridDriverManager.setDriver(driver);
    }

}

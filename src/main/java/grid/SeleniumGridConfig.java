package grid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SeleniumGridConfig {

    public SeleniumGridConfig(String browser) throws MalformedURLException {
        WebDriver driver;

        URL gridUrl = new URL("http://localhost:4444");

        switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                driver = new RemoteWebDriver(gridUrl, options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.setAcceptInsecureCerts(true);
                driver = new RemoteWebDriver(gridUrl, options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.setAcceptInsecureCerts(true);
                driver = new RemoteWebDriver(gridUrl, options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        GridDriverManager.setDriver(driver);
    }
}




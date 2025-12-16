import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import wait.WaitManager;

public class LoginPage {

    private static WebDriver driver;
    private static WaitManager wait;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        wait = new WaitManager(driver);
    }
    @BeforeEach
    public void openPage() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
    }
    @Test
    @DisplayName("Verify wait for visible + clickable + text")
    public void testWaits() {

        // Start button
        By startButton = By.cssSelector("#start button");

        // Wait for clickable
        wait.waitForClickable(startButton).click();

        // Element appears dynamically
        By finishText = By.id("finish");

        // Wait for visible (element becomes visible only after loading completes)
        wait.waitForVisible(finishText);

        // Validate text
        Assertions.assertTrue(
                wait.waitForText(finishText, "Hello World"),
                "Expected text 'Hello World' was not found!"
        );
    }
    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

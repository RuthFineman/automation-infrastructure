package wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WaitManager {
    private final FluentWaitFactory waitFactory;

    public WaitManager(WebDriver driver) {
        this.waitFactory = new FluentWaitFactory(driver);
    }

    // ---------------------------
// Visible
// ---------------------------
    public WebElement waitForVisible(By locator) {
        return waitFactory.createMediumWait().until(Conditions.elementVisible(locator));
    }

    public WebElement waitForVisible(By locator, boolean longWait) {
        return longWait
                ? waitFactory.createLongWait().until(Conditions.elementVisible(locator))
                : waitFactory.createShortWait().until(Conditions.elementVisible(locator));
    }

    // ---------------------------
// Clickable
// ---------------------------
    public WebElement waitForClickable(By locator) {
        return waitFactory.createMediumWait().until(Conditions.elementClickable(locator));
    }

    // ---------------------------
// Invisible
// ---------------------------
    public boolean waitForInvisibility(By locator) {
        return waitFactory.createShortWait().until(Conditions.elementNotVisible(locator));
    }

    // ---------------------------
// Text
// ---------------------------
    public boolean waitForText(By locator, String text) {
        return waitFactory.createMediumWait().until(Conditions.textPresent(locator, text));
    }
}

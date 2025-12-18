package wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.function.Function;

public class Conditions {

    public static Function<WebDriver, WebElement> elementVisible(By locator) {
        return driver -> {
            try {
                WebElement element = driver.findElement(locator);
                return element.isDisplayed() ? element : null;
            } catch (Exception e) {
                return null;
            }
        };
    }

    public static Function<WebDriver, WebElement> elementClickable(By locator) {
        return driver -> {
            try {
                WebElement element = driver.findElement(locator);
                return (element.isDisplayed() && element.isEnabled()) ? element : null;
            } catch (Exception e) {
                return null;
            }
        };
    }


    public static Function<WebDriver, Boolean> elementNotVisible(By locator) {
        return driver -> {
            try {
                return !driver.findElement(locator).isDisplayed();
            } catch (Exception e) {
                return true;
            }
        };
    }

    public static Function<WebDriver, Boolean> textPresent(By locator, String text) {
        return driver -> {
            try {
                WebElement element = driver.findElement(locator);
                return element.isDisplayed() && element.getText().contains(text);
            } catch (Exception e) {
                return false;
            }
        };
    }
}

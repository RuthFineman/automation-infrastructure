package wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class FluentWaitFactory {

    private final WebDriver driver;

    public FluentWaitFactory(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> createShortWait() {
        return createFluentWait(WaitConfig.getShortWait());
    }

    public FluentWait<WebDriver> createMediumWait() {
        return createFluentWait(WaitConfig.getMediumWait());
    }

    public FluentWait<WebDriver> createLongWait() {
        return createFluentWait(WaitConfig.getLongWait());
    }

    private FluentWait<WebDriver> createFluentWait(int seconds) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
    }
}

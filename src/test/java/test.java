import logging.TestLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import retryAnoution.annotations.Retry;
import retryAnoution.extensions.RetryExtension;

@ExtendWith(RetryExtension.class)
public class test {

    @Test
    @Retry(attempts = 7, delay = 3)
    public void test() throws Throwable {
        if (Math.random() < 0.7) {
            throw new RuntimeException("Failed randomly");
        }
        TestLogger.info("Test passed!!###############");
    }
}

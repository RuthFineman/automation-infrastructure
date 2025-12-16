import org.example.retryAnoution.annotations.Retry;
import org.example.retryAnoution.extensions.RetryExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RetryExtension.class)
public class test {

    @Test
    @Retry(attempts = 3, delay = 3)
    public void test() throws Throwable {
        if (Math.random() < 0.7) {
            throw new RuntimeException("Failed randomly");
        }
        System.out.println("Test passed!");
    }
}

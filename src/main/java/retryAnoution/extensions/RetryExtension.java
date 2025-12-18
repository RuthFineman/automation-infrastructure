package retryAnoution.extensions;
import logging.TestLogger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import retryAnoution.annotations.Retry;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;


public class RetryExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Optional<Method> testMethod = context.getTestMethod();
        if (testMethod.isPresent()) {
            Method method = testMethod.get();

            Retry retryAnnotation = method.getAnnotation(Retry.class);

            if (retryAnnotation == null) {

                throw throwable;
            }
            int maxAttempts = retryAnnotation.attempts();
            long delay = retryAnnotation.delay();
            Class<? extends Throwable>[] retryOn = retryAnnotation.retryOn();
            int attempt = 1;

            while (attempt <= maxAttempts) {
                TestLogger.info("Attempt " + attempt + " for method " + method.getName());
                try {
                    method.invoke(context.getRequiredTestInstance());
                    if (attempt > 1) {
                        TestLogger.warn("Test passed on attempt " + attempt + " for method " + method.getName());
                    } else {
                        TestLogger.info("Test passed on first attempt: " + method.getName());
                    }
                    return;
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    boolean shouldRetry = false;
                    for (Class<? extends Throwable> retryClass : retryOn) {
                        if (retryClass.isInstance(cause)) {
                            shouldRetry = true;
                            break;
                        }
                    }

                    if (!shouldRetry || attempt == maxAttempts) {
                        TestLogger.error("Test failed on attempt " + attempt + " for method " + method.getName()
                                + " | Exception: " + cause);
                        throw cause;
                    }
                    TestLogger.warn("Test failed on attempt " + attempt + ", will retry after " + delay + "ms | Exception: " + cause);

                    if (delay > 0) {
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException ex) {
                            TestLogger.error("Interrupted during retry delay: " + ex.getMessage());
                        }
                        attempt++;
                    }
                }
            }
        }
    }
}

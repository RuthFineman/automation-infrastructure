package org.example.retryAnoution.extensions;

import org.example.retryAnoution.annotations.Retry;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

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
                // אין אנוטציה → זרוק את החריג המקורי
                throw throwable;
            }
            int maxAttempts = retryAnnotation.attempts();
            long delay = retryAnnotation.delay();
            Class<? extends Throwable>[] retryOn = retryAnnotation.retryOn();
            int attempt = 1;

            while (attempt <= maxAttempts) {
                System.out.println("Attempt " + attempt + " for method " + method.getName());
                try {
                    // הפעל מחדש את הטסט באמצעות Reflection
                    method.invoke(context.getRequiredTestInstance());
                    return; // הצליח → יוצא מהלולאה
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
                        throw cause; // לא מתאים ל-Retry או זה הניסיון האחרון → זרוק
                    }

                    // אם כן → חכה קצת ואז נסה שוב
                    if (delay > 0) {
                        Thread.sleep(delay);
                    }

                    attempt++;
                }
            }
        }
    }
}

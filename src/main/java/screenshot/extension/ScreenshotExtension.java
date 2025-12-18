package screenshot.extension;
import com.epam.reportportal.service.ReportPortal;
import driver.MyDriverManager;
import logging.TestLogger;
import screenshot.TakeScreenshotOnFailure;
import screenshot.ScreenshotUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import java.io.File;
import java.util.Date;

public class ScreenshotExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TakeScreenshotOnFailure annotation = context.getTestMethod()
                .map(m -> m.getAnnotation(TakeScreenshotOnFailure.class))
                .orElse(context.getTestClass()
                        .map(c -> c.getAnnotation(TakeScreenshotOnFailure.class))
                        .orElse(null));

        if (annotation != null && annotation.screenshot()) {
            try {
                String testName = context.getTestMethod().map(m -> m.getName()).orElse(context.getDisplayName());


                File screenshotFile = ScreenshotUtils.takeScreenshotFile(MyDriverManager.getDriver(), testName);


                File pageSourceFile = ScreenshotUtils.savePageSource(MyDriverManager.getDriver(), testName);


                ReportPortal.emitLog("Screenshot on failure: " + testName, "ERROR", new Date(), screenshotFile);
                ReportPortal.emitLog("Page Source on failure: " + testName, "ERROR", new Date(), pageSourceFile);
                TestLogger.error("Screenshot saved locally at: " + screenshotFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        String testName = context.getTestMethod().map(m -> m.getName()).orElse(context.getDisplayName());

        TestLogger.setContext("testName", testName);
        TestLogger.info("Test passed");
        TestLogger.clearContext();
    }

    @Override
    public void testDisabled(ExtensionContext context, java.util.Optional<String> reason) {
        String testName = context.getTestMethod().map(m -> m.getName()).orElse(context.getDisplayName());
        TestLogger.info("Test disabled: " + testName + " - Reason: " + reason.orElse("No reason"));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String testName = context.getTestMethod().map(m -> m.getName()).orElse(context.getDisplayName());
        TestLogger.warn("Test aborted: " + testName + " - Cause: " + cause.getMessage());
    }
}


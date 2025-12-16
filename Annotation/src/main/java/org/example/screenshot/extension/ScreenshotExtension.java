package org.example.screenshot.extension;
import com.epam.reportportal.service.ReportPortal;
import driver.MyDriverManager;
import org.example.screenshot.TakeScreenshotOnFailure;
import org.example.screenshot.ScreenshotUtils;
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

                // צילום מסך ושמירה זמנית
                File screenshotFile = ScreenshotUtils.takeScreenshotFile(MyDriverManager.getDriver(), testName);

                // שמירת קוד HTML זמני של העמוד
                File pageSourceFile = ScreenshotUtils.savePageSource(MyDriverManager.getDriver(), testName);

                // שליחה ל-ReportPortal
                ReportPortal.emitLog("Screenshot on failure: " + testName, "ERROR", new Date(), screenshotFile);
                ReportPortal.emitLog("Page Source on failure: " + testName, "ERROR", new Date(), pageSourceFile);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

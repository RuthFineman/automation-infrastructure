package org.example.screenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static File takeScreenshotFile(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

            // יצירת תיקייה אם לא קיימת
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));

            return new File(screenshotPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }

    public static File savePageSource(WebDriver driver, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String pageSourcePath = "screenshots/" + testName + "_" + timestamp + ".html";

            Files.createDirectories(Paths.get("screenshots"));
            String pageSource = driver.getPageSource();
            Files.write(Paths.get(pageSourcePath), pageSource.getBytes(StandardCharsets.UTF_8));

            return new File(pageSourcePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save page source", e);
        }
    }
}

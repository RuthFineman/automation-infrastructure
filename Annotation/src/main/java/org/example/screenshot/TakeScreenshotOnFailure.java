package org.example.screenshot;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.example.screenshot.extension.ScreenshotExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(ReportPortalExtension.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@ExtendWith(ScreenshotExtension.class)
public @interface TakeScreenshotOnFailure {
    boolean screenshot() default true;
    boolean pageSource() default false;
}

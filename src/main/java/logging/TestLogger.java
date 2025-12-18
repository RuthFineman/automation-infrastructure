package logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class TestLogger {

    private static final Logger logger =
            LoggerFactory.getLogger("AUTOMATION");

    public static void trace(String msg) {
        logger.trace(msg);
    }

    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void warn(String msg) {
        logger.warn(msg);
    }

    public static void error(String msg) {
        logger.error(msg);
    }


    public static void fatal(String msg) {
        logger.error("FATAL: " + msg);
    }

    public static void setContext(String key, String value) {
        MDC.put(key, value);
    }

    public static void clearContext() {
        MDC.clear();
    }

}

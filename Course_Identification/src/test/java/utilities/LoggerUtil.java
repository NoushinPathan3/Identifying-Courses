package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Centralized logger utility using Log4j2
 * Thread-safe for parallel execution
 */
public class LoggerUtil {

    private static ThreadLocal<Logger> tlLogger = new ThreadLocal<>();

    public static Logger getLogger() {
        Logger logger = tlLogger.get();
        if (logger == null) {
            logger = LogManager.getLogger(Thread.currentThread().getName());
            tlLogger.set(logger);
        }
        return logger;
    }

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void error(String message) {
        getLogger().error(message);
    }

    public static void warn(String message) {
        getLogger().warn(message);
    }
}
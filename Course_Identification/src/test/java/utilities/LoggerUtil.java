package utilities;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Centralized logger utility for Coursera project.
 * Uses Log4j 1.x with property configuration.
 */
public class LoggerUtil {

    public static Logger logger = Logger.getLogger("CourseraLogger");

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }
}
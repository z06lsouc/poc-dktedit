package test.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private LogUtils() {
        // This is an util class, it should not never be instantiated
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static void logSomething(String message) {
        LOGGER.info("Something to log- {}", message);
    }
}

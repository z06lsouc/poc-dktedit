package test.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);

    private LogUtils() {
        // This is an util class, it should not never be instantiated
    }

    public static void logSomething(String message) {
       if( StringUtils.isNotBlank(message)) {
            LOGGER.info("Something to log - {}", message);
        }
    }
}

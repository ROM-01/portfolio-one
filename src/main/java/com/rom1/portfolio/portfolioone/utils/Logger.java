package com.rom1.portfolio.portfolioone.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Logger {

    @Value("${application.debug}")
    private boolean debugMode;
    
    private static Logger logger;

    private String loggingLevel = "INFO";

    public static Logger getLogger() {
        if (Objects.isNull(logger)) logger = new Logger();
        return logger;
    }

    public static Logger getLogger(String loggingLevel) {
        if (Objects.isNull(logger)) logger = new Logger();
        logger.loggingLevel = loggingLevel.toUpperCase();
        return logger;
    }

    public void printMessage(String message) {
        if (!debugMode) return;
        printWithTimestamp(message);
    }

    private void printWithTimestamp(String message) {
        var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var now = LocalDateTime.now();
        var printout = "[&s] [ %s ] %s%n".formatted(dtf.format(now), loggingLevel, message);
        System.out.println(printout);
    }
}

package com.reizx.andrutil.log;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

/**
 * Logback中LogcatAppender的构建器
 */
public class LogcatAppenderBuilder {
    private LoggerContext logContext;//日志Context
    private String pattern;//模式
    private String name;//名称

    public LogcatAppenderBuilder() {
        logContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        pattern = "%-5relative [%thread][%file:%M:%line] - %msg%n";
        name = "andrutil_logcat";
    }

    public static LogcatAppenderBuilder newBuilder() {
        return new LogcatAppenderBuilder();
    }

    public LogcatAppenderBuilder pattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public LogcatAppenderBuilder name(String name) {
        this.name = name;
        return this;
    }

    public LogcatAppender build() {
        LogcatAppender logcatAppender = new LogcatAppender();
        logcatAppender.setContext(logContext);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        encoder.setContext(logContext);
        encoder.start();

        logcatAppender.setEncoder(encoder);
        logcatAppender.setName(name);
        logcatAppender.start();
        return logcatAppender;
    }
}

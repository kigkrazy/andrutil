package com.reizx.andrutil.log;

import android.annotation.SuppressLint;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

public class RollingFileAppenderBuilder {
    private LoggerContext logContext;//日志Context

    private String logPattern;//模式
    private String filePattern;//模式
    private String name;//名称
    private boolean append;//
    private int history;//最大保存天数
    private String maxFileSize;

    @SuppressLint({"DefaultLocale", "SdCardPath"})
    public RollingFileAppenderBuilder() {
        logContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        logPattern = "%-5relative [%thread][%file:%M:%line] - %msg%n";
        filePattern = "/sdcard/andrutil/andrutil.%d{yyyy-MM-dd}.log";
        name = "andrutil_rolling_file";
        append = true;
        history = 5;
        maxFileSize = "10MB";
    }

    public static RollingFileAppenderBuilder newBuilder() {
        return new RollingFileAppenderBuilder();
    }

    public RollingFileAppenderBuilder logPattern(String pattern) {
        this.logPattern = pattern;
        return this;
    }

    public RollingFileAppenderBuilder filePattern(String pattern) {
        this.filePattern = pattern;
        return this;
    }

    public RollingFileAppenderBuilder append(boolean append) {
        this.append = append;
        return this;
    }

    public RollingFileAppenderBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RollingFileAppenderBuilder history(int history) {
        this.history = history;
        return this;
    }

    public RollingFileAppenderBuilder maxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
        return this;
    }

    public RollingFileAppender build() {
        RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<ILoggingEvent>();
        rollingFileAppender.setAppend(append);
        rollingFileAppender.setName(name);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern(logPattern);
        encoder.setContext(logContext);
        encoder.start();
        rollingFileAppender.setEncoder(encoder);

        TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<ILoggingEvent>();
        rollingPolicy.setFileNamePattern(filePattern);
        rollingPolicy.setMaxHistory(history);
        rollingPolicy.setParent(rollingFileAppender);  // parent and context required!
        rollingPolicy.setContext(logContext);
        rollingPolicy.start();
        rollingFileAppender.setRollingPolicy(rollingPolicy);


        SizeBasedTriggeringPolicy<ILoggingEvent> triggeringPolicy = new SizeBasedTriggeringPolicy<>();
        triggeringPolicy.setMaxFileSize(maxFileSize);
        triggeringPolicy.start();
        rollingFileAppender.setTriggeringPolicy(triggeringPolicy);

        rollingFileAppender.setEncoder(encoder);
        rollingFileAppender.setName(name);
        rollingFileAppender.start();
        return rollingFileAppender;
    }

}

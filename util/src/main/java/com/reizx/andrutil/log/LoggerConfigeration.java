package com.reizx.andrutil.log;


import android.util.Log;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

/**
 * 使用方法
 *
 * 基于logback框架的一个封装
 */
public class LoggerConfigeration {
    private static LoggerConfigeration loggerConfigeration;
    Logger root;
    Level lv;
    List<Appender<ILoggingEvent>> appenders = new ArrayList<>();

    public static boolean isConfig = false;//是否已经构建过了

    private LoggerConfigeration() throws Exception {
        if (isConfig)
            throw new Exception("you have already config the logback.");
        root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        lv = Level.TRACE;
    }

    public static LoggerConfigeration newConfigeration() throws Exception {
        if (loggerConfigeration == null) {
            loggerConfigeration = new LoggerConfigeration();
        }
        return loggerConfigeration;
    }

    public LoggerConfigeration level(Level lv) {
        this.lv = lv;
        return this;
    }

    public LoggerConfigeration addAppend(Appender<ILoggingEvent> newAppender) {
        appenders.add(newAppender);
        return this;
    }

    /**
     * 这个函数只能调用一次
     */
    public void config() {
        if (isConfig){
            Log.d(LoggerConfigeration.class.getName(), "you has already build logger config.");
            return;
        }

        root.setLevel(Level.TRACE);

        for (Appender<ILoggingEvent> appender : appenders){
            root.addAppender(appender);
        }
        isConfig = true;
    }
}

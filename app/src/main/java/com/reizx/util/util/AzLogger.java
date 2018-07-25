package com.reizx.util.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AzLogger {
    /**
     * 获得Logger
     *
     * @param clazz 日志发出的类
     * @return Logger
     */
    public static Logger get(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 获得Logger
     *
     * @param name 自定义的日志发出者名称
     * @return Logger
     */

    public static Logger get(String name) {
        return LoggerFactory.getLogger(name);
    }

    /**
     * @return 获得日志，自动判定日志发出者
     */

    public static Logger get() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return LoggerFactory.getLogger(stackTrace[2].getClassName());
    }

    /**
     * Trace等级日志，小于debug<br>
     * <p/>
     * <p/>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void trace(String format, Object... arguments) {
        if (GlobalData.isDebug) {
            trace(innerGet(), format, arguments);
        }
    }

    /**
     * Trace等级日志，小于Debug
     *
     * @param log       日志对象
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void trace(Logger log, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            log.trace(format, arguments);
        }
    }

    /**
     * Debug等级日志，小于Info<br>
     * <p/>
     * <p/>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void debug(String format, Object... arguments) {
        if (GlobalData.isDebug) {
            debug(innerGet(), format, arguments);
        }
    }

    /**
     * Debug等级日志，小于Info
     *
     * @param log       日志对象
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void debug(Logger log, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            log.debug(format, arguments);
        }
    }

    /**
     * Info等级日志，小于Warn<br>
     * <p/>
     * <p/>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void info(String format, Object... arguments) {
        if (GlobalData.isDebug) {
            info(innerGet(), format, arguments);
        }
    }

    /**
     * Info等级日志，小于Warn
     *
     * @param log       日志对象
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void info(Logger log, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            log.info(format, arguments);
        }
    }

    /**
     * Warn等级日志，小于Error<br>
     * <p/>
     * <p/>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void warn(String format, Object... arguments) {
        if (GlobalData.isDebug) {
            warn(innerGet(), format, arguments);
        }
    }


    /**
     * Warn等级日志，小于Error
     *
     * @param log       日志对象
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void warn(Logger log, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            log.warn(format, arguments);
        }
    }


    /**
     * Warn等级日志，小于Error<br>
     * <p/>
     * <p/>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param e         需在日志中堆栈打印的异常
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void warn(Throwable e, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            warn(innerGet(), e, format(format, arguments));
        }
    }

    /**
     * Warn等级日志，小于Error
     *
     * @param log       日志对象
     * @param e         需在日志中堆栈打印的异常
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void warn(Logger log, Throwable e, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            log.warn(format(format, arguments), e);
        }
    }

    /**
     * Error等级日志<br>
     * <p/>
     * <p/>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void error(String format, Object... arguments) {
        if (GlobalData.isDebug) {
            error(innerGet(), format, arguments);
        }
    }

    /**
     * Error等级日志<br>
     *
     * @param log       日志对象
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void error(Logger log, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            log.error(format, arguments);
        }
    }

    /**
     * Error等级日志<br>
     * <p/>
     * <p/>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param e         需在日志中堆栈打印的异常
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void error(Throwable e, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            error(innerGet(), e, format(format, arguments));
        }
    }


    /**
     * Error等级日志<br>
     * <p/>
     * <p/>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param log       日志对象
     * @param e         需在日志中堆栈打印的异常
     * @param format    格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */

    public static void error(Logger log, Throwable e, String format, Object... arguments) {
        if (GlobalData.isDebug) {
            log.error(format(format, arguments), e);
        }
    }

    /**
     * 格式化文本
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   参数值
     * @return 格式化后的文本
     */

    private static String format(String template, Object... values) {
        return String.format(template.replace("{}", "%s"), values);
    }

    /**
     * @return 获得日志，自动判定日志发出者
     */
    private static Logger innerGet() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return LoggerFactory.getLogger(stackTrace[3].getClassName());
    }


    public static class GlobalData{
        public static boolean isDebug = true;
    }
}

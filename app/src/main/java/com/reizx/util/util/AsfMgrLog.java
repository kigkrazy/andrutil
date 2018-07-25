package com.reizx.util.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Printer;

import org.joor.Reflect;


/**
 * 使用
 *      FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
 *          .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
 *          .methodCount(0)         // (Optional) How many method line to show. Default 2
 *          //.methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
 *          .tag("zues-rium")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
 *          .build();
 *      AsfMgrLog.addLogAdapter(new AndroidLogAdapter(formatStrategy));//默认的安卓打印
 *
 *      //打印带标签的log
 *      AsfMgrLog.d("ffff");
 *      //打印带临时标签的log
 *      AsfMgrLog.t("tmp-tag").d("ffff");
 */
public class AsfMgrLog {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

    @NonNull
    private static Printer printer;

    static {
        // 反射初始化
        printer = Reflect.on("com.orhanobut.logger.LoggerPrinter").create().get();
    }

    private AsfMgrLog() {
        //no instance
    }

    public static void printer(@NonNull Printer printer) {
        AsfMgrLog.printer = checkNotNull(printer);
    }

    public static void addLogAdapter(@NonNull LogAdapter adapter) {
        printer.addAdapter(checkNotNull(adapter));
    }

    public static void clearLogAdapters() {
        printer.clearLogAdapters();
    }

    /**
     * Given tag will be used as tag only once for this method call regardless of the tag that's been
     * set during initialization. After this invocation, the general tag that's been set will
     * be used for the subsequent log calls
     */
    public static Printer t(@Nullable String tag) {
        return printer.t(tag);
    }

    /**
     * General log function that accepts all configurations as parameter
     */
    public static void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        printer.d(message, args);
    }

    public static void d(@Nullable Object object) {
        printer.d(object);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        printer.e(null, message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        printer.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        printer.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        printer.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        printer.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        printer.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        printer.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        printer.xml(xml);
    }

    @NonNull
    static <T> T checkNotNull(@Nullable final T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}

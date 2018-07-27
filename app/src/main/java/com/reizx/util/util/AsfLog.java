package com.reizx.util.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.naming.FileNameGenerator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Printer androidPrinter = new AndroidPrinter();                       // 通过 android.util.Log 打印日志的打印器
 * AsfLog.HistoryDateFileNameGenerator fileNameGenerator = new AsfLog.HistoryDateFileNameGenerator(3, "/sdcard/sdf/");
 * Printer filePrinter = new FilePrinter                                // 打印日志到文件的打印器
 * .Builder("/sdcard/asf/")                                             // 指定保存日志文件的路径
 * .fileNameGenerator(fileNameGenerator)                                // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
 * .backupStrategy(new FileSizeBackupStrategy(50))                      // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
 * .logFlattener(new DefaultFlattener())                                // 指定日志平铺器，默认为 DefaultFlattener
 * .build();
 * AsfLog.Setter.newSetter()
 * .tag("asf-log")
 * .printers(androidPrinter, filePrinter)
 * .set();
 */
public class AsfLog {
    private static Logger logger;

    static {
        Printer androidPrinter = new AndroidPrinter();
        logger = new Logger.Builder()
                .nt()
                .tag("xlog")
                .nb()
                .nst()
                .printers(androidPrinter)
                .build();
    }


    public static void d(@NonNull String message, @Nullable Object... args) {
        logger.d(message, args);
    }


    public static void i(@NonNull String message, @Nullable Object... args) {
        logger.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        logger.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        logger.w(message, args);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        logger.e(null, message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        logger.xml(xml);
    }


    public static class Setter {
        String tag;
        com.elvishew.xlog.printer.Printer[] printers;

        private Setter() {
        }

        public static Setter newSetter() {
            return new Setter();
        }

        public Setter tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Setter printers(Printer... printers) {
            this.printers = printers;
            return this;
        }

        public void set() {
            XLog.init(LogLevel.ALL);//初始化否则报错

            if (printers == null) {
                printers = new Printer[]{new AndroidPrinter()};
            }

            logger = XLog.tag(tag)
                    .nt()
                    .nb()
                    .nst()
                    .printers(printers)
                    .build();
        }
    }

    public static class HistoryDateFileNameGenerator implements FileNameGenerator {
        int history;
        String logdir;

        ThreadLocal<SimpleDateFormat> localDateFormat = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            }
        };

        /**
         * @param history 保存历史天数
         * @param logdir  log目录
         */
        public HistoryDateFileNameGenerator(int history, String logdir) {
            this.logdir = logdir;

            if (history <= 0) {
                this.history = 3;
            } else {
                this.history = history;
            }
        }

        @Override
        public boolean isFileNameChangeable() {
            return true;
        }

        /**
         * Generate a file name which represent a specific date.
         * 此处设置保留几天的任务
         */
        @Override
        public String generateFileName(int logLevel, long timestamp) {
            SimpleDateFormat sdf = localDateFormat.get();
            sdf.setTimeZone(TimeZone.getDefault());
            String now = sdf.format(new Date(timestamp));
            deleteHistory(sdf, now);
            return now;
        }

        /**
         * 删除7天前的数据
         */
        public void deleteHistory(SimpleDateFormat sdf, String now) {
            List<File> files = FileUtils.listFilesInDir(logdir);
            if (files.size() <= history)
                return;

            for (File file : files) {
                String name = file.getName();
                if (!isDate(sdf, name)) {
                    //文件名格式错误，直接删除
                    FileUtils.deleteFile(file);
                    continue;
                }

                long span = TimeUtils.getTimeSpan(name, now, sdf, TimeConstants.DAY);
                if (span < 3) {
                    FileUtils.deleteFile(file);
                }
            }
        }

        /**
         * 校验date格式是否正确
         *
         * @param sdf
         * @param date
         * @return
         */
        public boolean isDate(SimpleDateFormat sdf, String date) {
            try {
                sdf.parse(date);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}

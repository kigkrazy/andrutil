package com.reizx.util.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.DefaultFlattener;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy;
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy;
import com.reizx.util.di.component.AppComponent;
import com.reizx.util.di.component.DaggerAppComponent;
import com.reizx.util.di.module.AppModule;
import com.reizx.util.di.module.HttpModule;
import com.reizx.util.util.AsfLog;

/**
 * j
 * Created by kigkrazy on 18-5-10.
 */

public class App extends Application {
    private static App app;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化日志环境，设置全局
        Log.d("Ares-Mgr", "Ares-Mgr onCreate...");
        initLog();
        Utils.init(this);//初始化AndroidUtilCode库
    }

    @SuppressLint("SdCardPath")
    public void initLog() {
        //XLog.init(LogLevel.ALL);
        Printer androidPrinter = new AndroidPrinter();                          // 通过 android.util.Log 打印日志的打印器
        AsfLog.HistoryDateFileNameGenerator fileNameGenerator = new AsfLog.HistoryDateFileNameGenerator(3, "/sdcard/asf/");
        Printer filePrinter = new FilePrinter                                   // 打印日志到文件的打印器
                .Builder("/sdcard/asf/")                            // 指定保存日志文件的路径
                .fileNameGenerator(fileNameGenerator)                           // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
                .backupStrategy(new NeverBackupStrategy())                      // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
                .logFlattener(new DefaultFlattener())                           // 指定日志平铺器，默认为 DefaultFlattener
                .build();
        AsfLog.Setter.newSetter()
                .tag("asf-log")
                .printers(androidPrinter, filePrinter)
                .set();
    }

    public static App getInstance() {
        return app;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}

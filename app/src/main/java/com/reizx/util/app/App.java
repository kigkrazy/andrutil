package com.reizx.util.app;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.reizx.andrutil.log.LogcatAppenderBuilder;
import com.reizx.andrutil.log.LoggerConfigeration;
import com.reizx.andrutil.log.RollingFileAppenderBuilder;
import com.reizx.util.di.component.AppComponent;
import com.reizx.util.di.component.DaggerAppComponent;
import com.reizx.util.di.module.AppModule;
import com.reizx.util.di.module.HttpModule;
import com.reizx.util.util.AsfMgrLog;

import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.core.FileAppender;

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

    public void initLog() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                //.methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("asf-log")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        AsfMgrLog.addLogAdapter(new AndroidLogAdapter(formatStrategy));//默认的安卓打印

        //============================
        //logback环境初始化
        try {
            //初始化logcat模板
            //一般来说我们只设置name就可以，其余的保持默认
            LogcatAppender logcatAppender = LogcatAppenderBuilder
                    .newBuilder()
                    .name("logcat")//名称
                    .pattern("%-5relative [%thread][%file:%M:%line] - %msg%n")//日志输出模板
                    .build();

            //初始化文件日志写入模板
            //一般来说我们只设置name跟filePattern就可以，其余的保持默认
            FileAppender fileAppender = RollingFileAppenderBuilder
                    .newBuilder()
                    .name("filelog")//名称，默认为“andrutil_rolling_file”
                    .filePattern("/sdcard/andrutil/andrutil.%d{yyyy-MM-dd}.log")//日志文件路径
                    .history(5)//最大日志保存天数，默认为 5
                    .logPattern("%-5relative [%thread][%file:%M:%line] - %msg%n")//log打印模板
                    .maxFileSize("10MB")//最大文件大小，文件大的话会在新文件写入默认为"10MB"
                    .append(true)//是否使用安全模式写入，默认TRUE。多个线程开启写入同一个文件保证多线程安全
                    .build();

            LoggerConfigeration.newConfigeration()
                    .addAppend(logcatAppender)//添加模板
                    .addAppend(fileAppender)//添加模板
                    .config();
        } catch (Exception e) {
            Log.d("logback-init", "logback init error : " + e);
            e.printStackTrace();
        }
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

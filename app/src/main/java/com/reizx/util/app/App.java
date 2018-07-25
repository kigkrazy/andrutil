package com.reizx.util.app;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.reizx.util.di.component.AppComponent;
import com.reizx.util.di.component.DaggerAppComponent;
import com.reizx.util.di.module.AppModule;
import com.reizx.util.di.module.HttpModule;
import com.reizx.util.util.AsfMgrLog;

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

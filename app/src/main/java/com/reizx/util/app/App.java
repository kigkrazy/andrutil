package com.reizx.util.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.elvishew.xlog.LogLevel;
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
        AsfLog.initLog("asf-log", LogLevel.ALL, null);
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

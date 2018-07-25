package com.reizx.util.di.component;

import com.reizx.util.app.App;
import com.reizx.util.di.module.AppModule;
import com.reizx.util.di.module.HttpModule;
import com.reizx.util.model.retrofit.api.IpApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context

    //对所有的请求进行处理
    IpApi getIpApi();//IP请求接口
}

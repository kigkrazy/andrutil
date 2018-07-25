package com.reizx.util.di.module;

import com.blankj.utilcode.util.NetworkUtils;
import com.reizx.util.constant.Constants;
import com.reizx.util.di.qualifier.IpQualifier;
import com.reizx.util.model.DataManager;
import com.reizx.util.model.retrofit.api.IpApi;
import com.reizx.util.util.SSLSocketClient;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class HttpModule {
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {
                    //没有网络，强制使用缓存，不清除缓存
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        //todo 增加绕过HTTPS验证，如果需要去掉HTTPS验证，请将下面注释打开
        //region绕过HTTPS验证, 如果不需要可以去掉
//        builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
//        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        //endregion
        return builder.build();
    }

    /**
     * 创建Retrofit
     *
     * @param builder
     * @param client
     * @param url
     * @return
     */
    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())//将JSON格式的respon转为对象
                .addConverterFactory(ScalarsConverterFactory.create())//将请求转换为String
                .build();
    }


    @Singleton
    @Provides
    DataManager provideDataManager(Retrofit.Builder builder, OkHttpClient client){
        return new DataManager(builder, client);
    }

    //todo 在这下面天下相关的请求接口Provides
    //region ip状态请求接口
    @Singleton
    @Provides
    @IpQualifier
    Retrofit provideIpRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, IpApi.HOST);
    }

    @Singleton
    @Provides
    IpApi provideIpApi(@IpQualifier Retrofit retrofit) {
        return retrofit.create(IpApi.class);
    }
    //endregion
}

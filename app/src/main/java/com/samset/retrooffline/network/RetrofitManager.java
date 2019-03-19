package com.samset.retrooffline.network;

import android.content.Context;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.samset.retrooffline.BuildConfig;
import com.samset.retrooffline.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.net.HttpHeaders.CACHE_CONTROL;

/**
 * Copyright (C) RetrofitOfflineSample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential.
 * <p>
 * Created by samset on 16/03/19 at 11:29 AM for RetrofitOfflineSample .
 */


public class RetrofitManager {

    private Context context;
    private ApiService apiService;
    private String BASE_URL = "https://api.github.com";

    public RetrofitManager(Context ctx) {
        this.context = ctx;
        apiService = getRetrofit(context).create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

    private Retrofit getRetrofit(Context context) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// for rxjava add RxJava2CallAdapterFactory adapter
                .client(getHttpClient(context))
                .build();
    }

    private OkHttpClient getHttpClient(Context context) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(getCacheInterceptor(context));
        httpBuilder.cache(getCache(context));
        if (BuildConfig.DEBUG) {
            httpBuilder.addNetworkInterceptor(new StethoInterceptor());
            httpBuilder.interceptors().add(getCacheInterceptor(context));
        }

        return httpBuilder.build();
    }

    private Interceptor getCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (!Utils.isConnected(context)) {
                    Request request = chain.request();


                    CacheControl cacheControl = new CacheControl.Builder().maxStale(1, TimeUnit.DAYS).build();
                    request = request.newBuilder().cacheControl(cacheControl).build();

                    String rawJson = chain.proceed(request).body().string();
                    Log.e(BuildConfig.APPLICATION_ID, String.format("req response cache raw JSON response is: %s", rawJson));


                    return chain.proceed(request);
                } else {

                    CacheControl cacheControl = new CacheControl.Builder().maxAge(1, TimeUnit.HOURS).build();

                    Request.Builder request = chain.request().newBuilder();
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Autherization", "your token");
                    request.header(CACHE_CONTROL, cacheControl.toString());

                    Response response = chain.proceed(chain.request());


                    return response;
                    //return response.newBuilder().header(CACHE_CONTROL, cacheControl.toString()).build();
                }
            }
        };
    }

    private Cache getCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File file = new File(context.getCacheDir(), "httpcache");
        return new Cache(file, cacheSize);
    }

    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


}

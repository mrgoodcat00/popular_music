package com.goodcat.popularmusicapp.net.retrofit;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.goodcat.popularmusicapp.BuildConfig;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private API api;

    public RetrofitHelper() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_REST_URL)
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder()
                                        .excludeFieldsWithoutExposeAnnotation()
                                        .create()
                        )
                )
                .callbackExecutor(new Executor() {

                    final Handler handler = new Handler(Looper.getMainLooper());

                    @Override
                    public void execute(@NonNull final Runnable runnable) {
                        handler.post(runnable);
                    }
                })
                .client(
                        new OkHttpClient.Builder()
                                .readTimeout(30, TimeUnit.SECONDS)
                                .connectTimeout(5, TimeUnit.SECONDS)
                                .addInterceptor(new AuthenticateInterceptor())
                                .build()
                )
                .build();

        api = retrofit.create(API.class);
    }

    public API getApi() {
        return api;
    }

}

package com.example.siqingchan.techcollect.retrofit.sample;


import com.example.framework.http.result.BaseResult;
import com.example.siqingchan.techcollect.input.activity.SampleApplicationContext;
import com.example.siqingchan.techcollect.retrofit.baseframe.BaseSubscriber;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.siqingchan.techcollect.retrofit.sample.utils.TrustAllCerts.createSSLSocketFactory;

/**
 * Created by siqingchan on 2017/9/21.
 * retrofit工厂
 */

public class RetrofitFactory {
    private static String host = "https://apitest.jikejia.cn";
    private static long DEFAULT_TIMEOUT = 20;

    public static Retrofit initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, DEFAULT_TIMEOUT, TimeUnit.SECONDS));

        MyInterceptor interceptor = new MyInterceptor(SampleApplicationContext.context);
        interceptor.setSigOff(true);
        builder.addInterceptor(interceptor);
        return new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(host)
                .build();
    }

    public static void bindSubscriber(Observable<? extends BaseResult> observable, BaseSubscriber subscriber) {
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }
}

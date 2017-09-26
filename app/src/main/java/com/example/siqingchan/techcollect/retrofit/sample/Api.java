package com.example.siqingchan.techcollect.retrofit.sample;

import com.example.siqingchan.techcollect.retrofit.baseframe.BaseSubscriber;

import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by siqingchan on 2017/9/26.
 */

public class Api {

    public static void test(Map<String, Object> map, BaseSubscriber subscriber) {
        Retrofit retrofit = RetrofitFactory.initRetrofit();
        Observable<Model> observable = retrofit.create(RetrofitService.class).getModel(map);
        RetrofitFactory.bindSubscriber(observable, subscriber);
    }
}

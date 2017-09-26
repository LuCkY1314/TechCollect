package com.example.siqingchan.techcollect.retrofit.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.siqingchan.techcollect.R;
import com.example.siqingchan.techcollect.retrofit.baseframe.BaseSubscriber;
import com.example.siqingchan.techcollect.retrofit.baseframe.ErrorInfo;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by siqingchan on 2017/9/20.
 */

public class Re extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.re);
        request();
    }

    private void request() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "shanghai01");
        map.put("password", "qwer1234");
        map.put("company_name", "上海公司");
        map.put("company_id", 25);
        BaseSubscriber subscriber = new BaseSubscriber<Model>() {
            @Override
            public void onRequestStart() {
                Log.d("111111111", "onRequestStart");
            }

            @Override
            public void onRequestEnd() {
                Log.d("111111111", "onRequestEnd");
            }

            @Override
            public void onResponse(Model resultModel) {
                Log.d("111111111", "onResponse");
            }

            @Override
            public void onErrorResponse(ErrorInfo errorInfo) {
                Log.d("111111111", "onErrorResponse");
            }
        };
        Api.test(map, subscriber);
    }
}

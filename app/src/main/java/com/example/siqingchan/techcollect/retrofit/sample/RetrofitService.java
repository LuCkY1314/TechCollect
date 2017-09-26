package com.example.siqingchan.techcollect.retrofit.sample;


import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by siqingchan on 2017/9/20.
 * api接口
 */

public interface RetrofitService {
    @POST("/user/login")
    Observable<Model> getModel(@Body Map<String, Object> map);
}

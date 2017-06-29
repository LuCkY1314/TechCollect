package com.example.siqingchan.techcollect.http.request;

import com.android.volley.VolleyError;
import com.example.framework.http.callback.BaseCallback;
import com.example.framework.http.result.BaseResult;

/**
 * Created by siqingchan on 2017/5/15.
 * 回调基类
 */

public abstract class RequestCallback<T extends BaseResult> extends BaseCallback<T> {
    //当请求进入队列，此方法被执行一次，一般在此加入加载动画
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(T response) {

    }
}

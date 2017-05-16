package com.example.framework.http.callback;

import com.android.volley.Response;
import com.example.framework.http.result.BaseResult;

/**
 * Created by siqingchan on 2017/5/12.
 */

public abstract class BaseCallback<T extends BaseResult> implements Response.Listener<T>, Response.ErrorListener {
    /**
     * 标记回调开始事件，在request类中使用，以便子类在此添加加载动画
     */
    public void onStart() {
    }
}

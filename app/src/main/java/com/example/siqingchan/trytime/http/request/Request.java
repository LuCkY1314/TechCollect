package com.example.siqingchan.trytime.http.request;


import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.example.framework.base.BaseApplication;
import com.example.framework.http.Request.RequestMethod;
import com.example.framework.http.Request.ResultRequest;
import com.example.framework.http.callback.BaseCallback;
import com.example.framework.util.Network;
import com.example.siqingchan.trytime.http.util.ApiUtil;
import com.example.siqingchan.trytime.http.util.VolleyTool;

import java.util.Map;

/**
 * Created by siqingchan on 2017/5/15.
 * 具体请求类
 */

public class Request {

    private static final RequestQueue mRequestQueue = VolleyTool.getRequestQueue();

    /**
     * 发送请求
     *
     * @param method
     * @param url
     * @param bodyParams
     * @param callback
     */
    public static void sendRequest(Class clazz, RequestMethod method, String url, Map<String, Object> bodyParams, RequestCallback callback) {
        if (!Network.isNetWorkAvailable()) {
            Toast.makeText(BaseApplication.getInstance().getApplicationContext(), "无网络连接", Toast.LENGTH_SHORT).show();
            if (callback != null) {
                callback.onErrorResponse(new VolleyError("网络不可用"));
            }
            return;
        }
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("request url must be not empty.");
        }
        if (callback != null) {
            callback.onStart();
        }
        //通过自定义ApiUtil的工具类（ApiUtil根据自己需求去定义，这里不予展示），获取请求发送时间
        String qtime = ApiUtil.getQtime();
        //通过自定义ApiUtil的工具类，根据请求时间生成所有公共传参
        Map<String, Object> commonParams = ApiUtil.getCommonParams(qtime);
        //通过自定义ApiUtil的工具类，根据所有传参，生成完整url
        String completeUrl = ApiUtil.getCompleteUrl(method.getMethod(), commonParams, bodyParams, url);
        //通过自定义ApiUtil的工具类，得到body参数的json字符串
        String bodyJson = ApiUtil.mapToJson(bodyParams);
        SigConfig sigConfig = new SigConfig();
        sigConfig.setQtime(qtime);
        sigConfig.setUrl(url);
        sigConfig.setQueryParams(bodyParams);
        sigConfig.setMethod(method);
        sigConfig.setCommonParams(commonParams);
        ResultRequest request = new ResultRequest(clazz, method, completeUrl, bodyJson, initHeader(sigConfig), callback);
        mRequestQueue.add(request);
    }

    /**
     * post请求
     *
     * @param url
     * @param bodyParams
     * @param callback
     */
    public static void doGet(Class clazz, String url, Map<String, Object> bodyParams, RequestCallback callback) {
        sendRequest(clazz, RequestMethod.GET, url, bodyParams, callback);
    }

    /**
     * get请求
     *
     * @param url
     * @param bodyParams
     * @param callback
     */
    public static void doPost(Class clazz, String url, Map<String, Object> bodyParams, RequestCallback callback) {
        sendRequest(clazz, RequestMethod.POST, url, bodyParams, callback);
    }

    /**
     * 获取完整header
     *
     * @param sigConfig
     * @return
     */
    public static Map<String, String> initHeader(SigConfig sigConfig) {
        return ApiUtil.getCommonHeaders(ApiUtil.getPublicHeaders(), sigConfig);
    }
}

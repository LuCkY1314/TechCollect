package com.example.framework.http.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.example.framework.http.callback.BaseCallback;
import com.example.framework.http.result.BaseResult;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by siqingchan on 2017/5/12.
 */

public class ResultRequest<T extends BaseResult> extends JsonRequest<T> {
    /**
     * The default socket timeout in milliseconds
     */
    public static final int TIMEOUT_MS = 10000;
    /**
     * The default number of retries
     */
    public static final int MAX_RETRIES = 1;
    /**
     * The default backoff multiplier
     */
    public static final float BACKOFF_MULT = 1f;
    /**
     * 公共传参
     */
    private Map<String, Object> commonParams;
    /**
     * api业务传参
     */
    private Map<String, Object> bodyParams;
    /**
     * http header
     */
    private Map<String, String> header;
    /**
     * 请求回调
     */
    private BaseCallback<T> callback;
    /**
     * result 泛型类型
     */
    private Class<T> clazz;

    /**
     * 构造器
     *
     * @param method
     * @param url
     * @param bodyJson
     * @param header
     * @param callback
     */
    public ResultRequest(Class clazz, RequestMethod method, String url, String bodyJson, Map<String, String> header, BaseCallback callback) {
        super(method.getMethod(), url, bodyJson, null, null);
        this.header = header;
        this.callback = callback;
        this.clazz = clazz;
        setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, MAX_RETRIES, BACKOFF_MULT));
    }


    @Override
    protected void deliverResponse(T response) {
        if (this.callback != null) {
            this.callback.onResponse(response);
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        if (callback != null && error != null) {
            callback.onErrorResponse(error);
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            //根据header里的charset，将data字节转化成对应的字符串（一般都是utf-8，安全起见，以header约定charset为准）
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            BaseResult baseResult = new Gson().fromJson(json, BaseResult.class);
            //返回Result类，以及header中约定的缓存信息（包含了result类）
            return Response.success(new Gson().fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return header == null ? super.getHeaders() : header;
    }
}

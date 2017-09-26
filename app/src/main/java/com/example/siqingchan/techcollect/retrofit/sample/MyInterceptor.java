package com.example.siqingchan.techcollect.retrofit.sample;

import android.content.Context;

import com.example.siqingchan.techcollect.http.util.ApiUtil;
import com.example.siqingchan.techcollect.retrofit.sample.utils.RetroUtil;
import com.example.siqingchan.techcollect.retrofit.sample.utils.UriUtils;
import com.example.siqingchan.techcollect.retrofit.baseframe.BaseInterceptor;
import com.example.siqingchan.techcollect.retrofit.baseframe.CheckParamBuilder;

import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Request;

import static com.example.siqingchan.techcollect.retrofit.sample.utils.RetroUtil.API_KEY;


/**
 * Created by siqingchan on 2017/9/25.
 * 拦截器实例
 */

public class MyInterceptor extends BaseInterceptor implements CheckParamBuilder {

    private String qTime;
    private String authToken = "";

    public MyInterceptor(Context context) {
        super(context);
        qTime = ApiUtil.getQtime();
    }


    @Override
    public String createPostSig(String url, String bodyStr) {
        String sigUrl = RetroUtil.getPostSig(url, "/", bodyStr, qTime, authToken, null);
        headerData.set(RetroUtil.getPostHeadMap(sigUrl, authToken, API_KEY));
        return sigUrl;
    }

    @Override
    public String createGetSig(String url, Map<String, String> map) {
        String sigUrl = RetroUtil.getGetSig(url, "/", map, qTime, authToken, null);
        headerData.set(RetroUtil.getGetHeaderMap(sigUrl, authToken, API_KEY));
        return sigUrl;
    }

    @Override
    public void useSig(String sig, Request.Builder builder) {
        //添加请求头
        Map<String, String> headers = headerData.get();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey));
            }
        }
    }

    @Override
    public void addCommonParams(Request.Builder builder, String completeUrl) {
        //添加公共参数
        HttpUrl url = HttpUrl.parse(UriUtils.addCommonParams(completeUrl, qTime));
        builder.url(url);
    }
}

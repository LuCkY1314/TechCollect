package com.example.siqingchan.techcollect.retrofit.baseframe;


import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by siqingchan on 2017/9/21.
 * 拦截器基类
 */

public abstract class BaseInterceptor implements Interceptor, CheckParamBuilder {
    private static String GET = "GET";
    private static String POST = "POST";
    /**
     * 验证参数sig构造监听器
     */
    private CheckParamBuilder sigBuilder;
    /**
     * 拦截器使用与否的开关
     */
    private boolean sigOff;

    public boolean isSigOff() {
        return sigOff;
    }

    public void setSigOff(boolean sigOff) {
        this.sigOff = sigOff;
    }

    public BaseInterceptor(Context context) {
        this.sigBuilder = this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = reConstructRequestBuilder(request);
        Response response = chain.proceed(builder.build());
        return response;
    }

    /**
     * 重塑请求——用于统一添加校验参数
     * <p>
     * 添加校验的参数的策略可供参考如下：
     * GET:MD5(url+?参数+app内部定义的私钥)=后端校验的参数
     * POST:MD5(url+request.body的参数字符串+app内部定义的私钥)=后端校验的参数
     *
     * @param request
     * @return
     */
    private Request.Builder reConstructRequestBuilder(Request request) {
        String method = request.method();
        HttpUrl httpUrl = request.url();
        Request.Builder builder = request.newBuilder();
        if (sigOff) {
            String sig = "";
            if (method.equals(GET)) {
                sig = initSignParamForGet(httpUrl);
            } else if (method.equals(POST)) {
                sig = initSignParamForPost(request);
            }
            sigBuilder.useSig(sig, builder);
        }
        addCommonParams(builder, httpUrl.toString());
        return builder;
    }

    /**
     * 用于添加公共参数（非业务参数）——比如设备基本信息参数(不支持重定向，只能更改参数部分，不能更改url的path或host)
     *
     * @param builder
     * @param CompleteUrl
     */
    public abstract void addCommonParams(Request.Builder builder, String CompleteUrl);

    /**
     * GET请求时：生成校验参数
     */
    private String initSignParamForGet(HttpUrl httpUrl) {
        String sig;
        Map<String, String> param = getQueryMap(httpUrl);
        String url = httpUrl.url().getPath().substring(1);
        sig = sigBuilder.createGetSig(url, param);
        return sig;
    }

    /**
     * POST请求时：生成校验参数
     *
     * @param request
     * @return
     */
    private String initSignParamForPost(Request request) {
        String sig;
        String bodyStr = getBodyStr(request);
        String url = request.url().url().getPath().substring(1);
        sig = sigBuilder.createPostSig(url, bodyStr);
        return sig;
    }


    /**
     * GET方法时，获取请求参数
     *
     * @param httpUrl
     * @return
     */
    private Map<String, String> getQueryMap(HttpUrl httpUrl) {
        Map<String, String> param = new HashMap<>();
        Set<String> queryParameterNames = httpUrl.queryParameterNames();
        for (String key : queryParameterNames) {
            param.put(key, httpUrl.queryParameter(key));
        }
        return param;
    }

    /**
     * POST请求时，获取请求body入参 数据格式如
     * "{"param1":"入参1","param2":"入参2"}"
     *
     * @param request
     * @return
     */
    private String getBodyStr(Request request) {
        RequestBody body = request.body();
        try {
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            String bodyStr = buffer.readUtf8();
            buffer.close();
            return bodyStr;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}


package com.example.siqingchan.techcollect.retrofit.baseframe;


import java.util.Map;

import okhttp3.Request;

/**
 * Created by siqingchan on 2017/9/22.
 * 校验参数生成器接口
 */

public interface CheckParamBuilder {
     ThreadLocal<Map<String, String>> headerData = new ThreadLocal<>();

    /**
     * 生成post请求时的校验参数
     *
     * @return
     */
    String createPostSig(String url, String bodyStr);

    /**
     * 生成get请求时的校验参数f
     *
     * @return
     */
    String createGetSig(String url, Map<String, String> map);

    /**
     * 将通过规则生成的sig加入到请求中的某个位置（推荐加入到header中）
     *
     * @param sig
     */
    void useSig(String sig, Request.Builder request);
}

package com.example.siqingchan.techcollect.retrofit.sample.utils;


import com.example.siqingchan.techcollect.retrofit.sample.utils.ApiCommonInfoUtil;

/**
 * desc:
 * <p>
 * Created by SishuiYe
 * E-mail: sishuiye@anjuke.com
 * Created on  2017/4/27
 */

public final class UriUtils {
    public static String sHost;
    public static final String TAG = "OkHttp";

    static {
        sHost = "https://apitest.jikejia.cn";
    }

    public static String addCommonParams(String url, String qtime) {
        StringBuffer tempBuffer = new StringBuffer(url);
        if (url.contains("?")) {
            tempBuffer.append("&");
        } else {
            tempBuffer.append(("?"));
        }
        tempBuffer.append(ApiCommonInfoUtil.getExtraParamsNoFirstAnd(qtime));
        return tempBuffer.toString();
    }


}

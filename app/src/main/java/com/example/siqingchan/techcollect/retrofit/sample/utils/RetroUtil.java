package com.example.siqingchan.techcollect.retrofit.sample.utils;

import android.text.TextUtils;


import com.example.siqingchan.techcollect.http.util.ApiUtil;
import com.example.siqingchan.techcollect.retrofit.sample.utils.ApiCommonInfoUtil;
import com.example.siqingchan.techcollect.retrofit.sample.utils.ApiSigUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 * <p>
 * Created by SishuiYe
 * E-mail: sishuiye@anjuke.com
 * Created on  2017/4/27
 */

public class RetroUtil {
    private static final String VERSION_ONLINE = "broker/";        // 线上版本

    //private_key，用作md5
    public static final String PRIVATE_KEY = ApiUtil.ANJUKE_API_PRIVATE_KEY;
    //public_key，用作md5、请求头
    public static final String API_KEY = ApiUtil.ANJUKE_API_PUBLIC_KEY;
    private static final String MEDIA_TYPE_JSON = "application/json";

    private static final String KEY_SIG = "sig";
    private static final String KEY_TOKEN = "AuthToken"; //added in 6.1.6
    private static final String KEY_API_KEY = "key";
    private static final String KEY_ACCEPT = "Accept";
    private static final String KEY_CONTENT_TYPE = "Content-type";

    private static final String apiReturnType = MEDIA_TYPE_JSON;
    private static String version = VERSION_ONLINE;

    public static void setVersion(String ver) {
        version = ver;
    }

    public static String getVersion() {
        return version;
    }

    /**
     * 获取GET请求sig【通用】
     *
     * @param methodUrl
     * @param specifiedVersion
     * @param params
     * @param qTime
     * @param publicKey
     * @param privateKey
     * @return
     */
    public static String getGetSig(String methodUrl, String specifiedVersion,
                                   Map<String, String> params, String qTime,
                                   String publicKey, String privateKey) {
        if (TextUtils.isEmpty(publicKey) || TextUtils.isEmpty(privateKey)) {
            publicKey = API_KEY;
            privateKey = PRIVATE_KEY;
        }
        HashMap<String, String> needSigParams = new HashMap<>();
        needSigParams.putAll(params);
        needSigParams.putAll(ApiCommonInfoUtil.getExtraParamsHashMap(qTime));
        String sigUrl = version + methodUrl;
        if (!TextUtils.isEmpty(specifiedVersion)) sigUrl = specifiedVersion + methodUrl;

        String sig = ApiSigUtil.sigGet(needSigParams, sigUrl, publicKey, privateKey);
        return sig;
    }

    /**
     * 获取GET请求头【通用】
     *
     * @param authToken
     * @param publicKey
     * @return
     */
    public static Map<String, String> getGetHeaderMap(String sig,
                                                      String authToken, String publicKey) {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put(KEY_SIG, sig);
        headerMap.put(KEY_API_KEY, publicKey);
        headerMap.put(KEY_ACCEPT, apiReturnType);
        if (!TextUtils.isEmpty(authToken)) {
            headerMap.put(KEY_TOKEN, authToken);//since v6.1.6
        }
        return headerMap;
    }


    /**
     * 获取POST请求sig【通用】
     */
    public static String getPostSig(String methodUrl, String specifiedVersion,
                                    String bodyStr, String qTime,
                                    String publicKey, String privateKey) {
        if (TextUtils.isEmpty(publicKey) || TextUtils.isEmpty(privateKey)) {
            publicKey = API_KEY;
            privateKey = PRIVATE_KEY;
        }
        String sigUrl = version + methodUrl;
        if (specifiedVersion != null) sigUrl = specifiedVersion + methodUrl;

        String sig = ApiSigUtil.sigPost(ApiCommonInfoUtil.getExtraParamsHashMap(qTime), bodyStr, sigUrl, publicKey, privateKey);
        return sig;
    }

    /**
     * 获取POST请求头【通用】
     */
    public static Map<String, String> getPostHeadMap(String sig, String authToken,
                                                     String publicKey) {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put(KEY_SIG, sig);
        headerMap.put(KEY_API_KEY, publicKey);
        headerMap.put(KEY_ACCEPT, MEDIA_TYPE_JSON);
        headerMap.put(KEY_CONTENT_TYPE, MEDIA_TYPE_JSON);
        if (!TextUtils.isEmpty(authToken)) {
            headerMap.put(KEY_TOKEN, authToken);//since v6.1.6
        }
        return headerMap;
    }
}

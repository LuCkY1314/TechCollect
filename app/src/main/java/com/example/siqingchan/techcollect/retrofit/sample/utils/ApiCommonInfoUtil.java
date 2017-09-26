package com.example.siqingchan.techcollect.retrofit.sample.utils;

import android.os.Process;
import android.text.TextUtils;


import com.example.framework.http.lib.PhoneInfo;
import com.example.siqingchan.techcollect.BuildConfig;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ApiCommonInfoUtil {

    public static final String NOT_INITIALIZE_ERROR = PhoneInfo.class
            .getSimpleName()
            + " not initialize. Please run "
            + PhoneInfo.class.getSimpleName()
            + ".initialize() first !";

    static String getExtraParams(String qtime) {
        StringBuffer retBuffer = new StringBuffer();

        HashMap<String, String> temp = getExtraParamsHashMap(qtime);
        for (String key : temp.keySet()) {
            retBuffer.append("&").append(key).append("=").append(strUrlEncode(temp.get(key)));
        }

        return retBuffer.toString();
    }

    public static String getExtraParamsNoFirstAnd(String qtime) {
        StringBuffer retBuffer = new StringBuffer();

        HashMap<String, String> temp = getExtraParamsHashMap(qtime);
        int index = 0;
        for (String key : temp.keySet()) {
            index++;
            if (index > 1)
                retBuffer.append("&");

            retBuffer.append(key).append("=").append(strUrlEncode(temp.get(key)));
        }

        return retBuffer.toString();
    }

    public static HashMap<String, String> getExtraParamsHashMap(String qtime) {
        HashMap<String, String> ret = new HashMap<String, String>();

        if (PhoneInfo.mOutContext == null) {// 未初始化 提示初始化
            throw new RuntimeException(NOT_INITIALIZE_ERROR);
        }

        ret.put("i", PhoneInfo.DeviceID);
        ret.put("macid", PhoneInfo.NewID);
        ret.put("m", PhoneInfo.DeviceID);
        ret.put("o", PhoneInfo.OSDesc);
        ret.put("v", PhoneInfo.OSVer);
        ret.put("cv", PhoneInfo.AppVer);
        ret.put("app", PhoneInfo.AppName);
        ret.put("pm", PhoneInfo.AppPM);

        ret.put("from", "mobile");
        if (qtime != null) {
            ret.put("qtime", qtime);// 统一由外部指定同一时间 不然算sig会有问题
        }

        if (PhoneInfo.uuid != null) {
            ret.put("uuid", PhoneInfo.uuid);
        }

        if (!TextUtils.isEmpty(PhoneInfo._brokerId)) {
            ret.put("_broker_id", PhoneInfo._brokerId);
        }

        if (!TextUtils.isEmpty(PhoneInfo._chatId)) {
            ret.put("_chat_id", PhoneInfo._chatId);
        }

        try {
            ret.put("_pid", Process.myPid() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (BuildConfig.DEBUG) {
//            ret.put("pgVersion", "pgpmt34978");
            Map<String, String> commParams = getCommonParamsForDebug();
            if (null != commParams && !commParams.isEmpty()) {
                for (Map.Entry<String, String> entry : commParams.entrySet()) {
                    ret.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return ret;
    }

    /**
     * url encode
     *
     * @param str
     * @return
     */
    public static String strUrlEncode(String str) {
        // TODO 这里待研究这个不被推荐的方法。
        if (str != null)
            return URLEncoder.encode(str);
        else
            return "";
    }

    public static HashMap<String, String> paramsEncode(Map<String, String> params) {
        HashMap<String, String> ret = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            ret.put(entry.getKey(), strUrlEncode(entry.getValue()));
        }
        return ret;
    }

    private static Map<String, String> commonParamsForDebug;

    public static Map<String, String> getCommonParamsForDebug() {
        return commonParamsForDebug;
    }

    public static void addCommonParamsForDebug(String key, String value) {
        if (null == commonParamsForDebug) commonParamsForDebug = new HashMap<>();
        commonParamsForDebug.put(key, value);
    }
}

package com.example.siqingchan.trytime.http.request;

import com.example.framework.http.Request.RequestMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by siqingchan on 2016/6/6.
 * 公共参数辅助类
 */
public class SigConfig {

    private String mUrl;
    private String qtime;
    private RequestMethod method;
    private Map<String, Object> mQueryParams;
    private Map<String, Object> mCommonParams;


    public String getUrlPath() {
        String result = "";
        try {
            URL url = new URL(mUrl);
            result = url.getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getQtime() {
        return qtime;
    }

    public void setQtime(String qtime) {
        this.qtime = qtime;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public Map<String, Object> getQueryParams() {
        return mQueryParams;
    }

    public void setQueryParams(Map<String, Object> mParams) {
        this.mQueryParams = mParams;
    }

    public Map<String, Object> getCommonParams() {
        return mCommonParams;
    }

    public void setCommonParams(Map<String, Object> mCommonParams) {
        this.mCommonParams = mCommonParams;
    }
}

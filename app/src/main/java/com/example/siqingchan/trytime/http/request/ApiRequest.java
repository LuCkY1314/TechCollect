package com.example.siqingchan.trytime.http.request;

import android.text.TextUtils;

import com.example.framework.http.callback.BaseCallback;
import com.example.siqingchan.trytime.http.result.ThirdLoginUserResult;
import com.example.siqingchan.trytime.http.util.ApiUtil;

import java.util.Map;

/**
 * Created by siqingchan on 2017/5/15.
 * api请求库
 */

public class ApiRequest {
    /**
     * 公司账号登陆
     *
     * @param company_id
     * @param company_name
     * @param username
     * @param password
     * @param code
     * @param requestCallback
     */
    public static void loginCompany(long company_id, String company_name, String username, String password, String code, RequestCallback<ThirdLoginUserResult> requestCallback) {
        Map<String, Object> map = ApiUtil.getQueryParams();
        map.put("company_id", company_id);
        map.put("company_name", company_name);
        map.put("username", username);
        map.put("password", password);
        if (!TextUtils.isEmpty(code)) {
            map.put("code", code);
        }
        Request.doPost(ThirdLoginUserResult.class, Urls.getInstance().loginCompany, map, requestCallback);
    }
}

package com.example.siqingchan.techcollect.http.request;

import android.text.TextUtils;

import com.example.siqingchan.techcollect.details.result.CompanyRentHouseCustomerDetailsResult;
import com.example.siqingchan.techcollect.http.result.ThirdLoginUserResult;
import com.example.siqingchan.techcollect.http.util.ApiUtil;

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
    /**
     * 获取租房客源详情
     *
     * @param accountId
     * @param cityId
     * @param companyId
     * @param departmentId
     * @param roleId
     * @param customerId
     * @param callback
     */
    public static void getRentHouseCustomerDetailsData(long accountId, int cityId, int companyId, int departmentId, long roleId, String customerId, RequestCallback<CompanyRentHouseCustomerDetailsResult> callback) {
        Map<String, Object> params = ApiUtil.getQueryParams();
        params.put("account_id", accountId);
        params.put("city_id", cityId);
        params.put("customer_id", customerId);
        params.put("company_id", companyId);
        params.put("department_id", departmentId);
        params.put("role_id", roleId);
        Request.doGet(CompanyRentHouseCustomerDetailsResult.class, Urls.getInstance().getCompanyRentHouseCustomerDetails, params, callback);
    }
}

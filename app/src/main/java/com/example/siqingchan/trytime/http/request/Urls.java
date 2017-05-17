package com.example.siqingchan.trytime.http.request;

/**
 * Created by siqingchan on 2017/5/15.
 * url库
 */

public class Urls {
    private static Urls instance = new Urls();
    public static final String API_HOST_TEST = "https://apitest.jikejia.cn";

    private String getHost() {
        return API_HOST_TEST;
    }

    public synchronized static Urls getInstance() {
        if (instance == null) {
            instance = new Urls();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
        instance = getInstance();
    }

    public String loginCompany = getHost() + "/user/login";
    /**
     * 公司租房客源详情
     */
    public String getCompanyRentHouseCustomerDetails = getHost() + "/rentCustomer/details";
}

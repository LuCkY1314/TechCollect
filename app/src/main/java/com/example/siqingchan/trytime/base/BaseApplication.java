package com.example.siqingchan.trytime.base;

import com.example.framework.http.lib.PhoneInfo;
import com.example.framework.util.Network;

/**
 * Created by siqingchan on 2017/5/15.
 */

public class BaseApplication extends com.example.framework.base.BaseApplication {
    private String APP_NAME = "a-jkj";
    private static BaseApplication application;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    public void initialize() {
        //获取手机设备信息辅助类初始化
        PhoneInfo.initialize(this, APP_NAME);
        //网络状态工具类初始化
        Network.init(this);
    }

    @Override
    public void backgroundInitialize() {

    }
}

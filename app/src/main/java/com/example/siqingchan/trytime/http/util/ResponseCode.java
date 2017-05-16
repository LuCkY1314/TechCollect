package com.example.siqingchan.trytime.http.util;

import com.google.gson.FieldAttributes;

/**
 * Created by Lennon on 2016/6/17.
 */
public class ResponseCode {
    public static final int SUCCESS = 00000;
    public static final int UNSUPPORT_VERSION = 10006;


    public static final int IMAGE_VERIFY_CODE_ERROR = 20005;//图片验证码错误
    public static final int LOGIN_PASSWORD_ERROR = 20013; //登录密码错误
    public static final int TOKEN_INVALID = 20020;//ACCESS_TOKEN失效
    public static final int THIRD_AUTH_BIND = 20027;// 第三方授权账号需绑定账号

}

package com.example.siqingchan.techcollect.http.util;

/**
 * Created by Lennon on 2016/5/31.
 */
public class ResponseStatus {
    public static final int SUCCESS             = 200;//请求成功
    public static final int ACCEPT              = 202;//服务器已经接受请求（异步操作）
    public static final int REQUEST_ERROR       = 400;//请求错误
    public static final int AUTH_ERROR          = 401;//授权失败，access token 过期
    public static final int PERMISSION_ERROR    = 403;//授权成功，但访问被禁止
    public static final int NOT_FOUND           = 404;//该请求不存在
    public static final int FORMAT_ERROR        = 406;//请求格式错误
    public static final int PARAMS_ERROR        = 422;//请求参数验证错误
    public static final int SERVER_ERROR        = 500;//服务器错误
    public static final int UNSUPPORT_VERSION   = 505;
}
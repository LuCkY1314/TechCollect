package com.example.siqingchan.techcollect.retrofit.baseframe;

/**
 * Created by csq
 * 错误码
 */
public class ErrorInfo {
    /**
     * http请求返回为空
     */
    public final static String CODE_RESPONSE_EMPTY = "-666666";

    /**
     * data字段为空时的code码
     */
    public final static String CODE_DATA_EMPTY = "-666667";
    /**
     * 未知错误
     */
    public static final String UNKNOWN = "1000";
    /**
     * 解析错误
     */
    public static final String PARSE_ERROR = "1001";
    /**
     * 网络错误
     */
    public static final String NETWORD_ERROR = "1002";
    /**
     * 协议出错
     */
    public static final String HTTP_ERROR = "1003";
    /**
     * 证书出错
     */
    public static final String SSL_ERROR = "1005";

    /**
     * 连接超时
     */
    public static final String TIMEOUT_ERROR = "1006";


    private String code;
    private String message;

    public ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorInfo() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

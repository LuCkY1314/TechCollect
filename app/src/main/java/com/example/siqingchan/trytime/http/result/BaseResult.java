package com.example.siqingchan.trytime.http.result;

import com.example.siqingchan.trytime.http.util.ResponseCode;
import com.example.siqingchan.trytime.http.util.ResponseStatus;
import com.google.gson.annotations.SerializedName;

/**
 * Created by siqingchan on 2017/5/15.
 */

public class BaseResult extends com.example.framework.http.result.BaseResult {
    @SerializedName("status")
    private int status;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public boolean isSuccess() {
        return status == ResponseStatus.SUCCESS && code == ResponseCode.SUCCESS;
    }

    public boolean isAccessTokenInvalidate() {
        return status == ResponseStatus.AUTH_ERROR && code == ResponseCode.TOKEN_INVALID;
    }

    public boolean isForceUpdate() {
        return status == ResponseStatus.UNSUPPORT_VERSION && code == ResponseCode.UNSUPPORT_VERSION;
    }

    public boolean isLoginPasswordError() {
        return status == ResponseStatus.PARAMS_ERROR && code == ResponseCode.LOGIN_PASSWORD_ERROR;
    }

    public boolean isImageVerifyCodeError() {
        return status == ResponseStatus.PARAMS_ERROR && code == ResponseCode.IMAGE_VERIFY_CODE_ERROR;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

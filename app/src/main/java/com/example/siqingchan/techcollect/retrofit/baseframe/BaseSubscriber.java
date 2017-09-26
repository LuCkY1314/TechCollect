package com.example.siqingchan.techcollect.retrofit.baseframe;

import android.net.ParseException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by siqingchan on 2017/9/26.
 * 观察者基类——提供四个接口
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    public BaseSubscriber() {
        onRequestStart();
    }

    @Override
    public void onCompleted() {
        onRequestEnd();
    }

    @Override
    public void onError(Throwable e) {
        handleException(e);
        e.printStackTrace();
    }

    @Override
    public void onNext(T resultModel) {
        onResponse(resultModel);
    }

    private void handleException(Throwable e) {
        ErrorInfo info = new ErrorInfo();
        if (e instanceof HttpException) {
            HttpException he = (HttpException) e;
            info.setCode(he.code() + "");
            info.setMessage("网络错误");
        } else if (e instanceof JsonParseException
                || e instanceof JsonIOException
                || e instanceof ParseException) {
            info.setCode(ErrorInfo.PARSE_ERROR);
            info.setMessage("解析错误");
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            info.setCode(ErrorInfo.NETWORD_ERROR);
            info.setMessage("连接失败");
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            info.setCode(ErrorInfo.SSL_ERROR);
            info.setMessage("证书验证失败");
        } else if (e instanceof ConnectTimeoutException
                || e instanceof SocketTimeoutException) {
            info.setCode(ErrorInfo.TIMEOUT_ERROR);
            info.setMessage("连接超时");
        } else {
            info.setCode(ErrorInfo.UNKNOWN);
            info.setMessage("未知错误: " + (e == null ? "null" : e.getMessage()));
        }
        onErrorResponse(info);
    }

    /**
     * 请求开始
     */
    public abstract void onRequestStart();

    /**
     * 请求结束
     */
    public abstract void onRequestEnd();

    /**
     * 成功回调
     */
    public abstract void onResponse(T resultModel);

    /**
     * 失败回调
     */
    public abstract void onErrorResponse(ErrorInfo errorInfo);
}

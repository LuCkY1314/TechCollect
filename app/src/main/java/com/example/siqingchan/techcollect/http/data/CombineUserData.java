package com.example.siqingchan.techcollect.http.data;

import com.example.siqingchan.techcollect.filter.data.BaseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lennon on 2016/8/12.
 */

public class CombineUserData extends BaseData {
    @SerializedName(value = "accessToken", alternate = {"acces_token", "access_token"})
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("expire")
    private String expire;
    @SerializedName("user")
    private User user;

    /*绑定端口用*/
    @SerializedName("mergeAccountId")
    private long mergeAccountId;
    /*绑定端口用*/
    @SerializedName("bindSite")
    private int bindSite;

    /*web登录后，记录登录的网站*/
    private int loginSite;
    /*公司账号登录后，记录登录的账号*/
    private String loginAccount;


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public long getMergeAccountId() {
        return mergeAccountId;
    }

    public void setMergeAccountId(long mergeAccountId) {
        this.mergeAccountId = mergeAccountId;
    }

    public int getBindSite() {
        return bindSite;
    }

    public void setBindSite(int bindSite) {
        this.bindSite = bindSite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLoginSite() {
        return loginSite;
    }

    public void setLoginSite(int loginSite) {
        this.loginSite = loginSite;
    }


    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }
}

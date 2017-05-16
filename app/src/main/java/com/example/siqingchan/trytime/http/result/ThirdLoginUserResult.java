package com.example.siqingchan.trytime.http.result;

import com.example.siqingchan.trytime.http.data.CombineUserData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lennon on 2016/7/15.
 */
public class ThirdLoginUserResult extends BaseResult {

    @SerializedName("data")
    private CombineUserData data;

    public CombineUserData getData() {
        return data;
    }

    public void setData(CombineUserData data) {
        this.data = data;
    }

}
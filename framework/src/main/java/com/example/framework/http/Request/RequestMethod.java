package com.example.framework.http.Request;

import com.android.volley.Request;

/**
 * Created by Lennon on 2016/7/6.
 */
public enum RequestMethod {
    POST(Request.Method.POST), GET(Request.Method.GET);

    private int method;

    RequestMethod(int method) {
        this.method = method;
    }

    public int getMethod() {
        return method;
    }
}

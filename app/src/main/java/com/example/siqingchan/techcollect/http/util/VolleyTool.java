package com.example.siqingchan.techcollect.http.util;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.example.framework.base.BaseApplication;
import javax.net.ssl.SSLSocketFactory;


/**
 * Created by siqingchan on 2017/5/15.
 */

public class VolleyTool {
    private static RequestQueue requestQueue;

    public static synchronized RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(BaseApplication.getInstance(), getHttpsStackTrustAll());
        }
        return requestQueue;
    }

    private static HttpStack getHttpsStackTrustAll() {
        SSLSocketFactory sslSocketFactory = HttpsTool.buildSSLSocketFactoryTrustAll();
        HttpStack httpStack = new HurlStack(null, sslSocketFactory);
        return httpStack;
    }
}

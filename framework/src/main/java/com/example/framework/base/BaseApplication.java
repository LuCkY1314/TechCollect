package com.example.framework.base;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created on 2016/5/10.
 *
 * @Author Lennon
 */
public abstract class BaseApplication extends Application {
    private static BaseApplication mApplication;


    /**
     * 在主线程执行，做非耗时初始化操作
     */
    public abstract void initialize();

    /**
     * 在工作线程执行，做耗时初始化操作
     */
    public abstract void backgroundInitialize();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        init();
    }

    private void init() {
        initialize();
        doBackgroundInit();
    }

    private void doBackgroundInit() {
        new AsyncTask<Object, Integer, Object>() {
            @Override
            protected Object doInBackground(Object... params) {
                backgroundInitialize();
                return null;
            }
        }.execute();
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }


    /**
     * 退出应用
     */
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


}

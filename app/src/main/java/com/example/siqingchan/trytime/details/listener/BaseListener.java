package com.example.siqingchan.trytime.details.listener;

import android.app.Activity;
import android.databinding.ViewDataBinding;

/**
 * Created by siqingchan on 2017/5/16.
 */

public class BaseListener<T extends ViewDataBinding> {
    private Activity activity;
    protected T dataBinding;

    public BaseListener(Activity activity, T dataBinding) {
        this.activity = activity;
        this.dataBinding = dataBinding;
    }
}

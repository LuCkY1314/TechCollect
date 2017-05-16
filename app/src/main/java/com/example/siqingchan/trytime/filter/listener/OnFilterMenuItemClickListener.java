package com.example.siqingchan.trytime.filter.listener;


import android.databinding.ViewDataBinding;

/**
 * Created by siqingchan on 2017/5/8.
 * 封装于基类filter adapter内的整行item点击监听器
 */

public interface OnFilterMenuItemClickListener<T> {
    void onItemClick(T t, ViewDataBinding dataBinding,int type);
}

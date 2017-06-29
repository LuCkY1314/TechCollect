package com.example.siqingchan.techcollect.details.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.siqingchan.techcollect.R;
import com.example.siqingchan.techcollect.details.data.BaseDetailsData;
import com.example.siqingchan.techcollect.details.listener.BaseListener;

/**
 * Created by siqingchan on 2017/5/16.
 */

public abstract class BaseDetailsActivity<T extends ViewDataBinding> extends Activity {
    private T dataBinding;
    private int layoutId, variableId, listenerId;
    private BaseDetailsData data;
    private BaseListener listener;
    private FrameLayout baseLayout;

    /**
     * 初始化网络回调
     */
    public abstract void initCallback(T dataBinding);

    /**
     * 初始化监听器
     *
     * @param dataBinding
     */
    public abstract void initListener(T dataBinding);

    /**
     * 获取上个页面传参
     */
    public abstract void getPreviousData();

    /**
     * 网络数据请求操作
     */
    public abstract void requestHttpData(T dataBinding);

    /**
     * 初始化xml绑定相关参数
     */
    public abstract void setXml();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseLayout = (FrameLayout) findViewById(R.id.base_layout);
        getPreviousData();
        setXml();
        bindXml(layoutId, variableId, data);
        initListener(dataBinding);
        initCallback(dataBinding);
    }

    /**
     * 请求一般可以刷新，故放在Resume生命周期
     */
    @Override
    protected void onResume() {
        super.onResume();
        requestHttpData(dataBinding);
    }

    /**
     * 填充主视图
     *
     * @param view
     */
    public void setContentView(View view) {
        baseLayout.addView(view);
    }

    /**
     * 仅仅设置layout 后续需要在setXml方法中使用setBindingData绑定数据集
     *
     * @param layoutId
     */
    public void setXmlVariable(int layoutId) {
        this.layoutId = layoutId;
    }

    /**
     * 推荐使用 同时设置layout和data
     *
     * @param layoutId
     * @param variableId
     * @param data
     */
    public void setXmlVariable(int layoutId, int variableId, BaseDetailsData data) {
        this.layoutId = layoutId;
        this.variableId = variableId;
        this.data = data;
    }


    /**
     * 绑定dataBinding的xml
     *
     * @param layoutId
     */
    public void bindXml(int layoutId, int variableId, BaseDetailsData data) {
        try {
            dataBinding = DataBindingUtil.inflate(LayoutInflater.from(this), layoutId, baseLayout, false);
            dataBinding.executePendingBindings();
            dataBinding.setVariable(variableId, data);
            setContentView(dataBinding.getRoot());
        } catch (RuntimeException e) {
            throw new RuntimeException("should use setXmlVariable first");
        }
    }

    /**
     * 绑定详情数据集
     *
     * @param variableId
     * @param data
     */
    public void setBindingData(int variableId, BaseDetailsData data) {
        if (data != null) {
            dataBinding.setVariable(variableId, data);
        }
    }

    /**
     * 绑定详情监听器
     *
     * @param listenerId
     * @param listener
     */
    public void setBindingListener(int listenerId, BaseListener listener) {
        if (listener != null) {
            dataBinding.setVariable(listenerId, listener);
        }
    }
}

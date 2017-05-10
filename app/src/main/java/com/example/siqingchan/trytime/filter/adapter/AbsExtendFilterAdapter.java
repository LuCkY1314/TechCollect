package com.example.siqingchan.trytime.filter.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.siqingchan.trytime.filter.listener.OnFilterMenuItemClickListener;
import com.example.siqingchan.trytime.filter.viewholder.BindingViewHolder;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/8.
 * 用于普通列表-可以有不同type的item，或有head和foot特别样式的列表
 */

public abstract class AbsExtendFilterAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {
    private Context context;
    private List<T> data;
    private int variableId;
    private OnFilterMenuItemClickListener<T> listener;
    private LayoutInflater layoutInflater;
    protected final static int HEAD_TYPE = 1, NORMAL_TYPE = 2;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public OnFilterMenuItemClickListener<T> getListener() {
        return listener;
    }

    public void setListener(OnFilterMenuItemClickListener<T> listener) {
        this.listener = listener;
    }

    public AbsExtendFilterAdapter(Context context, int variableId) {
        this.context = context;
        this.variableId = variableId;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingViewHolder(inflaterView(layoutInflater, parent, viewType));
    }

    /**
     * 如果子类adapter有新的type要求，可以重写此方法
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEAD_TYPE;
        else
            return NORMAL_TYPE;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, final int position) {
        T t = data.get(position);
        holder.getBinding().setVariable(variableId, t);
        holder.getBinding().executePendingBindings();
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(data.get(position));
            }
        });
        addOtherListener(holder.getBinding(), t, getItemViewType(position));
    }

    /**
     * 给item子控件添加事件
     *
     * @param binding item的ViewDataBinding
     * @param data    item的对应的数据
     */
    protected abstract void addOtherListener(ViewDataBinding binding, T data, int type);

    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * 加载布局
     *
     * @return
     */
    public abstract ViewDataBinding inflaterView(LayoutInflater layoutInflater, ViewGroup parent, int viewType);
}

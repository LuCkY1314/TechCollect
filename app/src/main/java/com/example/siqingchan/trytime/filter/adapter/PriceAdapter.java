package com.example.siqingchan.trytime.filter.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.siqingchan.trytime.R;

/**
 * Created by siqingchan on 2017/5/10.
 */

public class PriceAdapter extends AbsExtendFilterAdapter<String> {
    private Context context;

    public PriceAdapter(Context context, int variableId) {
        super(context, variableId);
        this.context = context;
    }

    @Override
    protected void addOtherListener(ViewDataBinding binding, String data, int type) {

    }

    @Override
    public ViewDataBinding inflaterView(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            return DataBindingUtil.inflate(layoutInflater, R.layout.item_filter_price_menu_all, parent, false);
        } else {
            return DataBindingUtil.inflate(layoutInflater, R.layout.item_filter_type_price_menu, parent, false);
        }
    }
}

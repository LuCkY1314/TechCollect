package com.example.siqingchan.trytime.filter.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.databinding.ItemFilterDistictMenuAllBinding;
import com.example.siqingchan.trytime.databinding.ItemFilterDistictMenuBinding;
import com.example.siqingchan.trytime.filter.data.District;

/**
 * Created by siqingchan on 2017/5/10.
 */

public class FirstAdapter extends AbsExtendFilterAdapter<District.DistrictsBean> {
    private Context context;

    public FirstAdapter(Context context, int variableId) {
        super(context, variableId);
        this.context = context;
    }

    @Override
    protected void addOtherListener(ViewDataBinding binding, District.DistrictsBean data, int type) {
        if (type == HEAD_TYPE) {
            final ItemFilterDistictMenuAllBinding databinding = (ItemFilterDistictMenuAllBinding) binding;
            databinding.selectorTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, databinding.selectorTv.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final ItemFilterDistictMenuBinding databinding = (ItemFilterDistictMenuBinding) binding;
            databinding.selectorTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, databinding.selectorTv.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public ViewDataBinding inflaterView(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            return DataBindingUtil.inflate(layoutInflater, R.layout.item_filter_distict_menu_all, parent, false);
        } else {
            return DataBindingUtil.inflate(layoutInflater, R.layout.item_filter_distict_menu, parent, false);
        }
    }
}

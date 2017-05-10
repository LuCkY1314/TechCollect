package com.example.siqingchan.trytime.filter.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.databinding.ItemFilterMenuAllBinding;
import com.example.siqingchan.trytime.databinding.ItemFilterMenuBinding;
import com.example.siqingchan.trytime.filter.data.District;

/**
 * Created by siqingchan on 2017/5/10.
 */

public class FirstAdapter extends ExtendFilterAdapter<District.DistrictsBean> {
    private LayoutInflater layoutInflater;
    private Context context;

    public FirstAdapter(Context context, int variableId) {
        super(context, variableId);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    protected void addOtherListener(ViewDataBinding binding, District.DistrictsBean data, int type) {
        if (type == HEAD_TYPE) {
            final ItemFilterMenuAllBinding databinding = (ItemFilterMenuAllBinding) binding;
            databinding.selectorTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, databinding.selectorTv.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            final ItemFilterMenuBinding databinding = (ItemFilterMenuBinding) binding;
            databinding.selectorTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, databinding.selectorTv.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public ViewDataBinding inflaterView(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            return DataBindingUtil.inflate(layoutInflater, R.layout.item_filter_menu_all, parent, false);
        } else {
            return DataBindingUtil.inflate(layoutInflater, R.layout.item_filter_menu, parent, false);
        }
    }
}

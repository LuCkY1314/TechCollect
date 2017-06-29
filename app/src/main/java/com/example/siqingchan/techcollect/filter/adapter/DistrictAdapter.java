package com.example.siqingchan.techcollect.filter.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.siqingchan.techcollect.R;
import com.example.siqingchan.techcollect.databinding.ItemFilterDistictMenuAllBinding;
import com.example.siqingchan.techcollect.databinding.ItemFilterDistictMenuBinding;
import com.example.siqingchan.techcollect.filter.data.DistrictData;

/**
 * Created by siqingchan on 2017/5/10.
 * 区域选择recycleView adapter
 */

public class DistrictAdapter extends AbsExtendFilterAdapter<DistrictData.DistrictsBean> {
    private Context context;

    public DistrictAdapter(Context context, int variableId) {
        super(context, variableId);
        this.context = context;
    }

    @Override
    protected void addOtherListener(ViewDataBinding binding, DistrictData.DistrictsBean data, int type) {
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

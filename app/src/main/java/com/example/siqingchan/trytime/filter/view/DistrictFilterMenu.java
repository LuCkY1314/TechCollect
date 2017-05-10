package com.example.siqingchan.trytime.filter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.filter.adapter.DistrictAdapter;
import com.example.siqingchan.trytime.filter.data.District;
import com.example.siqingchan.trytime.filter.listener.OnFilterMenuItemClickListener;

/**
 * Created by siqingchan on 2017/5/8.
 */

public class DistrictFilterMenu extends LinearLayout {

    public DistrictFilterMenu(final Context context, District data) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.filter_extend_view, null);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(context, VERTICAL, false));
        DistrictAdapter adapter = new DistrictAdapter(context, data.getDistricts());
        list.setAdapter(adapter);
        adapter.setListener(new OnFilterMenuItemClickListener<District.DistrictsBean>() {
            @Override
            public void onItemClick(District.DistrictsBean districtsBean) {
                Toast.makeText(context, districtsBean.getName() + "has been clicked", Toast.LENGTH_SHORT).show();
            }
        });
        FrameLayout customView = (FrameLayout) view.findViewById(R.id.custom_view);
        View extraView = initExtraView();
        if (extraView != null) {
            customView.addView(initExtraView());
        }
        addView(view);
    }

    /**
     * 自定义-recycleView下面的布局
     */
    private View initExtraView() {
        return null;
    }

}

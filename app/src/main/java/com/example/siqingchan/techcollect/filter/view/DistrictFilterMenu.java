package com.example.siqingchan.techcollect.filter.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.siqingchan.techcollect.R;
import com.example.siqingchan.techcollect.filter.adapter.AbsExtendFilterAdapter;
import com.example.siqingchan.techcollect.filter.data.DistrictData;

/**
 * Created by siqingchan on 2017/5/10.
 * 区域筛选菜单下拉窗
 */

public class DistrictFilterMenu extends BaseFilterMenu<DistrictData.DistrictsBean> {
    private AbsExtendFilterAdapter<DistrictData.DistrictsBean> adapter;

    public DistrictFilterMenu(Context context, AbsExtendFilterAdapter<DistrictData.DistrictsBean> adapter) {
        super(context, adapter);
        this.adapter = adapter;
    }

    @Override
    public View initExtraView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filter_foot, null);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "";
                for (DistrictData.DistrictsBean value : adapter.getData()) {
                    name = name + value.getName();
                }
                if (getListener() != null) {
                    getListener().onSelectCompleted();
                }
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

package com.example.siqingchan.trytime.filter.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.filter.adapter.AbsExtendFilterAdapter;
import com.example.siqingchan.trytime.filter.data.DistrictData;

/**
 * Created by siqingchan on 2017/5/10.
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
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

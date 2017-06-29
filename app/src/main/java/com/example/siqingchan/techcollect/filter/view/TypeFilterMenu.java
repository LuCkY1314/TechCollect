package com.example.siqingchan.techcollect.filter.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.siqingchan.techcollect.R;
import com.example.siqingchan.techcollect.filter.adapter.AbsExtendFilterAdapter;
import com.example.siqingchan.techcollect.filter.data.HouseTypeData;
import com.example.siqingchan.techcollect.filter.utils.PreferenceKey;
import com.example.siqingchan.techcollect.filter.utils.PreferenceUtil;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/10.
 * 户型筛选下拉菜单窗
 */

public class TypeFilterMenu extends BaseFilterMenu<HouseTypeData.NormalBean> {

    public TypeFilterMenu(Context context, AbsExtendFilterAdapter<HouseTypeData.NormalBean> adapter) {
        super(context, adapter);
    }

    @Override
    public View initExtraView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filter_foot, null);
        view.findViewById(R.id.ok_tv).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<HouseTypeData.NormalBean> data = getData();
                String values = "";
                for (HouseTypeData.NormalBean bean : data) {
                    if (bean.isSelect()) {
                        values = values + bean.getEnumId() + ",";
                    }
                }
                if (values.length() > 0) {
                    values = values.substring(0, values.length() - 1);
                }
                if (values.contains("-1"))
                    values = "-1";
                if (getListener() != null) {
                    getListener().onSelectCompleted();
                }
                PreferenceUtil.getInstance(context).putString(PreferenceKey.HOUSE_TYPE_FILTER, values);
                Toast.makeText(context, "you hand in type enumId collection: " + values, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

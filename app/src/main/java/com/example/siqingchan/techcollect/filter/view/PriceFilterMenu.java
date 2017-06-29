package com.example.siqingchan.techcollect.filter.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.siqingchan.techcollect.R;
import com.example.siqingchan.techcollect.filter.adapter.AbsExtendFilterAdapter;
import com.example.siqingchan.techcollect.filter.data.PriceData;
import com.example.siqingchan.techcollect.filter.utils.PreferenceKey;
import com.example.siqingchan.techcollect.filter.utils.PreferenceUtil;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/12.
 * 价格筛选菜单下拉窗
 */

public class PriceFilterMenu extends BaseFilterMenu<PriceData.NormalBean> {

    public PriceFilterMenu(Context context, AbsExtendFilterAdapter<PriceData.NormalBean> adapter) {
        super(context, adapter);
    }

    @Override
    public View initExtraView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filter_foot, null);
        view.findViewById(R.id.ok_tv).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PriceData.NormalBean> data = getData();
                String values = "";
                for (PriceData.NormalBean bean : data) {
                    if (bean.isSelect()) {
                        values = values + bean.getEnumId() + ",";
                    }
                }
                if (values.length() > 0) {
                    values = values.substring(0, values.length() - 1);
                }
                if (values.contains("-1")) {
                    values = "-1";
                }
                if (getListener() != null) {
                    getListener().onSelectCompleted();
                }
                PreferenceUtil.getInstance(context).putString(PreferenceKey.HOUSE_PRICE_FILTER, values);
                Toast.makeText(context, "you hand in enumId collection: " + values, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

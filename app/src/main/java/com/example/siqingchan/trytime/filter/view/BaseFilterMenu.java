package com.example.siqingchan.trytime.filter.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.filter.adapter.AbsExtendFilterAdapter;

/**
 * Created by siqingchan on 2017/5/8.
 */

public class BaseFilterMenu<T> extends LinearLayout {

    public BaseFilterMenu(final Context context, AbsExtendFilterAdapter<T> adapter) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.filter_extend_view, null);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(context, VERTICAL, false));
        list.setAdapter(adapter);
        FrameLayout customView = (FrameLayout) view.findViewById(R.id.custom_view);
        View extraView = initExtraView(context);
        if (extraView != null) {
            customView.addView(extraView);
        }
        addView(view);
    }

    /**
     * 自定义-recycleView下面的布局
     */
    public View initExtraView(Context context) {
        return null;
    }

}

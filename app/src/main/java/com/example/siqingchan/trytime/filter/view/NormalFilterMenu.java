package com.example.siqingchan.trytime.filter.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.filter.adapter.AbsExtendFilterAdapter;

/**
 * Created by siqingchan on 2017/5/10.
 */

public class NormalFilterMenu extends BaseFilterMenu<String> {
    private AbsExtendFilterAdapter adapter;

    public NormalFilterMenu(Context context, AbsExtendFilterAdapter<String> adapter) {
        super(context, adapter);
        this.adapter = adapter;
    }

    @Override
    public View initExtraView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filter_foot, null);
        view.findViewById(R.id.ok_tv).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = adapter.getData().size();
                Toast.makeText(context, "you hand in " + num + "selector", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

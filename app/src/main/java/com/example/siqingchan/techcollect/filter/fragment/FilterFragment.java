package com.example.siqingchan.techcollect.filter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjuke.android.commonutils.UIUtils;
import com.example.siqingchan.techcollect.R;
import com.example.siqingchan.techcollect.filter.listener.OnFilterTitleClick;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/8.
 */

public class FilterFragment extends Fragment implements View.OnClickListener{
    private LinearLayout filterTitleHolder;
    private OnFilterTitleClick listener;
    /**
     * titles一定要在fragment展示之前赋值（ft.commit之前）
     */
    private List<String> titles;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public OnFilterTitleClick getListener() {
        return listener;
    }

    public void setListener(OnFilterTitleClick listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        filterTitleHolder = (LinearLayout) view.findViewById(R.id.filter_title_holder);
        addFilterTitle();
        UIUtils.initDisplayMetrics(getActivity().getWindowManager());
        return view;
    }

    private void addFilterTitle() {
        for (String title : titles) {
            addTitle(title, titles.indexOf(title));
        }
    }

    private void addTitle(String title, int position) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearParams.weight = 1;
        linearLayout.setLayoutParams(linearParams);
        TextView textView = new TextView(getContext());
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(UIUtils.dip2Px(6), UIUtils.dip2Px(3));
        imageParams.setMargins(UIUtils.dip2Px(5), 0, 0, 0);
        imageView.setLayoutParams(imageParams);
        textView.setText(title);
        imageView.setBackgroundResource(R.drawable.icon_filter_arrow_n);
        linearLayout.setOnClickListener(this);
        linearLayout.setTag(title);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);
        linearLayout.addView(imageView);
        filterTitleHolder.addView(linearLayout);
        if (position != title.length()) {
            View line = new View(getContext());
            LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(UIUtils.dip2Px(1), UIUtils.dip2Px(20));
            lineParams.gravity = Gravity.CENTER_VERTICAL;
            line.setBackgroundResource(R.color.jkjH6GYColor);
            line.setLayoutParams(lineParams);
            filterTitleHolder.addView(line);
        }
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        listener.onTitleClick(v, tag);
    }

}

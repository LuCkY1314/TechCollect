package com.example.siqingchan.trytime.filter.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.anjuke.android.commonutils.UIUtils;
import com.example.siqingchan.trytime.BR;
import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.filter.adapter.AbsExtendFilterAdapter;
import com.example.siqingchan.trytime.filter.adapter.FirstAdapter;
import com.example.siqingchan.trytime.filter.adapter.PriceAdapter;
import com.example.siqingchan.trytime.filter.adapter.TypeAdapter;
import com.example.siqingchan.trytime.filter.data.District;
import com.example.siqingchan.trytime.filter.fragment.FilterFragment;
import com.example.siqingchan.trytime.filter.listener.OnFilterMenuItemClickListener;
import com.example.siqingchan.trytime.filter.listener.OnFilterTitleClick;
import com.example.siqingchan.trytime.filter.view.BaseFilterMenu;
import com.example.siqingchan.trytime.filter.view.DistrictFilterMenu;
import com.example.siqingchan.trytime.filter.view.NormalFilterMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFilterMenuItemClickListener<District.DistrictsBean> {
    private String districtJson = "{\"districts\":[{\"name\":\"全部\",\"value\":-1},{\"name\":\"岳麓\",\"value\":3470,\"blocks\":[{\"name\":\"西湖公园\",\"value\":3477},{\"name\":\"观沙岭\",\"value\":3479}]},{\"name\":\"望城\",\"value\":9211,\"blocks\":[{\"name\":\"雷锋大道\",\"value\":9212},{\"name\":\"望城区政府\",\"value\":9213}]}]}";
    private List<String> titleList = new ArrayList<>();
    private OnFilterTitleClick listener;
    District district;
    List<String> houseType;
    List<String> housePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIUtils.initDisplayMetrics(getWindowManager());
        initData();
        initListener();
        addFragment();
       // initView();
    }

    private void initView() {
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        FirstAdapter adapter = new FirstAdapter(this, BR.item);
        adapter.setData(district.getDistricts());
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter.setListener(this);
        list.setAdapter(adapter);
    }

    private void initListener() {
        listener = new OnFilterTitleClick() {
            @Override
            public void onTitleClick(View view, String tag) {
                switch (tag) {
                    case "区域":
                        FirstAdapter adapter = new FirstAdapter(MainActivity.this, BR.item);
                        adapter.setData(district.getDistricts());
                        adapter.setListener(new OnFilterMenuItemClickListener<District.DistrictsBean>() {
                            @Override
                            public void onItemClick(District.DistrictsBean districtsBean) {
                                Toast.makeText(MainActivity.this, districtsBean.getName() + districtsBean.getValue(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        DistrictFilterMenu menu = new DistrictFilterMenu(MainActivity.this, adapter);
                        showFilterMenu(view, menu, adapter);
                        break;
                    case "户型":
                        TypeAdapter adapter1 = new TypeAdapter(MainActivity.this, BR.item);
                        adapter1.setData(houseType);
                        NormalFilterMenu menu1 = new NormalFilterMenu(MainActivity.this, adapter1);
                        showFilterMenu(view, menu1, adapter1);
                        break;
                    case "价格":
                        PriceAdapter adapter2 = new PriceAdapter(MainActivity.this, BR.item);
                        adapter2.setData(housePrice);
                        NormalFilterMenu menu2 = new NormalFilterMenu(MainActivity.this, adapter2);
                        showFilterMenu(view, menu2, adapter2);
                        break;
                }
            }
        };
    }

    /**
     * 显示filter点击后的菜单
     *
     * @param accordingView
     * @param filterMenu
     * @param adapter
     */
    private void showFilterMenu(View accordingView, BaseFilterMenu filterMenu, AbsExtendFilterAdapter adapter) {
        filterMenu.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        PopupWindow popupWindow = new PopupWindow(accordingView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(filterMenu);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(accordingView);
    }

    private void initData() {
        district = new Gson().fromJson(districtJson, District.class);
        houseType = new ArrayList<>();
        houseType.add("不限");
        houseType.add("1室");
        houseType.add("2室");
        houseType.add("3室");
        houseType.add("4室");
        houseType.add("5室");
        houseType.add("5室以上");
        housePrice = new ArrayList<>();
        housePrice.add("50万元以下");
        housePrice.add("50-80万元");
        housePrice.add("80-100万元");
        housePrice.add("100-120万元");
        housePrice.add("120-150万元");
        housePrice.add("150-200万元");
        housePrice.add("150-200万元");
        titleList.add("区域");
        titleList.add("户型");
        titleList.add("价格");
    }

    private void addFragment() {
        FilterFragment filterFragment = new FilterFragment();
        filterFragment.setTitles(titleList);
        filterFragment.setListener(listener);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.filter, filterFragment);
        ft.commit();
    }

    @Override
    public void onItemClick(District.DistrictsBean districtsBean) {
        Toast.makeText(this, districtsBean.getName() + districtsBean.getValue(), Toast.LENGTH_SHORT).show();
    }
}

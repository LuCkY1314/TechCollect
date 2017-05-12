package com.example.siqingchan.trytime.filter.activity;

import android.databinding.ViewDataBinding;
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
import com.example.siqingchan.trytime.databinding.ItemFilterDistictMenuAllBinding;
import com.example.siqingchan.trytime.databinding.ItemFilterDistictMenuBinding;
import com.example.siqingchan.trytime.filter.adapter.AbsExtendFilterAdapter;
import com.example.siqingchan.trytime.filter.adapter.DistrictAdapter;
import com.example.siqingchan.trytime.filter.adapter.PriceAdapter;
import com.example.siqingchan.trytime.filter.adapter.TypeAdapter;
import com.example.siqingchan.trytime.filter.data.DistrictData;
import com.example.siqingchan.trytime.filter.data.HouseTypeData;
import com.example.siqingchan.trytime.filter.data.PriceData;
import com.example.siqingchan.trytime.filter.fragment.FilterFragment;
import com.example.siqingchan.trytime.filter.listener.OnFilterMenuItemClickListener;
import com.example.siqingchan.trytime.filter.listener.OnFilterTitleClick;
import com.example.siqingchan.trytime.filter.view.BaseFilterMenu;
import com.example.siqingchan.trytime.filter.view.DistrictFilterMenu;
import com.example.siqingchan.trytime.filter.view.NormalFilterMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String districtJson = "{\"districts\":[{\"name\":\"全部\",\"value\":-1},{\"name\":\"岳麓\",\"value\":3470,\"blocks\":[{\"name\":\"西湖公园\",\"value\":3477},{\"name\":\"观沙岭\",\"value\":3479}]},{\"name\":\"望城\",\"value\":9211,\"blocks\":[{\"name\":\"雷锋大道\",\"value\":9212},{\"name\":\"望城区政府\",\"value\":9213}]}]}";
    private String houseTypeJson = "{\"unlimited\":{\"enumId\":\"-1\",\"enumValue\":\"不限\"},\"normal\":[{\"enumId\":\"1\",\"enumValue\":\"1室\"},{\"enumId\":\"2\",\"enumValue\":\"2室\"}]}";
    private String priceJson = "{\"normal\":[{\"enumValue\":\"100万元以下\",\"enumId\":\"0,100\"},{\"enumValue\":\"50-100万元\",\"enumId\":\"50,100\"}],\"unlimited\":{\"enumValue\":\"全部\",\"enumId\":\"-1\"}}";
    private List<String> titleList = new ArrayList<>();
    DistrictData districtData;
    HouseTypeData houseType;
    PriceData housePrice;
    private int HEAD_TYPE = 1, NORMAL_TYPE = 2;
    /**
     * filter的title点击事件回调
     */
    private OnFilterTitleClick listener;
    /**
     * 三个filter的更多菜单popWindow中contentView里recycleView的adapter
     */
    private AbsExtendFilterAdapter adapter, adapter1, adapter2;
    /**
     * 三个filter的更多菜单popWindow中contentView
     */
    private BaseFilterMenu menu, menu1, menu2;
    /**
     * 三个filter的更多菜单popWindow
     */
    private PopupWindow districtWindow, typeWindow, priceWindow;

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
        DistrictAdapter adapter = new DistrictAdapter(this, BR.item);
        adapter.setData(districtData.getDistricts());
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    /**
     * 回调实现
     */
    private void initListener() {
        listener = new OnFilterTitleClick() {
            @Override
            public void onTitleClick(View view, String tag) {
                switch (tag) {
                    case "区域":
                        if (adapter == null) {
                            adapter = new DistrictAdapter(MainActivity.this, BR.item);
                            adapter.setData(districtData.getDistricts());
                            adapter.setListener(new OnFilterMenuItemClickListener<DistrictData.DistrictsBean>() {
                                @Override
                                public void onItemClick(DistrictData.DistrictsBean districtsBean, ViewDataBinding dataBinding, int type) {
                                    if (type == HEAD_TYPE) {
                                        ItemFilterDistictMenuAllBinding binding = (ItemFilterDistictMenuAllBinding) dataBinding;
                                        if (districtsBean.isSelect())
                                        binding.selectorTv.setSelected(true);
                                    } else {
                                        ItemFilterDistictMenuBinding binding = (ItemFilterDistictMenuBinding) dataBinding;
                                        binding.selectorIv.setSelected(true);
                                        binding.selectorTv.setSelected(true);
                                    }
                                    Toast.makeText(MainActivity.this, districtsBean.getName() + districtsBean.getValue(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        if (menu == null) {
                            menu = new DistrictFilterMenu(MainActivity.this, adapter);
                        }
                        if (districtWindow == null) {
                            districtWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT,
                                    WindowManager.LayoutParams.WRAP_CONTENT);
                            newFilterMenu(menu, districtWindow);
                        }
                        initAndShowFilter(districtWindow, view);
                        break;
                    case "户型":
                        if (adapter1 == null) {
                            adapter1 = new TypeAdapter(MainActivity.this, BR.item);
                            List<HouseTypeData.NormalBean> data = houseType.getNormal();
                            HouseTypeData.NormalBean bean = new HouseTypeData.NormalBean();
                            bean.setEnumId(houseType.getUnlimited().getEnumId());
                            bean.setEnumValue(houseType.getUnlimited().getEnumValue());
                            data.add(0, bean);
                            adapter1.setData(data);
                        }
                        if (menu1 == null) {
                            menu1 = new NormalFilterMenu(MainActivity.this, adapter1);
                        }
                        if (typeWindow == null) {
                            typeWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT,
                                    WindowManager.LayoutParams.WRAP_CONTENT);
                            newFilterMenu(menu1, typeWindow);
                        }
                        initAndShowFilter(typeWindow, view);
                        break;
                    case "价格":
                        if (adapter2 == null) {
                            adapter2 = new PriceAdapter(MainActivity.this, BR.item);
                            List<PriceData.NormalBean> data = housePrice.getNormal();
                            PriceData.NormalBean bean = new PriceData.NormalBean();
                            bean.setEnumId(housePrice.getUnlimited().getEnumId());
                            bean.setEnumValue(housePrice.getUnlimited().getEnumValue());
                            data.add(0, bean);
                            adapter2.setData(data);
                        }
                        if (menu2 == null) {
                            menu2 = new NormalFilterMenu(MainActivity.this, adapter2);
                        }
                        if (priceWindow == null) {
                            priceWindow = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT,
                                    WindowManager.LayoutParams.WRAP_CONTENT);
                            newFilterMenu(menu2, priceWindow);
                        }
                        initAndShowFilter(priceWindow, view);
                        break;
                }
            }
        };
    }

    /**
     * 显示filter点击后的菜单
     *
     * @param filterMenu
     */
    private void newFilterMenu(BaseFilterMenu filterMenu, PopupWindow popupWindow) {
        filterMenu.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        popupWindow.setContentView(filterMenu);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);

    }

    /**
     * 初始化一次popWindow，控制点击同一title的出现和消失逻辑
     *
     * @param popupWindow
     * @param accordingView
     */
    public void initAndShowFilter(PopupWindow popupWindow, View accordingView) {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(accordingView);
        }
    }

    private void initData() {
        districtData = new Gson().fromJson(districtJson, DistrictData.class);
        houseType = new Gson().fromJson(houseTypeJson, HouseTypeData.class);
        housePrice = new Gson().fromJson(priceJson, PriceData.class);
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

}

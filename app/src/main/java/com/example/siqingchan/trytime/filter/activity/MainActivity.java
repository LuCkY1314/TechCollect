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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.anjuke.android.commonutils.UIUtils;
import com.example.framework.base.BaseApplication;
import com.example.framework.http.callback.BaseCallback;
import com.example.framework.util.FramePreferenceKey;
import com.example.siqingchan.trytime.BR;
import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.databinding.ItemFilterDistictMenuAllBinding;
import com.example.siqingchan.trytime.databinding.ItemFilterDistictMenuBinding;
import com.example.siqingchan.trytime.databinding.ItemFilterPriceMenuAllBinding;
import com.example.siqingchan.trytime.databinding.ItemFilterTypeMenuAllBinding;
import com.example.siqingchan.trytime.databinding.ItemFilterTypeMenuBinding;
import com.example.siqingchan.trytime.databinding.ItemFilterTypePriceMenuBinding;
import com.example.siqingchan.trytime.filter.adapter.AbsExtendFilterAdapter;
import com.example.siqingchan.trytime.filter.adapter.DistrictAdapter;
import com.example.siqingchan.trytime.filter.adapter.PriceAdapter;
import com.example.siqingchan.trytime.filter.adapter.TypeAdapter;
import com.example.siqingchan.trytime.filter.data.DistrictData;
import com.example.siqingchan.trytime.filter.data.HouseTypeData;
import com.example.siqingchan.trytime.filter.data.PriceData;
import com.example.siqingchan.trytime.filter.fragment.FilterFragment;
import com.example.siqingchan.trytime.filter.fragment.ListFragment;
import com.example.siqingchan.trytime.filter.listener.OnFilterMenuItemClickListener;
import com.example.siqingchan.trytime.filter.listener.OnFilterSelectedListener;
import com.example.siqingchan.trytime.filter.listener.OnFilterTitleClick;
import com.example.siqingchan.trytime.filter.utils.PreferenceKey;
import com.example.siqingchan.trytime.filter.utils.PreferenceUtil;
import com.example.siqingchan.trytime.filter.view.BaseFilterMenu;
import com.example.siqingchan.trytime.filter.view.DistrictFilterMenu;
import com.example.siqingchan.trytime.filter.view.PriceFilterMenu;
import com.example.siqingchan.trytime.filter.view.TypeFilterMenu;
import com.example.siqingchan.trytime.http.data.User;
import com.example.siqingchan.trytime.http.request.ApiRequest;
import com.example.siqingchan.trytime.http.request.RequestCallback;
import com.example.siqingchan.trytime.http.result.ThirdLoginUserResult;
import com.example.siqingchan.trytime.http.util.ApiUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity implements OnFilterSelectedListener {
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
    /**
     * 列表fragment
     */
    private ListFragment listFragment;

    private RequestCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIUtils.initDisplayMetrics(getWindowManager());
        initData();
        initListener();
        addFragment();
        initCallback();
        buildContract();
        // initView();
    }

    private void initCallback() {
        callback = new RequestCallback<ThirdLoginUserResult>() {
            @Override
            public void onResponse(ThirdLoginUserResult response) {
                if (response.isSuccess()) {
                    Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                    String token = response.getData().getAccessToken();
                    String refreshToken = response.getData().getRefreshToken();
                    PreferenceUtil.getInstance(BaseApplication.getInstance()).putString(FramePreferenceKey.ACCESS_TOKEN, token);
                    PreferenceUtil.getInstance(BaseApplication.getInstance()).putString(FramePreferenceKey.FRESH_TOKEN, refreshToken);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","无网络");
            }
        };
    }

    private void buildContract() {
        ApiRequest.loginCompany(25, "上海公司", "shanghai01", "qwer1234", "", callback);
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
                        //创建对应的filter菜单的adapter
                        //实现adapter对应的整行item点击回调
                        //创建对应filter的下拉菜单窗口
                        //实现下拉菜单窗口的确定选择事件回调
                        if (adapter == null) {
                            adapter = new DistrictAdapter(MainActivity.this, BR.item);
                            adapter.setData(districtData.getDistricts());
                            adapter.setListener(new OnFilterMenuItemClickListener<DistrictData.DistrictsBean>() {
                                @Override
                                public void onItemClick(DistrictData.DistrictsBean districtsBean, ViewDataBinding dataBinding, int type) {
                                    districtsBean.setSelect(!districtsBean.isSelect());
                                    if (type == HEAD_TYPE) {
                                        ItemFilterDistictMenuAllBinding binding = (ItemFilterDistictMenuAllBinding) dataBinding;
                                        binding.selectorTv.setSelected(districtsBean.isSelect());
                                    } else {
                                        ItemFilterDistictMenuBinding binding = (ItemFilterDistictMenuBinding) dataBinding;
                                        binding.selectorIv.setSelected(districtsBean.isSelect());
                                        binding.selectorTv.setSelected(districtsBean.isSelect());
                                    }
                                    Toast.makeText(MainActivity.this, districtsBean.getName() + districtsBean.getValue(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        if (menu == null) {
                            menu = new DistrictFilterMenu(MainActivity.this, adapter);
                            menu.setListener(MainActivity.this);
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
                            adapter1.setListener(new OnFilterMenuItemClickListener<HouseTypeData.NormalBean>() {
                                @Override
                                public void onItemClick(HouseTypeData.NormalBean normalBean, ViewDataBinding dataBinding, int type) {
                                    normalBean.setSelect(!normalBean.isSelect());
                                    if (type == HEAD_TYPE) {
                                        ItemFilterTypeMenuAllBinding binding = (ItemFilterTypeMenuAllBinding) dataBinding;
                                        binding.selectorTv.setSelected(normalBean.isSelect());
                                    } else {
                                        ItemFilterTypeMenuBinding binding = (ItemFilterTypeMenuBinding) dataBinding;
                                        binding.selectorIv.setSelected(normalBean.isSelect());
                                        binding.selectorTv.setSelected(normalBean.isSelect());
                                    }
                                }
                            });
                        }
                        if (menu1 == null) {
                            menu1 = new TypeFilterMenu(MainActivity.this, adapter1);
                            menu1.setListener(MainActivity.this);
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
                            final List<PriceData.NormalBean> data = housePrice.getNormal();
                            PriceData.NormalBean bean = new PriceData.NormalBean();
                            bean.setEnumId(housePrice.getUnlimited().getEnumId());
                            bean.setEnumValue(housePrice.getUnlimited().getEnumValue());
                            data.add(0, bean);
                            adapter2.setData(data);
                            adapter2.setListener(new OnFilterMenuItemClickListener<PriceData.NormalBean>() {
                                @Override
                                public void onItemClick(PriceData.NormalBean normalBean, ViewDataBinding dataBinding, int type) {
                                    normalBean.setSelect(!normalBean.isSelect());
                                    if (type == HEAD_TYPE) {
                                        ItemFilterPriceMenuAllBinding binding = (ItemFilterPriceMenuAllBinding) dataBinding;
                                        binding.selectorTv.setSelected(normalBean.isSelect());
                                    } else {
                                        ItemFilterTypePriceMenuBinding binding = (ItemFilterTypePriceMenuBinding) dataBinding;
                                        binding.selectorIv.setSelected(normalBean.isSelect());
                                        binding.selectorTv.setSelected(normalBean.isSelect());
                                    }
                                }
                            });
                        }
                        if (menu2 == null) {
                            menu2 = new PriceFilterMenu(MainActivity.this, adapter2);
                            menu2.setListener(MainActivity.this);
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
        listFragment = new ListFragment();
        ft.add(R.id.list, listFragment);
        ft.commit();

    }

    @Override
    public void onSelectCompleted() {
        String typeValue = PreferenceUtil.getInstance(this).getString(PreferenceKey.HOUSE_TYPE_FILTER);
        String priceValue = PreferenceUtil.getInstance(this).getString(PreferenceKey.HOUSE_PRICE_FILTER);
        listFragment.refresh();
    }
}

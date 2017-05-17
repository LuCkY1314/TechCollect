package com.example.siqingchan.trytime.details.activity;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.anjuke.android.commonutils.UIUtils;
import com.example.framework.base.BaseApplication;
import com.example.framework.util.FramePreferenceKey;
import com.example.siqingchan.trytime.BR;
import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.databinding.ActivityDetailsBinding;
import com.example.siqingchan.trytime.details.data.CompanyRentHouseCustomerDetailsData;
import com.example.siqingchan.trytime.details.listener.Presenter;
import com.example.siqingchan.trytime.details.result.CompanyRentHouseCustomerDetailsResult;
import com.example.siqingchan.trytime.filter.utils.PreferenceUtil;
import com.example.siqingchan.trytime.http.data.User;
import com.example.siqingchan.trytime.http.request.ApiRequest;
import com.example.siqingchan.trytime.http.request.RequestCallback;
import com.example.siqingchan.trytime.http.result.ThirdLoginUserResult;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/16.
 */

public class DetailsActivity extends BaseDetailsActivity<ActivityDetailsBinding> {
    private CompanyRentHouseCustomerDetailsData data;
    private User user;
    private RequestCallback customerDetailsCallback, callback;
    private boolean mainInfoOff;
    private String telephone;
    private int sex;
    private static final int LADY = 1, MAN = 2;


    @Override
    public void initCallback(final ActivityDetailsBinding dataBinding) {
        customerDetailsCallback = new RequestCallback<CompanyRentHouseCustomerDetailsResult>() {
            @Override
            public void onResponse(CompanyRentHouseCustomerDetailsResult response) {
                super.onResponse(response);
                if (response.isSuccess()) {
                    updateUI(response.getData());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }

            /**
             * 更新界面
             * @param data
             */

            private void updateUI(CompanyRentHouseCustomerDetailsData data) {
                dataBinding.setDetails(data);
                long publishTime = data.getRegistrationTime();
                dataBinding.publishTv.setText(publishTime + "");
                String blocks = "";
                for (String block : data.getBlockNames()) {
                    blocks = blocks + block + "；";
                }
                if (!TextUtils.isEmpty(blocks)) {
                    blocks = blocks.substring(0, blocks.length() - 1);
                }
                dataBinding.wantBlockTv.setText(blocks);
                String communities = "";
                for (String community : data.getCommunityNames()) {
                    communities = communities + community + "；";
                }
                if (!TextUtils.isEmpty(communities)) {
                    communities = communities.substring(0, communities.length() - 1);
                }
                dataBinding.wantCommunityTv.setText(communities);
                if (dataBinding.label.getChildCount() > 0) {
                    dataBinding.label.removeAllViews();
                }
                addLabel(data.getCustomerFixedLabel());
                getParams(data);
                showWitchUIStyle(data);
                /*if (data.getFollowPermission() == HAS_AUTHORITY) {
                    followIv.setVisibility(VISIBLE);
                } else {
                    followIv.setVisibility(View.GONE);
                }*/
               /* if (dataBinding.followRecordLl.getChildCount() > 0) {
                    dataBinding.followRecordLl.removeAllViews();
                }
                addFollowRecord(data.getFollowDetails());*/
            }

            /**
             * 底部ui样式显示判断
             *
             * @param data
             */

            private void showWitchUIStyle(CompanyRentHouseCustomerDetailsData data) {
                if (mainInfoOff == true) {
                    dataBinding.callLl.setVisibility(View.GONE);
                    dataBinding.showInfoTv.setVisibility(View.VISIBLE);
                    dataBinding.departmentTv.setVisibility(View.GONE);
                    if (data.getSex() == LADY) {
                        dataBinding.headiconIv.setBackgroundResource(R.drawable.icon_tx_women);
                    } else if (data.getSex() == MAN) {
                        dataBinding.headiconIv.setBackgroundResource(R.drawable.icon_tx_men);
                    }
                } else {
                    dataBinding.callLl.setVisibility(View.VISIBLE);
                    dataBinding.showInfoTv.setVisibility(View.GONE);
                    dataBinding.departmentTv.setVisibility(View.VISIBLE);
                    dataBinding.headiconIv.setBackgroundResource(R.drawable.pic_login_tx);
                }
            }

            private void getParams(CompanyRentHouseCustomerDetailsData data) {
                mainInfoOff = data.isWatchCore();
                telephone = data.getTelephone();
                sex = data.getSex();
            }

            /**
             * 添加标签图文
             *
             * @param fixLabel
             */
            private void addLabel(List<String> fixLabel) {
                for (String labelText : fixLabel) {
                    if (TextUtils.isEmpty(labelText)) {
                        continue;
                    }
                    TextView textView = new TextView(DetailsActivity.this);
                    textView.setText(labelText);
                    textView.setPadding(UIUtils.dip2Px(4), 0, UIUtils.dip2Px(4), UIUtils.dip2Px(2));
                    textView.setTextSize(12);
                    textView.setBackgroundResource(R.drawable.company_second_house_details_fix_label_bg);
                    textView.setTextColor(getResources().getColor(R.color.label_text_color));
                    dataBinding.label.addView(textView);
                }
            }
        };
        callback = new RequestCallback<ThirdLoginUserResult>() {
            @Override
            public void onResponse(ThirdLoginUserResult response) {
                if (response.isSuccess()) {
                    Toast.makeText(DetailsActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                    String token = response.getData().getAccessToken();
                    user = response.getData().getUser();
                    String refreshToken = response.getData().getRefreshToken();
                    PreferenceUtil.getInstance(BaseApplication.getInstance()).putString(FramePreferenceKey.ACCESS_TOKEN, token);
                    PreferenceUtil.getInstance(BaseApplication.getInstance()).putString(FramePreferenceKey.FRESH_TOKEN, refreshToken);
                    ApiRequest.getRentHouseCustomerDetailsData(25, user.getCityId(), user.getCompanyId(), user.getDepartmentId(), user.getRoleId(), "161606330883510323", customerDetailsCallback);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "无网络");
            }
        };
    }

    @Override
    public void initListener(ActivityDetailsBinding dataBinding) {
        Presenter presenter = new Presenter(this, dataBinding);
        setBindingListener(BR.presenter, presenter);
    }

    @Override
    public void getPreviousData() {
        UIUtils.initDisplayMetrics(getWindowManager());
    }

    @Override
    public void requestHttpData(ActivityDetailsBinding dataBinding) {
        ApiRequest.loginCompany(25, "上海公司", "shanghai01", "qwer1234", "", callback);
    }

    @Override
    public void setXml() {
        setXmlVariable(R.layout.activity_details, BR.details, data);
    }
}

package com.example.siqingchan.techcollect.details.listener;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.example.siqingchan.techcollect.R;
import com.example.siqingchan.techcollect.databinding.ActivityDetailsBinding;

public class Presenter extends BaseListener<ActivityDetailsBinding> {
    private Activity activity;

    public Presenter(Activity activity, ActivityDetailsBinding dataBinding) {
        super(activity, dataBinding);
        this.activity = activity;
    }


    //点击更多信息或收起
    public void onExtendInfoClick(View view) {
        changeExtendInfoShowState();
    }

    //点击更多跟进记录
    public void onMoreFollowInfoClick(View view) {
    }

    //点击电话联系
    public void onCallClick(String telephone) {
        if (TextUtils.isEmpty(telephone)) {
            //PopupUtils.showToastShort("电话号码无效");
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telephone));
            activity.startActivity(intent);
        }
    }

    //点击查看核心信息
    public void onShowMainInfoClick(View view) {
        //GathererApis.requestRentHouseCustomerMainInfoAuthority(AppUserUtil.getCityId(), AppUserUtil.getAccountId(), customerId, AppUserUtil.getDepartmentId(), AppUserUtil.getRoleId(), customerMainInfoCallback);
    }

    /**
     * 展开收起逻辑
     */
    private void changeExtendInfoShowState() {
        if (dataBinding.extendInfoRl.getVisibility() == View.VISIBLE) {
            dataBinding.extendInfoRl.setVisibility(View.GONE);
            dataBinding.extendTv.setText("更多信息");
            dataBinding.extendIv.setBackgroundResource(R.drawable.down_arrow_og_solid);
        } else {
            dataBinding.extendInfoRl.setVisibility(View.VISIBLE);
            dataBinding.extendTv.setText("收起");
            dataBinding.extendIv.setBackgroundResource(R.drawable.up_arrow_og_solid);
        }
    }
}
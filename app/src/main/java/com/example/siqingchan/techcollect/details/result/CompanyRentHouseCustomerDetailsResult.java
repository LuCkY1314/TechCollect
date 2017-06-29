package com.example.siqingchan.techcollect.details.result;

import com.example.siqingchan.techcollect.details.data.CompanyRentHouseCustomerDetailsData;
import com.example.siqingchan.techcollect.http.result.BaseResult;
import com.google.gson.annotations.SerializedName;


/**
 * Created by siqingchan on 2017/4/27.
 */

public class CompanyRentHouseCustomerDetailsResult extends BaseResult {

    /**
     * data : {"registration_time":1489992288000,"department_name":"我爱我家一部","vindicator":"王星星","block_names":["浦东新区"],"community_names":["峨山"],"price_range":"200-300","price_unit":"万","area_range":"50-60","area_unit":"平米","room_range":"1-2","floor_range":"3-5","decorate":"精装修、简单装修","orientation":"南北、西南","house_age_range":"5-10","check_in_time":"2017-04-15","like":"学区、地铁","dislike":"顶层、底楼","customer_id":"123451234512345","customer_source":"到店","registrant":"王星星","note":"","customer_fixed_label":["住宅","有效"],"customer_dynamic_label":[""],"watch_core":true,"telephone":"16762833672","owner":"","follow_details":[{"follow_time":"2017-03-01","follow_person":"王海洋","follow_status":"电话跟进"}],"sex":1,"follow_permission":1}
     */
    @SerializedName("data")
    private CompanyRentHouseCustomerDetailsData data;

    public CompanyRentHouseCustomerDetailsData getData() {
        return data;
    }

    public void setData(CompanyRentHouseCustomerDetailsData data) {
        this.data = data;
    }
}

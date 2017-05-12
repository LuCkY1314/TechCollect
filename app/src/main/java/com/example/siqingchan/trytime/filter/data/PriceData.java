package com.example.siqingchan.trytime.filter.data;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/10.
 */

public class PriceData extends BaseData {

    /**
     * normal : [{"enumValue":"100万元以下","enumId":"0,100"},{"enumValue":"50-100万元","enumId":"50,100"}]
     * unlimited : {"enumValue":"全部","enumId":"-1"}
     */

    @SerializedName("unlimited")
    private UnlimitedBean unlimited;
    @SerializedName("normal")
    private List<NormalBean> normal;

    public UnlimitedBean getUnlimited() {
        return unlimited;
    }

    public void setUnlimited(UnlimitedBean unlimited) {
        this.unlimited = unlimited;
    }

    public List<NormalBean> getNormal() {
        return normal;
    }

    public void setNormal(List<NormalBean> normal) {
        this.normal = normal;
    }

    public static class UnlimitedBean extends BaseData {
        /**
         * enumValue : 全部
         * enumId : -1
         */

        @SerializedName("enumValue")
        private String enumValue;
        @SerializedName("enumId")
        private String enumId;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getEnumValue() {
            return enumValue;
        }

        public void setEnumValue(String enumValue) {
            this.enumValue = enumValue;
        }

        public String getEnumId() {
            return enumId;
        }

        public void setEnumId(String enumId) {
            this.enumId = enumId;
        }
    }

    public static class NormalBean extends BaseData {
        /**
         * enumValue : 100万元以下
         * enumId : 0,100
         */

        @SerializedName("enumValue")
        private String enumValue;
        @SerializedName("enumId")
        private String enumId;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getEnumValue() {
            return enumValue;
        }

        public void setEnumValue(String enumValue) {
            this.enumValue = enumValue;
        }

        public String getEnumId() {
            return enumId;
        }

        public void setEnumId(String enumId) {
            this.enumId = enumId;
        }
    }
}

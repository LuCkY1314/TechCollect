package com.example.siqingchan.trytime.filter.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/10.
 */

public class HouseTypeData extends BaseData {

    /**
     * unlimited : {"enumId":"-1","enumValue":"不限"}
     * normal : [{"enumId":"1","enumValue":"1室"},{"enumId":"2","enumValue":"2室"}]
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

    public static class UnlimitedBean extends BaseData{
        /**
         * enumId : -1
         * enumValue : 不限
         */

        @SerializedName("enumId")
        private String enumId;
        @SerializedName("enumValue")
        private String enumValue;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
        public String getEnumId() {
            return enumId;
        }

        public void setEnumId(String enumId) {
            this.enumId = enumId;
        }

        public String getEnumValue() {
            return enumValue;
        }

        public void setEnumValue(String enumValue) {
            this.enumValue = enumValue;
        }
    }

    public static class NormalBean extends BaseData{
        /**
         * enumId : 1
         * enumValue : 1室
         */

        @SerializedName("enumId")
        private String enumId;
        @SerializedName("enumValue")
        private String enumValue;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
        public String getEnumId() {
            return enumId;
        }

        public void setEnumId(String enumId) {
            this.enumId = enumId;
        }

        public String getEnumValue() {
            return enumValue;
        }

        public void setEnumValue(String enumValue) {
            this.enumValue = enumValue;
        }
    }
}

package com.example.siqingchan.trytime.filter.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/8.
 */

public class DistrictData extends BaseData {

    @SerializedName("districts")
    private List<DistrictsBean> districts;

    public List<DistrictsBean> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictsBean> districts) {
        this.districts = districts;
    }

    public static class DistrictsBean extends BaseData {
        /**
         * name : 全部
         * value : -1
         * blocks : [{"name":"西湖公园","value":3477},{"name":"观沙岭","value":3479}]
         */

        @SerializedName("name")
        private String name;
        @SerializedName("value")
        private int value;
        @SerializedName("blocks")
        private List<BlocksBean> blocks;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public List<BlocksBean> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<BlocksBean> blocks) {
            this.blocks = blocks;
        }

        public static class BlocksBean extends BaseData {
            /**
             * name : 西湖公园
             * value : 3477
             */

            @SerializedName("name")
            private String name;
            @SerializedName("value")
            private int value;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}

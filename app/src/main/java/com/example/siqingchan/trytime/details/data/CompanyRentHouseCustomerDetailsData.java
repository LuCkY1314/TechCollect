package com.example.siqingchan.trytime.details.data;

import com.example.siqingchan.trytime.filter.data.BaseData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by siqingchan on 2017/4/27.
 */

public class CompanyRentHouseCustomerDetailsData extends BaseDetailsData {
    /**
     * registration_time : 1489992288000
     * department_name : 我爱我家一部
     * vindicator : 王星星
     * block_names : ["浦东新区"]
     * community_names : ["峨山"]
     * price_range : 200-300
     * price_unit : 万
     * area_range : 50-60
     * area_unit : 平米
     * room_range : 1-2
     * floor_range : 3-5
     * decorate : 精装修、简单装修
     * orientation : 南北、西南
     * check_in_time : 2017-04-15
     * like : 学区、地铁
     * dislike : 顶层、底楼
     * customer_id : 123451234512345
     * customer_source : 到店
     * registrant : 王星星
     * note :
     * customer_fixed_label : ["住宅","有效"]
     * watch_core : true
     * telephone : 16762833672
     * owner :
     * follow_details : [{"follow_time":"2017-03-01","follow_person":"王海洋","follow_status":"电话跟进"}]
     * sex : 1
     * follow_permission : 1
     */

    @SerializedName("registration_time")
    private long registrationTime;
    @SerializedName("department_name")
    private String departmentName;
    @SerializedName("vindicator")
    private String vindicator;
    @SerializedName("price_range")
    private String priceRange;
    @SerializedName("price_unit")
    private String priceUnit;
    @SerializedName("area_range")
    private String areaRange;
    @SerializedName("area_unit")
    private String areaUnit;
    @SerializedName("room_range")
    private String roomRange;
    @SerializedName("floor_range")
    private String floorRange;
    @SerializedName("decorate")
    private String decorate;
    @SerializedName("orientation")
    private String orientation;
    @SerializedName("check_in_time")
    private String checkInTime;
    @SerializedName("like")
    private String like;
    @SerializedName("dislike")
    private String dislike;
    @SerializedName("customer_id")
    private String customerId;
    @SerializedName("customer_source")
    private String customerSource;
    @SerializedName("registrant")
    private String registrant;
    @SerializedName("note")
    private String note;
    @SerializedName("watch_core")
    private boolean watchCore;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("owner")
    private String owner;
    @SerializedName("sex")
    private int sex;
    @SerializedName("follow_permission")
    private int followPermission;
    @SerializedName("block_names")
    private List<String> blockNames;
    @SerializedName("community_names")
    private List<String> communityNames;
    @SerializedName("customer_fixed_label")
    private List<String> customerFixedLabel;
    @SerializedName("follow_details")
    private List<FollowDetailsBean> followDetails;
    /**
     * budget : 2000-4000元/月
     * renter_type : 男性
     * share_type : 合租(主卧-朝南)
     */

    @SerializedName("budget")
    private String budget;
    @SerializedName("renter_type")
    private String renterType;
    @SerializedName("share_type")
    private String shareType;

    public long getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(long registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getVindicator() {
        return vindicator;
    }

    public void setVindicator(String vindicator) {
        this.vindicator = vindicator;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getAreaRange() {
        return areaRange;
    }

    public void setAreaRange(String areaRange) {
        this.areaRange = areaRange;
    }

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public String getRoomRange() {
        return roomRange;
    }

    public void setRoomRange(String roomRange) {
        this.roomRange = roomRange;
    }

    public String getFloorRange() {
        return floorRange;
    }

    public void setFloorRange(String floorRange) {
        this.floorRange = floorRange;
    }

    public String getDecorate() {
        return decorate;
    }

    public void setDecorate(String decorate) {
        this.decorate = decorate;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isWatchCore() {
        return watchCore;
    }

    public void setWatchCore(boolean watchCore) {
        this.watchCore = watchCore;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getFollowPermission() {
        return followPermission;
    }

    public void setFollowPermission(int followPermission) {
        this.followPermission = followPermission;
    }

    public List<String> getBlockNames() {
        return blockNames;
    }

    public void setBlockNames(List<String> blockNames) {
        this.blockNames = blockNames;
    }

    public List<String> getCommunityNames() {
        return communityNames;
    }

    public void setCommunityNames(List<String> communityNames) {
        this.communityNames = communityNames;
    }

    public List<String> getCustomerFixedLabel() {
        return customerFixedLabel;
    }

    public void setCustomerFixedLabel(List<String> customerFixedLabel) {
        this.customerFixedLabel = customerFixedLabel;
    }

    public List<FollowDetailsBean> getFollowDetails() {
        return followDetails;
    }

    public void setFollowDetails(List<FollowDetailsBean> followDetails) {
        this.followDetails = followDetails;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRenterType() {
        return renterType;
    }

    public void setRenterType(String renterType) {
        this.renterType = renterType;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public static class FollowDetailsBean extends BaseData {
        /**
         * follow_time : 2017-03-01
         * follow_person : 王海洋
         * follow_status : 电话跟进
         */

        @SerializedName("follow_time")
        private String followTime;
        @SerializedName("follow_person")
        private String followPerson;
        @SerializedName("follow_status")
        private String followStatus;

        public String getFollowTime() {
            return followTime;
        }

        public void setFollowTime(String followTime) {
            this.followTime = followTime;
        }

        public String getFollowPerson() {
            return followPerson;
        }

        public void setFollowPerson(String followPerson) {
            this.followPerson = followPerson;
        }

        public String getFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(String followStatus) {
            this.followStatus = followStatus;
        }
    }
}

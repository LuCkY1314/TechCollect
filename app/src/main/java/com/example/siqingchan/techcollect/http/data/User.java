package com.example.siqingchan.techcollect.http.data;

import com.example.siqingchan.techcollect.filter.data.BaseData;
import com.google.gson.annotations.SerializedName;

public class User extends BaseData {
    @SerializedName("account_id")
    private long accountId;
    @SerializedName("account_name")
    private String accountName;
    @SerializedName("true_name")
    private String trueName;
    @SerializedName("company_id")
    private int companyId;
    @SerializedName("company_name")
    private String companyName;
    @SerializedName("department_id")
    private int departmentId;
    @SerializedName("department_name")
    private String departmentName;
    @SerializedName("city_id")
    private int cityId;
    @SerializedName("city_name")
    private String cityName;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("email")
    private String email;
    @SerializedName("is_work")
    private int isWork;
    @SerializedName("position")
    private String position;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("is_general")
    private boolean isGeneral;
    /**
     * review_status : 3
     */
    @SerializedName("review_status")
    private int review_status;
    @SerializedName("is_vip")
    private boolean isVip;
    /**
     * is_bind_weixin : false
     * role_id : 150
     */

    @SerializedName("is_bind_weixin")
    private boolean isBindWeixin;
    @SerializedName("role_id")
    private int roleId;


    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(boolean general) {
        isGeneral = general;
    }

    public boolean IsVip() {
        return isVip;
    }

    public void setIsVip(boolean is_vip) {
        this.isVip = is_vip;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsWork() {
        return isWork;
    }

    public void setIsWork(int isWork) {
        this.isWork = isWork;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isIsGeneral() {
        return isGeneral;
    }

    public void setIsGeneral(boolean isGeneral) {
        this.isGeneral = isGeneral;
    }

    public int getReview_status() {
        return review_status;
    }

    public void setReview_status(int review_status) {
        this.review_status = review_status;
    }

    public boolean isIsBindWeixin() {
        return isBindWeixin;
    }

    public void setIsBindWeixin(boolean isBindWeixin) {
        this.isBindWeixin = isBindWeixin;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
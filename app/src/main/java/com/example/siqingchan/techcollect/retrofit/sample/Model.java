package com.example.siqingchan.techcollect.retrofit.sample;

import com.example.framework.http.result.BaseResult;
import com.google.gson.annotations.SerializedName;

/**
 * Created by siqingchan on 2017/9/20.
 */

public class Model extends BaseResult {


    /**
     * status : 200
     * code : 0
     * message :
     * data : {"acces_token":"ef4f0eb2ca8c2bfab3348d65623023e2","refresh_token":"a2f86ea5406adac9deae1fd0b8430c25","expire":"1506338535957","user":{"account_id":226681,"account_name":"shanghai01","true_name":"上海测试一","company_id":25,"company_name":"上海公司","department_id":44,"department_name":"上海公司","city_id":11,"city_name":"上海","telephone":"15184333333","email":"","is_work":1,"position":"150","avatar":"","is_general":false,"review_status":2,"is_vip":false,"is_bind_weixin":false,"role_id":150,"account_type":2,"need_check_phone":false,"temporary_telephone":"","jkj_version":"2"}}
     */

    @SerializedName("status")
    private int status;
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * acces_token : ef4f0eb2ca8c2bfab3348d65623023e2
         * refresh_token : a2f86ea5406adac9deae1fd0b8430c25
         * expire : 1506338535957
         * user : {"account_id":226681,"account_name":"shanghai01","true_name":"上海测试一","company_id":25,"company_name":"上海公司","department_id":44,"department_name":"上海公司","city_id":11,"city_name":"上海","telephone":"15184333333","email":"","is_work":1,"position":"150","avatar":"","is_general":false,"review_status":2,"is_vip":false,"is_bind_weixin":false,"role_id":150,"account_type":2,"need_check_phone":false,"temporary_telephone":"","jkj_version":"2"}
         */

        @SerializedName("acces_token")
        private String accesToken;
        @SerializedName("refresh_token")
        private String refreshToken;
        @SerializedName("expire")
        private String expire;
        @SerializedName("user")
        private UserBean user;

        public String getAccesToken() {
            return accesToken;
        }

        public void setAccesToken(String accesToken) {
            this.accesToken = accesToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * account_id : 226681
             * account_name : shanghai01
             * true_name : 上海测试一
             * company_id : 25
             * company_name : 上海公司
             * department_id : 44
             * department_name : 上海公司
             * city_id : 11
             * city_name : 上海
             * telephone : 15184333333
             * email :
             * is_work : 1
             * position : 150
             * avatar :
             * is_general : false
             * review_status : 2
             * is_vip : false
             * is_bind_weixin : false
             * role_id : 150
             * account_type : 2
             * need_check_phone : false
             * temporary_telephone :
             * jkj_version : 2
             */

            @SerializedName("account_id")
            private int accountId;
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
            @SerializedName("review_status")
            private int reviewStatus;
            @SerializedName("is_vip")
            private boolean isVip;
            @SerializedName("is_bind_weixin")
            private boolean isBindWeixin;
            @SerializedName("role_id")
            private int roleId;
            @SerializedName("account_type")
            private int accountType;
            @SerializedName("need_check_phone")
            private boolean needCheckPhone;
            @SerializedName("temporary_telephone")
            private String temporaryTelephone;
            @SerializedName("jkj_version")
            private String jkjVersion;

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
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

            public int getReviewStatus() {
                return reviewStatus;
            }

            public void setReviewStatus(int reviewStatus) {
                this.reviewStatus = reviewStatus;
            }

            public boolean isIsVip() {
                return isVip;
            }

            public void setIsVip(boolean isVip) {
                this.isVip = isVip;
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

            public int getAccountType() {
                return accountType;
            }

            public void setAccountType(int accountType) {
                this.accountType = accountType;
            }

            public boolean isNeedCheckPhone() {
                return needCheckPhone;
            }

            public void setNeedCheckPhone(boolean needCheckPhone) {
                this.needCheckPhone = needCheckPhone;
            }

            public String getTemporaryTelephone() {
                return temporaryTelephone;
            }

            public void setTemporaryTelephone(String temporaryTelephone) {
                this.temporaryTelephone = temporaryTelephone;
            }

            public String getJkjVersion() {
                return jkjVersion;
            }

            public void setJkjVersion(String jkjVersion) {
                this.jkjVersion = jkjVersion;
            }
        }
    }
}

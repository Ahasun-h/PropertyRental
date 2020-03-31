
package com.example.myapplication.UserInterestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInterest {

    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("post_title")
    @Expose
    private String postTitle;
    @SerializedName("post_division")
    @Expose
    private String postDivision;
    @SerializedName("post_city")
    @Expose
    private String postCity;
    @SerializedName("post_address")
    @Expose
    private String postAddress;
    @SerializedName("post_category")
    @Expose
    private String postCategory;
    @SerializedName("renters")
    @Expose
    private String renters;
    @SerializedName("rent_date")
    @Expose
    private String rentDate;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_mobile")
    @Expose
    private String userMobile;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDivision() {
        return postDivision;
    }

    public void setPostDivision(String postDivision) {
        this.postDivision = postDivision;
    }

    public String getPostCity() {
        return postCity;
    }

    public void setPostCity(String postCity) {
        this.postCity = postCity;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getRenters() {
        return renters;
    }

    public void setRenters(String renters) {
        this.renters = renters;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

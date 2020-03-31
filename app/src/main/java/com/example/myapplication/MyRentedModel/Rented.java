
package com.example.myapplication.MyRentedModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rented {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("Ad_id")
    @Expose
    private String adId;
    @SerializedName("Rental_Data")
    @Expose
    private String rentalData;
    @SerializedName("Rental_amount")
    @Expose
    private String rentalAmount;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getRentalData() {
        return rentalData;
    }

    public void setRentalData(String rentalData) {
        this.rentalData = rentalData;
    }

    public String getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(String rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}

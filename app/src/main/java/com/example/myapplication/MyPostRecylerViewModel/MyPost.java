
package com.example.myapplication.MyPostRecylerViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPost {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("post_title")
    @Expose
    private String postTitle;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("authority_types")
    @Expose
    private String authorityTypes;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("apartment_no")
    @Expose
    private String apartmentNo;
    @SerializedName("rent_date")
    @Expose
    private String rentDate;
    @SerializedName("tenant")
    @Expose
    private String tenant;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("space_size")
    @Expose
    private String spaceSize;
    @SerializedName("renters")
    @Expose
    private String renters;
    @SerializedName("utility")
    @Expose
    private String utility;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("floor")
    @Expose
    private String floor;
    @SerializedName("bedroom")
    @Expose
    private String bedroom;
    @SerializedName("bathroom")
    @Expose
    private String bathroom;
    @SerializedName("balconi")
    @Expose
    private String balconi;
    @SerializedName("kitchen")
    @Expose
    private String kitchen;
    @SerializedName("dining_room")
    @Expose
    private String diningRoom;
    @SerializedName("drawing_room")
    @Expose
    private String drawingRoom;
    @SerializedName("garage")
    @Expose
    private String garage;
    @SerializedName("closing_time")
    @Expose
    private String closingTime;
    @SerializedName("opening_time")
    @Expose
    private String openingTime;
    @SerializedName("nearest_famous_place_one")
    @Expose
    private String nearestFamousPlaceOne;
    @SerializedName("nearest_famous_place_two")
    @Expose
    private String nearestFamousPlaceTwo;
    @SerializedName("featured_image")
    @Expose
    private String featuredImage;
    @SerializedName("more_image")
    @Expose
    private String moreImage;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAuthorityTypes() {
        return authorityTypes;
    }

    public void setAuthorityTypes(String authorityTypes) {
        this.authorityTypes = authorityTypes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpaceSize() {
        return spaceSize;
    }

    public void setSpaceSize(String spaceSize) {
        this.spaceSize = spaceSize;
    }

    public String getRenters() {
        return renters;
    }

    public void setRenters(String renters) {
        this.renters = renters;
    }

    public String getUtility() {
        return utility;
    }

    public void setUtility(String utility) {
        this.utility = utility;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getBalconi() {
        return balconi;
    }

    public void setBalconi(String balconi) {
        this.balconi = balconi;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getDiningRoom() {
        return diningRoom;
    }

    public void setDiningRoom(String diningRoom) {
        this.diningRoom = diningRoom;
    }

    public String getDrawingRoom() {
        return drawingRoom;
    }

    public void setDrawingRoom(String drawingRoom) {
        this.drawingRoom = drawingRoom;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getNearestFamousPlaceOne() {
        return nearestFamousPlaceOne;
    }

    public void setNearestFamousPlaceOne(String nearestFamousPlaceOne) {
        this.nearestFamousPlaceOne = nearestFamousPlaceOne;
    }

    public String getNearestFamousPlaceTwo() {
        return nearestFamousPlaceTwo;
    }

    public void setNearestFamousPlaceTwo(String nearestFamousPlaceTwo) {
        this.nearestFamousPlaceTwo = nearestFamousPlaceTwo;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getMoreImage() {
        return moreImage;
    }

    public void setMoreImage(String moreImage) {
        this.moreImage = moreImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}

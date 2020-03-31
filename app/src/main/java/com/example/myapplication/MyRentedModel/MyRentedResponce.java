
package com.example.myapplication.MyRentedModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyRentedResponce {

    @SerializedName("Rented")
    @Expose
    private List<Rented> rented = null;

    public List<Rented> getRented() {
        return rented;
    }

    public void setRented(List<Rented> rented) {
        this.rented = rented;
    }

}

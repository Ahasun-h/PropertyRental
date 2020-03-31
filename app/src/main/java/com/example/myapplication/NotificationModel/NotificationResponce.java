
package com.example.myapplication.NotificationModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationResponce {

    @SerializedName("Notification")
    @Expose
    private List<Notification> notification = null;

    public List<Notification> getNotification() {
        return notification;
    }

    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }

}

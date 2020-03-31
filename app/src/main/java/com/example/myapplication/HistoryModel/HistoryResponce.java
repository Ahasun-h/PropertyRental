
package com.example.myapplication.HistoryModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryResponce {

    @SerializedName("History")
    @Expose
    private List<History> history = null;

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

}

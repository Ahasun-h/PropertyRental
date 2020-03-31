
package com.example.myapplication.CategoryRecyclerViewModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryRecyclerViewResponse {

    @SerializedName("CategoryActivePosts")
    @Expose
    private List<CategoryActivePost> categoryActivePosts = null;

    public List<CategoryActivePost> getCategoryActivePosts() {
        return categoryActivePosts;
    }

    public void setCategoryActivePosts(List<CategoryActivePost> categoryActivePosts) {
        this.categoryActivePosts = categoryActivePosts;
    }

}


package com.example.saurabh.restaurantdairy.apimodel.menujson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MenuData implements Serializable
{

    @SerializedName("menu_items")
    @Expose
    private List<MenuItem> menuItems = null;
    @SerializedName("category")
    @Expose
    private Category category;

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

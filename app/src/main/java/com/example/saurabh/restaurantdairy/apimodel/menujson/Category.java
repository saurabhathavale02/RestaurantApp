
package com.example.saurabh.restaurantdairy.apimodel.menujson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable
{

    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("special_instructions")
    @Expose
    private String specialInstructions;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

}

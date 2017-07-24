
package com.example.saurabh.restaurantdairy.apimodel.menujson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MenuItem implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price_small")
    @Expose
    private Object priceSmall;
    @SerializedName("price_large")
    @Expose
    private Float priceLarge;
    @SerializedName("small_portion_name")
    @Expose
    private Object smallPortionName;
    @SerializedName("large_portion_name")
    @Expose
    private Object largePortionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getPriceSmall() {
        return priceSmall;
    }

    public void setPriceSmall(Object priceSmall) {
        this.priceSmall = priceSmall;
    }

    public Float getPriceLarge() {
        return priceLarge;
    }

    public void setPriceLarge(Float priceLarge) {
        this.priceLarge = priceLarge;
    }

    public Object getSmallPortionName() {
        return smallPortionName;
    }

    public void setSmallPortionName(Object smallPortionName) {
        this.smallPortionName = smallPortionName;
    }

    public Object getLargePortionName() {
        return largePortionName;
    }

    public void setLargePortionName(Object largePortionName) {
        this.largePortionName = largePortionName;
    }

}

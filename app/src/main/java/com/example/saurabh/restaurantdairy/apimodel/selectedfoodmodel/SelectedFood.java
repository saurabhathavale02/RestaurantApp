package com.example.saurabh.restaurantdairy.apimodel.selectedfoodmodel;

/**
 * Created by saura on 7/15/2017.
 */

public class SelectedFood
{

    String selected_food_desc;
    String selected_food;
    Float Price;
    Integer Quantity;
    Integer id;

    public SelectedFood(Integer id, String selected_food, Float price, Integer quantity,String selected_food_desc)
    {
        this.id=id;
        this.selected_food = selected_food;
        this.Price = price;
        this.Quantity = quantity;
        this.selected_food_desc=selected_food_desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelected_food() {
        return selected_food;
    }

    public void setSelected_food(String selected_food) {
        this.selected_food = selected_food;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public String getSelected_food_desc() {
        return selected_food_desc;
    }

    public void setSelected_food_desc(String selected_food_desc) {
        this.selected_food_desc = selected_food_desc;
    }




}

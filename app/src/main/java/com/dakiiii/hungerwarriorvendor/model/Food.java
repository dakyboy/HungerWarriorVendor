package com.dakiiii.hungerwarriorvendor.model;

public class Food {
    private String foodName;
    private String foodDescription;
    private int foodPrice;
    private String foodVendor;
    private String foodImageUrl;

    public Food(String foodName, int foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodVendor() {
        return foodVendor;
    }

    public void setFoodVendor(String foodVendor) {
        this.foodVendor = foodVendor;
    }

    public String getFoodImageUrl() {
        return foodImageUrl;
    }

    public void setFoodImageUrl(String foodImageUrl) {
        this.foodImageUrl = foodImageUrl;
    }
}

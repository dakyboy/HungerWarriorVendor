package com.dakiiii.hungerwarriorvendor.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class Food {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "food_id")
    private int foodId;

    @ColumnInfo(name = "food_name")
    private String foodName;

    @ColumnInfo(name = "food_desc")
    private String foodDescription;

    @ColumnInfo(name = "food_price")
    private int foodPrice;

    @ColumnInfo(name = "food_vendor")
    private String foodVendor;

    @ColumnInfo(name = "food_pic_url")
    private String foodImageUrl;

    @Ignore
    public Food(String foodName, int foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
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

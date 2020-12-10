package com.dakiiii.hungerwarriorvendor.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orderItems_table")
public class OrderItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int orderId;

    private String status;

    private String foodName;

    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

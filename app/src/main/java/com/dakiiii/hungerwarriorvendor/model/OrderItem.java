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


}

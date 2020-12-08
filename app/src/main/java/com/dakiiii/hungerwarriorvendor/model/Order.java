package com.dakiiii.hungerwarriorvendor.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders_table")
public class Order {

    @PrimaryKey
    private int id;

    private int order_id;

    private int total;

    private String status;

    private String customerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}

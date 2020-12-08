package com.dakiiii.hungerwarriorvendor.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Order {

    @PrimaryKey
    private int id;

    private int total;

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
}

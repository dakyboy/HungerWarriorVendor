package com.dakiiii.hungerwarriorvendor.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.dakiiii.hungerwarriorvendor.model.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `order`")
    LiveData<List<Order>> getOrders();
}

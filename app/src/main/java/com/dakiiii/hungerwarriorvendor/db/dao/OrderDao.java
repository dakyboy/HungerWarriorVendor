package com.dakiiii.hungerwarriorvendor.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dakiiii.hungerwarriorvendor.model.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `orders_table`")
    LiveData<List<Order>> getOrders();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Order order);
}

package com.dakiiii.hungerwarriorvendor.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dakiiii.hungerwarriorvendor.model.OrderItem;

import java.util.List;

@Dao
public interface OrderItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderItem orderItem);

    @Query("SELECT * FROM orderItems_table")
    LiveData<List<OrderItem>> getAllOrderItems();

    @Query("SELECT * FROM orderItems_table WHERE orderId = :orderId")
    LiveData<List<OrderItem>> getOrderItemByOrderId(int orderId);

    @Query("SELECT status FROM ORDERITEMS_TABLE WHERE orderId = :orderItemId")
    LiveData<String> getOrderItemStatus(int orderItemId);

    @Query("DELETE FROM orderItems_table")
    void deleteAll();

    @Query("UPDATE orderItems_table SET status = :status WHERE id = :orderItemId AND orderId = :orderId")
    void updateStatus(String status, int orderItemId, int orderId);


}

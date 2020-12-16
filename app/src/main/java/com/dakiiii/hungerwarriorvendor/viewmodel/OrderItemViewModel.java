package com.dakiiii.hungerwarriorvendor.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dakiiii.hungerwarriorvendor.model.OrderItem;
import com.dakiiii.hungerwarriorvendor.repository.OrderRepo;

import java.util.List;

public class OrderItemViewModel extends AndroidViewModel {
    OrderRepo eOrderRepo;
    LiveData<List<OrderItem>> eListLiveData;

    public OrderItemViewModel(@NonNull Application application) {
        super(application);
        eOrderRepo = new OrderRepo(application);
        eListLiveData = eOrderRepo.getOrderItemsLiveData();
    }

    public LiveData<List<OrderItem>> getOrderItemsById(int orderId) {
        return eOrderRepo.getOrderItemByOrderId(orderId);
    }

    public void setOrderItemStatus(int orderItemId, String status) {
        eOrderRepo.setOrderItemStatus(orderItemId, status);

    }

    public LiveData<String> getOrderItemStatus(int orderItemId) {

        return eOrderRepo.getOrderItemStatus(orderItemId);
    }

    public LiveData<List<OrderItem>> getListLiveData() {
        return eListLiveData;
    }
}

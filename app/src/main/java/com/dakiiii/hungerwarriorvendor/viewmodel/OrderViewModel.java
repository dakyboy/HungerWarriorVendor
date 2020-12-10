package com.dakiiii.hungerwarriorvendor.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.repository.OrderRepo;

import java.util.Calendar;
import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private final OrderRepo eOrderRepo;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        eOrderRepo = new OrderRepo(application);
    }


    public LiveData<List<Order>> getOrders() {
        return eOrderRepo.getOrderss();
    }
}

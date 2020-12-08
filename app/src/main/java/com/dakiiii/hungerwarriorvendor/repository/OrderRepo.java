package com.dakiiii.hungerwarriorvendor.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dakiiii.hungerwarriorvendor.db.FoodRoomDatabase;
import com.dakiiii.hungerwarriorvendor.db.dao.OrderDao;
import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.networking.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

public class OrderRepo {

    private final OrderDao eOrderDao;
    private final VolleySingleton eVolleySingleton;
    List<Order> eOrders = new ArrayList<>();
    LiveData<List<Order>> eLiveDataOrders = new MutableLiveData<>();

    public OrderRepo(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eOrderDao = foodRoomDatabase.eOrderDao();
        eLiveDataOrders = eOrderDao.getOrders();
        eVolleySingleton = VolleySingleton.getInstance(application);
    }

    public List<Order> getOrders() {
        return eOrders;
    }

    public LiveData<List<Order>> getOrderss() {
        return eLiveDataOrders;
    }

//    Async Classes
}

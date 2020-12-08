package com.dakiiii.hungerwarriorvendor.repository;

import android.app.Application;

import com.dakiiii.hungerwarriorvendor.db.FoodRoomDatabase;
import com.dakiiii.hungerwarriorvendor.db.dao.OrderDao;
import com.dakiiii.hungerwarriorvendor.networking.VolleySingleton;

public class OrderRepo {

    private final OrderDao eOrderDao;
    private final VolleySingleton eVolleySingleton;

    public OrderRepo(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eOrderDao = foodRoomDatabase.eOrderDao();

        eVolleySingleton = VolleySingleton.getInstance(application);
    }

//    Async Classes
}

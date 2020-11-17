package com.dakiiii.hungerwarriorvendor;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dakiiii.hungerwarriorvendor.db.dao.FoodDao;
import com.dakiiii.hungerwarriorvendor.db.dao.FoodRoomDatabase;
import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.List;

public class FoodRepository {
    private FoodDao eFoodDao;
    private LiveData<List<Food>> eAllFoods;

    public FoodRepository(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eFoodDao = foodRoomDatabase.eFoodDao();
        eAllFoods = eFoodDao.getFoods();
    }

    public LiveData<List<Food>> getAllFoods() {
        return eAllFoods;
    }

    public void insert(Food food) {
    FoodRoomDatabase.databaseWriteEXECUTOR_SERVICE.execute(() -> {
        eFoodDao.insert(food);
    });
    }
}

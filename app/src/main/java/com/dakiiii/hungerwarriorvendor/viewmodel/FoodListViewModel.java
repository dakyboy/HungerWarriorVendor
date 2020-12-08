package com.dakiiii.hungerwarriorvendor.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.repository.FoodRepository;

import java.util.List;

public class FoodListViewModel extends AndroidViewModel {

    FoodRepository eFoodRepository;
    LiveData<List<Food>> eLiveDataFoods;

    public FoodListViewModel(@NonNull Application application) {
        super(application);
        eFoodRepository = new FoodRepository(application);
        eLiveDataFoods = eFoodRepository.getAllFoods();
    }

    public LiveData<List<Food>> getLiveDataFoods() {
        return eLiveDataFoods;
    }

    public void deleteFood(Food food) {
        eFoodRepository.deleteFood(food);
    }
}

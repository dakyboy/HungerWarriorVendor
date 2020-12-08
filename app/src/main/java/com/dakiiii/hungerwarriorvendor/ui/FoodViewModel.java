package com.dakiiii.hungerwarriorvendor.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.repository.FoodRepository;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {

    private final FoodRepository eFoodRepository;
    private final LiveData<List<Food>> eFoodsListLiveData;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        eFoodRepository = new FoodRepository(application);

        eFoodsListLiveData = eFoodRepository.getAllFoods();


    }

    public LiveData<List<Food>> getFoodsListLiveData() {
        return eFoodsListLiveData;
    }

    public void insert(Food food) {
        eFoodRepository.insert(food);
    }
}

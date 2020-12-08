package com.dakiiii.hungerwarriorvendor.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.repository.FoodRepository;

public class FoodViewModel extends AndroidViewModel {

    private final FoodRepository eFoodRepository;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        eFoodRepository = new FoodRepository(application);
    }

    public void saveFoodToServer(Food food) {
        eFoodRepository.saveFoodToServer(food);
    }
}

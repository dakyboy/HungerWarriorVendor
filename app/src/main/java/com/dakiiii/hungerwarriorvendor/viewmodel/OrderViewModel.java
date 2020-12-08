package com.dakiiii.hungerwarriorvendor.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.dakiiii.hungerwarriorvendor.repository.OrderRepo;

public class OrderViewModel extends AndroidViewModel {

    private final OrderRepo eOrderRepo;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        eOrderRepo = new OrderRepo(application);
    }
}

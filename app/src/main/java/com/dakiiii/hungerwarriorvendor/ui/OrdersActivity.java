package com.dakiiii.hungerwarriorvendor.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.viewmodel.OrderViewModel;

public class OrdersActivity extends AppCompatActivity {

    OrderViewModel eOrderViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        eOrderViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(OrderViewModel.class);
    }
}
package com.dakiiii.hungerwarriorvendor.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.adapter.OrdersAdapter;
import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.viewmodel.OrderViewModel;

import java.util.List;

public class OrdersActivity extends AppCompatActivity {


    private OrderViewModel eOrderViewModel;
    private OrdersAdapter eOrdersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        RecyclerView recyclerViewOrders = findViewById(R.id.recyclerview_orders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(this));

        eOrdersAdapter = new OrdersAdapter();
        recyclerViewOrders.setAdapter(eOrdersAdapter);

        eOrderViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(OrderViewModel.class);

//        get orders for vendor
        eOrderViewModel.getOrderss().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                eOrdersAdapter.setOrders(orders);
            }
        });
//        display dem on list

//        update status
    }
}
package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Context;
import android.net.ConnectivityManager;
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
    private ConnectivityManager eConnectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_orders);
        eOrdersAdapter = new OrdersAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eOrdersAdapter);
        eConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        eOrderViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(OrderViewModel.class);

        eOrderViewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                eOrdersAdapter.setOrders(orders);
            }
        });
//        display dem on list

//        update status
    }


    @Override
    protected void onStart() {
        super.onStart();
        eOrderViewModel.refreshOrders();
    }
}
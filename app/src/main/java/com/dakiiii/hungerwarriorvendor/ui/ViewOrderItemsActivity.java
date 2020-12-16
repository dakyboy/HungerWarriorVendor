package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.adapter.OrderItemsAdapter;
import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.model.OrderItem;
import com.dakiiii.hungerwarriorvendor.viewmodel.OrderItemViewModel;
import com.google.gson.Gson;

import java.util.List;

public class ViewOrderItemsActivity extends AppCompatActivity {

    public static final String ORDER_EXTRA = "com.dakiiii.hungerwarriorvendor.EXTRA.order";
    OrderItemsAdapter eOrderItemsAdapter;
    OrderItemViewModel eOrderItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_items2);

        Intent intent = getIntent();
        String orderGson = intent.getStringExtra(ORDER_EXTRA);
        Order order = new Gson().fromJson(orderGson, Order.class);
        TextView textViewOrderId = findViewById(R.id.textView_OI_orderId);
        textViewOrderId.setText(String.valueOf(order.getOrderId()));

        RecyclerView recyclerView = findViewById(R.id.recyclerview_oi_order_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        eOrderItemViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication()).create(OrderItemViewModel.class);

        eOrderItemsAdapter = new OrderItemsAdapter(eOrderItemViewModel, this);
        recyclerView.setAdapter(eOrderItemsAdapter);

        eOrderItemViewModel.getOrderItemsById(order.getOrderId())
                .observe(this, new Observer<List<OrderItem>>() {
                    @Override
                    public void onChanged(List<OrderItem> orderItems) {
                        eOrderItemsAdapter.setOrderItems(orderItems);
                    }
                });


    }
}
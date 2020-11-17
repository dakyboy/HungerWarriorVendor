package com.dakiiii.hungerwarriorvendor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dakiiii.hungerwarriorvendor.FoodsActivity;
import com.dakiiii.hungerwarriorvendor.OrdersActivity;
import com.dakiiii.hungerwarriorvendor.R;

public class MainActivity extends AppCompatActivity {

    public Intent foodsIntent;
    public Intent ordersIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodsIntent = new Intent(this, FoodsActivity.class);
        ordersIntent = new Intent(this, OrdersActivity.class);
    }

    public void openFoods(View view) {
        startActivity(foodsIntent);

    }

    public void openOrders(View view) {
        startActivity(ordersIntent);
    }
}
package com.dakiiii.hungerwarriorvendor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dakiiii.hungerwarriorvendor.R;

public class MainActivity extends AppCompatActivity {

    public Intent foodsIntent;
    public Intent ordersIntent;
    public FoodsListFragment eFoodsListFragment;
    private FragmentManager eFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodsIntent = new Intent(this, FoodsActivity.class);
        ordersIntent = new Intent(this, OrdersActivity.class);
        eFoodsListFragment = new FoodsListFragment();
        eFragmentManager = getSupportFragmentManager();


    }




    public void openOrders(View view) {
//        startActivity(ordersIntent);
        FragmentTransaction transaction = eFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_main, eFoodsListFragment)
                .commit();
    }



    public void openFoods(View view) {
        startActivity(foodsIntent);
    }
}
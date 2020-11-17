package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.dakiiii.hungerwarriorvendor.FoodsActivity;
import com.dakiiii.hungerwarriorvendor.OrdersActivity;
import com.dakiiii.hungerwarriorvendor.R;

public class MainActivity extends AppCompatActivity {

    public Intent foodsIntent;
    public Intent ordersIntent;
    public Fragment foodsListFragment;
    private FragmentManager eFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodsIntent = new Intent(this, FoodsActivity.class);
        ordersIntent = new Intent(this, OrdersActivity.class);
        foodsListFragment = new FoodsListFragment();
        eFragmentManager = getSupportFragmentManager();

    }


    public void setFoodsFrag() {
        Toast.makeText(this, "show fof", Toast.LENGTH_SHORT).show();

        /*FragmentTransaction fragmentTransaction = eFragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_main, foodsListFragment)
        .commit();*/
    }

    public void openOrders(View view) {
        startActivity(ordersIntent);
    }
}
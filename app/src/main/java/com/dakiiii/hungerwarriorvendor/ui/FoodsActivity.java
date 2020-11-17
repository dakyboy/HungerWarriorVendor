package com.dakiiii.hungerwarriorvendor.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.ui.AddFoodActivity;

public class FoodsActivity extends AppCompatActivity {

    public Intent addFoodIntent;
    private FragmentManager eFragmentManager;
    private FoodsListFragment eFoodsListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        addFoodIntent = new Intent(this, AddFoodActivity.class);
        eFragmentManager = getSupportFragmentManager();

        eFoodsListFragment = new FoodsListFragment();
        FragmentTransaction transaction = eFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container_foods, eFoodsListFragment)
        .commit();

    }

    public void openAddFood(View view) {
        startActivity(addFoodIntent);
    }


}
package com.dakiiii.hungerwarriorvendor.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.ui.AddFoodActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodsActivity extends AppCompatActivity {

    public Intent addFoodIntent;
    private FragmentManager eFragmentManager;
    private FoodsListFragment eFoodsListFragment;
    private int NEW_FOOD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);


        addFoodIntent = new Intent(this, AddFoodActivity.class);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButtonAddFood);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(addFoodIntent, NEW_FOOD_ACTIVITY_REQUEST_CODE);
            }
        });
        eFragmentManager = getSupportFragmentManager();

        eFoodsListFragment = new FoodsListFragment();
        FragmentTransaction transaction = eFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container_foods, eFoodsListFragment)
                .commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_FOOD_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Food food = new Food(data.getStringExtra(AddFoodActivity.EXTRA_REPLY));
                

            }
        }
    }

    public void openAddFood(View view) {
        startActivity(addFoodIntent);
    }


}
package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.viewmodel.FoodViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class FoodsActivity extends AppCompatActivity {

    public Intent addFoodIntent;
    private FragmentManager eFragmentManager;
    private FoodsListFragment eFoodsListFragment;
    private final int NEW_FOOD_ACTIVITY_REQUEST_CODE = 1;
    FoodViewModel eFoodViewModel;

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
        eFoodViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(FoodViewModel.class);

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
                String new_food = data.getStringExtra(AddFoodActivity.NEW_FOOD_EXTRA);
                Food food = new Gson().fromJson(new_food, Food.class);
                eFoodViewModel.saveFoodToServer(food);

                Toast.makeText(this, "Food saved", Toast.LENGTH_SHORT).show();


            }
        }
    }

    public void openAddFood(View view) {
        startActivity(addFoodIntent);
    }


}
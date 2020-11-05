package com.dakiiii.hungerwarriorvendor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FoodsActivity extends AppCompatActivity {

    public Intent addFoodIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        addFoodIntent = new Intent(this, AddFoodActivity.class);
    }

    public void openAddFood(View view) {
        startActivity(addFoodIntent);
    }


}
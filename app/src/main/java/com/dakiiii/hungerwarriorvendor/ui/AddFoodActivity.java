package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Food;

public class AddFoodActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.dakiiii.hungerwarriorvendor.ui.food_uid_.REPLY";

    private ImageView foodPicImageView;
    private EditText foodNameEditText;
    private EditText foodDescriptionEditText;
    private EditText foodPriceEditText;

    private Food eFood;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodNameEditText = findViewById(R.id.editTextFoodName);
        foodDescriptionEditText = findViewById(R.id.editTextFoodDescription);
        foodPriceEditText = findViewById(R.id.editTextFoodPrice);
        foodPicImageView = findViewById(R.id.imageViewFoodPic);

        Glide.with(this)
                .load("http://via.placeholder.com/300.png")
                .placeholder(R.drawable.com_facebook_profile_picture_blank_portrait)
                .into(foodPicImageView);
    }

    public void addFood(View view) {
        getFoodDetail();
    }


    private void getFoodDetail() {
        String foodName;
        String foodDesc;
        Food food = null;
        Intent replyIntent = new Intent();
        int foodPrice;
        if (TextUtils.isEmpty(foodNameEditText.getText()) || TextUtils.isEmpty(foodPriceEditText.getText())) {
            setResult(RESULT_CANCELED, replyIntent);


        } else {
            foodName = foodNameEditText.getText().toString().trim();
            foodDesc = foodDescriptionEditText.getText().toString().trim();
            foodPrice = Integer.parseInt(foodPriceEditText.getText().toString().trim());
            food = new Food(foodName, foodPrice);

            Bundle foodBundleExtras = new Bundle();
            foodBundleExtras.putString("FOOD_NAME", food.getFoodName());
            foodBundleExtras.putInt("FOOD_PRICE ", food.getFoodPrice());
            replyIntent.putExtra(EXTRA_REPLY, foodBundleExtras);
            setResult(RESULT_OK, replyIntent);


        }

        finish();

    }

    public void uploadPic(View view) {
    }

}
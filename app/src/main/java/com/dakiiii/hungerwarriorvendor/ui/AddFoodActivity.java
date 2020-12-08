package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.viewmodel.FoodViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddFoodActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.dakiiii.hungerwarriorvendor.ui.food_uid_.REPLY";
    public static final String KEY_FOOD_NAME = "food_name";
    public static final String KEY_FOOD_DESC = "food_desc";
    public static final String KEY_FOOD_PRICE = "food_price";
    public static final String KEY_FOOD_VENDOR = "food_vendor";


    private ImageView foodPicImageView;
    private EditText foodNameEditText;
    private EditText foodDescriptionEditText;
    private EditText foodPriceEditText;
    private FirebaseUser eFirebaseUser;
    private FoodViewModel eFoodViewModel;
    private Button eButtonAddFood;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        eButtonAddFood = findViewById(R.id.buttonAddFood);
        foodNameEditText = findViewById(R.id.editTextFoodName);
        foodDescriptionEditText = findViewById(R.id.editTextFoodDescription);
        foodPriceEditText = findViewById(R.id.editTextFoodPrice);
        foodPicImageView = findViewById(R.id.imageViewFoodPic);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        eFirebaseUser = firebaseAuth.getCurrentUser();

        eFoodViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(FoodViewModel.class);

        Glide.with(this)
                .load("http://via.placeholder.com/300.png")
                .placeholder(R.drawable.ic_baseline_fastfood_24)
                .into(foodPicImageView);
    }

    public void addFood(View view) {
        eButtonAddFood.setEnabled(false);
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
            Toast.makeText(getApplicationContext()
                    , "Please fill the fields", Toast.LENGTH_SHORT).show();


        } else {
            foodName = foodNameEditText.getText().toString().trim();
            foodDesc = foodDescriptionEditText.getText().toString().trim();
            foodPrice = Integer.parseInt(foodPriceEditText.getText().toString().trim());

            food = new Food(foodName, foodPrice);
            food.setFoodDescription(foodDesc);
            food.setFoodVendor(eFirebaseUser.getDisplayName());

            eFoodViewModel.saveFoodToServer(food);
            setResult(RESULT_OK, replyIntent);


        }

        finish();

    }

    public void uploadPic(View view) {
    }

}
package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.networking.VolleySingleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

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
    private FirebaseAuth eFirebaseAuth;
    private FirebaseUser eFirebaseUser;

    private Food eFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodNameEditText = findViewById(R.id.editTextFoodName);
        foodDescriptionEditText = findViewById(R.id.editTextFoodDescription);
        foodPriceEditText = findViewById(R.id.editTextFoodPrice);
        foodPicImageView = findViewById(R.id.imageViewFoodPic);
        eFirebaseAuth = FirebaseAuth.getInstance();
        eFirebaseUser = eFirebaseAuth.getCurrentUser();

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
            food.setFoodDescription(foodDesc);
            food.setFoodVendor(eFirebaseUser.getDisplayName());

            saveFood(this, food);
            setResult(RESULT_OK, replyIntent);


        }

        finish();

    }

    private void saveFood(Context context, Food food) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, FoodsListFragment.foodsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "no success", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_FOOD_NAME, food.getFoodName());
                params.put(KEY_FOOD_DESC, food.getFoodDescription());
                params.put(KEY_FOOD_PRICE, Integer.toString(food.getFoodPrice()));
                params.put(KEY_FOOD_VENDOR, food.getFoodVendor());
                return params;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void uploadPic(View view) {
    }

}
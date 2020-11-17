package com.dakiiii.hungerwarriorvendor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.HashMap;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity {

    private ImageView foodPicImageView;
    private EditText foodNameEditText;
    private EditText foodDescriptionEditText;
    private EditText foodPriceEditText;

    private Food eFood;
    private String foodsUrl = "http://hungerwarrior.herokuapp.com/api/foods";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodNameEditText = findViewById(R.id.editTextFoodName);
        foodDescriptionEditText = findViewById(R.id.editTextFoodDescription);
        foodPriceEditText = findViewById(R.id.editTextFoodPrice);
        foodPicImageView = findViewById(R.id.imageViewFoodPic);
    }

    public void addFood(View view) {
        Food food = getFoodDetail();

        StringRequest addFoodStringRequest = new StringRequest(Request.Method.POST
                , foodsUrl
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("food_name", food.getFoodName());
                params.put("food_desc", food.getFoodDescription());
                params.put("food_price", Integer.toString(food.getFoodPrice()));
                return params;
            }
        };

        VolleySingleton.getInstance(AddFoodActivity.this).addToRequestQueue(addFoodStringRequest);

    }

    private Food getFoodDetail() {
        String foodName = foodNameEditText.getText().toString();
        String foodDesc = foodDescriptionEditText.getText().toString();
        int foodPrice = Integer.parseInt(foodPriceEditText.getText().toString());

        if (foodName != null) {
            eFood = new Food(foodName);
            eFood.setFoodPrice(foodPrice);
            eFood.setFoodDescription(foodDesc);
        }

        return eFood;
    }

    public void uploadPic(View view) {
    }
}
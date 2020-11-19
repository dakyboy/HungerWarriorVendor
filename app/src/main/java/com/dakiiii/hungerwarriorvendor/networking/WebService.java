package com.dakiiii.hungerwarriorvendor.networking;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.List;

public class WebService {
    private final String API_FOODS_URL = "http://hungerwarrior.herokuapp.com/api/foods";
    public List<Food> eFoods;

    public List<Food> getFoaaods() {

        StringRequest addFoodStringRequest = new StringRequest(Request.Method.POST
                , API_FOODS_URL
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response == null) {

                    return;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FAIL", error.toString());
            }
        }) {

            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("food_name", food.getFoodName());
                params.put("food_desc", food.getFoodDescription());
                params.put("food_price", Integer.toString(food.getFoodPrice()));
                return params;
            }
        };

        VolleySingleton.getInstance(AddFoodActivity.this).addToRequestQueue(addFoodStringRequest);*/
        };
        return eFoods;
    }

    public void setFoods(List<Food> foods) {
        eFoods = foods;
    }
}

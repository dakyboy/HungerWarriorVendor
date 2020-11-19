package com.dakiiii.hungerwarriorvendor;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dakiiii.hungerwarriorvendor.db.dao.FoodDao;
import com.dakiiii.hungerwarriorvendor.db.dao.FoodRoomDatabase;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.networking.VolleySingleton;
import com.dakiiii.hungerwarriorvendor.networking.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository {
    private FoodDao eFoodDao;
    private LiveData<List<Food>> eAllFoods;
    private final String API_FOODS_URL = "http://hungerwarrior.herokuapp.com/api/foods";
    private WebService eWebService;
    private List<Food> eFoods = new ArrayList<>();

    public FoodRepository(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eFoodDao = foodRoomDatabase.eFoodDao();
//        eFoods = getFoodsFromServer(application.getApplicationContext());
//        eAllFoods = eFoodDao.getFoods();
    }


    public LiveData<List<Food>> getAllFoods() {
        return eAllFoods;
    }

    public void insert(Food food) {
        FoodRoomDatabase.databaseWriteEXECUTOR_SERVICE.execute(() -> {
            eFoodDao.insert(food);
        });
    }

    public LiveData<List<Food>> getAllFoodsFromServer() {


        return eAllFoods;
    }

    public void insertApi(Food food) {
    }

    public List<Food> getFoodsFromServer(Context context) {
        List<Food> foods = new ArrayList<>();
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_FOODS_URL
                , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                eFoods.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject foodJsonObject = response.getJSONObject(i);

                        int foodId = foodJsonObject.getInt("id");
                        int food_price = foodJsonObject.getInt("food_price");
                        String food_name = foodJsonObject.getString("food_name");
                        String food_desc = foodJsonObject.getString("food_desc");

                        Food food = new Food(food_name, food_price);
                        food.setFoodId(foodId);
                        food.setFoodDescription(food_desc);

                        foods.add(food);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                        progressDialog.dismiss();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

        return foods;
    }
}

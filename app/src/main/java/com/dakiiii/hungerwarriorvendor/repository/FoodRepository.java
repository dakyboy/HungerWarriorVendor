package com.dakiiii.hungerwarriorvendor.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.dakiiii.hungerwarriorvendor.db.FoodRoomDatabase;
import com.dakiiii.hungerwarriorvendor.db.dao.FoodDao;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.networking.VolleySingleton;
import com.dakiiii.hungerwarriorvendor.ui.FoodsListFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodRepository {

    public static final String API_FOODS_URL = "https://hungerwarrior.herokuapp.com/api/foods";
    public static final String API_FOODS_BY_VENDOR_URL = API_FOODS_URL + "ByVendor/";
    public static final String KEY_FOOD_NAME = "food_name";
    public static final String KEY_FOOD_DESC = "food_desc";
    public static final String KEY_FOOD_PRICE = "food_price";
    public static final String KEY_FOOD_VENDOR = "food_vendor";
    private final FoodDao eFoodDao;
    private final LiveData<List<Food>> eAllFoods;
    private final VolleySingleton eVolleySingleton;
    private final LiveData<List<Food>> eLiveDataFoods = new MutableLiveData<>();
    private final FirebaseUser eFirebaseUser;

    public FoodRepository(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eFoodDao = foodRoomDatabase.eFoodDao();
        eFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        eVolleySingleton = VolleySingleton.getInstance(application.getApplicationContext());

        getFoodsFromServer();

        eAllFoods = eFoodDao.getFoods();

    }


    //    get live data food list from db
    public LiveData<List<Food>> getAllFoods() {
        return eAllFoods;
    }

    //    save food to server
    public void saveFoodOnWebServer(Food food) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, FoodsListFragment.foodsUrl, new Response.Listener<String>() {
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
                Map<String, String> params = new HashMap<>();
                params.put(KEY_FOOD_NAME, food.getFoodName());
                params.put(KEY_FOOD_DESC, food.getFoodDescription());
                params.put(KEY_FOOD_PRICE, Integer.toString(food.getFoodPrice()));
                params.put(KEY_FOOD_VENDOR, food.getFoodVendor());
                return params;
            }
        };

        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(0
                        , DefaultRetryPolicy.DEFAULT_MAX_RETRIES
                        , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        eVolleySingleton.addToRequestQueue(stringRequest);
    }

    //    Get foods from server

    public void getFoodsFromServer() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET
                , API_FOODS_BY_VENDOR_URL + eFirebaseUser.getDisplayName()
                , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

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

                        FoodRoomDatabase.databaseWriteEXECUTOR_SERVICE.execute(new Runnable() {
                            @Override
                            public void run() {
                                eFoodDao.insert(food);
                            }
                        });

                    } catch (JSONException jsonException) {
                        Log.e("get food repo error", jsonException.toString());
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("get food repo error", error.toString());
            }
        });


        eVolleySingleton.addToRequestQueue(jsonArrayRequest);

    }

    public void deleteFood(Food food) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, API_FOODS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    //    Async tasks

}

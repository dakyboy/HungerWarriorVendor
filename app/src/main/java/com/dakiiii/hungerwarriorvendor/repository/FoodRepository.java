package com.dakiiii.hungerwarriorvendor.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodRepository {
    public static final String API_FOODS_URL = "http://hungerwarrior.herokuapp.com/api/foods";
    private final FoodDao eFoodDao;
    private final LiveData<List<Food>> eAllFoods;
    private final VolleySingleton eVolleySingleton;
    private final List<Food> eFoods = new ArrayList<>();
    private final LiveData<List<Food>> eLiveDataFoods = new MutableLiveData<>();

    public FoodRepository(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eFoodDao = foodRoomDatabase.eFoodDao();

        eVolleySingleton = VolleySingleton.getInstance(application);
        getFoodsFromServer();

        eAllFoods = eFoodDao.getFoods();
    }


//    get live data food list from db
public LiveData<List<Food>> getAllFoods() {
    return eAllFoods;
}


    public void saveFoodToDb(Food food) {
        new saveFoodsToDbAsyncTask(eFoodDao, food).execute();
    }


    //    save food to server
    public void saveFoodToServer(Food food) {
        new saveFoodToServerAsyncTask(eFoodDao, eVolleySingleton, food).execute();
    }

    //    Get foods from server
    public void getFoodsFromServer() {
        new getFoodsFromServerAsyncTask(eFoodDao, eVolleySingleton).execute();
    }

    //    Async tasks

    //    Async task to save the foods from the server to the db
    private static class getFoodsFromServerAsyncTask extends AsyncTask<Void, Void, Void> {

        private final FoodDao eFoodDao;
        private final VolleySingleton eVolleySingleton;

        public getFoodsFromServerAsyncTask(FoodDao foodDao, VolleySingleton volleySingleton) {
            eFoodDao = foodDao;
            eVolleySingleton = volleySingleton;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getFoodsFromServer();
            return null;
        }

        private void getFoodsFromServer() {

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_FOODS_URL
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

                            new saveFoodsToDbAsyncTask(eFoodDao, food).execute();

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


    }


    //    Async task to save a food to the db
    public static class saveFoodsToDbAsyncTask extends AsyncTask<Void, Void, Void> {
        FoodDao eFoodDao;
        Food eFood;

        public saveFoodsToDbAsyncTask(FoodDao foodDao, Food food) {
            eFoodDao = foodDao;
            eFood = food;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            eFoodDao.insert(eFood);

            return null;
        }
    }

    //    Async task to save a food to the server
    public static class saveFoodToServerAsyncTask extends AsyncTask<Void, Void, Void> {
        public static final String KEY_FOOD_NAME = "food_name";
        public static final String KEY_FOOD_DESC = "food_desc";
        public static final String KEY_FOOD_PRICE = "food_price";
        public static final String KEY_FOOD_VENDOR = "food_vendor";
        FoodDao eFoodDao;
        Food eFood;
        VolleySingleton eVolleySingleton;

        public saveFoodToServerAsyncTask(FoodDao foodDao, VolleySingleton volleySingleton, Food food) {
            eFoodDao = foodDao;
            eFood = food;
            eVolleySingleton = volleySingleton;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            saveFood(eFood);

            return null;
        }

        private void saveFood(Food food) {
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

            eVolleySingleton.addToRequestQueue(stringRequest);
        }
    }
}

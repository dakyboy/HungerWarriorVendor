package com.dakiiii.hungerwarriorvendor.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dakiiii.hungerwarriorvendor.db.FoodRoomDatabase;
import com.dakiiii.hungerwarriorvendor.db.dao.OrderDao;
import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.networking.VolleySingleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class OrderRepo {

    private final OrderDao eOrderDao;
    private final VolleySingleton eVolleySingleton;
    List<Order> eOrders = new ArrayList<>();
    LiveData<List<Order>> eLiveDataOrders = new MutableLiveData<>();
    private final FirebaseUser eFirebaseUser;

    public OrderRepo(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eOrderDao = foodRoomDatabase.eOrderDao();
        eLiveDataOrders = eOrderDao.getOrders();
        eVolleySingleton = VolleySingleton.getInstance(application);
        eFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        new getVendorOrdersAsyncTask(eVolleySingleton, eFirebaseUser).execute();
    }

    public List<Order> getOrders() {
        return eOrders;
    }

    public LiveData<List<Order>> getOrderss() {
        return eLiveDataOrders;
    }

//    Async Classes

    private static class getVendorOrdersAsyncTask extends AsyncTask<Void, Void, Void> {
        public static String API_ORDERS_VENDOR = "https://hungerwarrior.herokuapp.com/api/orderItemsByVendor/";
        VolleySingleton eVolleySingleton;
        FirebaseUser eFirebaseUser;

        public getVendorOrdersAsyncTask(VolleySingleton volleySingleton, FirebaseUser user) {
            eVolleySingleton = volleySingleton;
            eFirebaseUser = user;
            API_ORDERS_VENDOR += user.getDisplayName();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getOrders();
            return null;
        }

        private void getOrders() {
            JsonArrayRequest jsonArrayRequestOrders = new JsonArrayRequest(Request.Method.GET
                    , API_ORDERS_VENDOR, null
                    , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("whosssss", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Volley error ewww: ", error.toString());
                }
            });

            eVolleySingleton.addToRequestQueue(jsonArrayRequestOrders);
        }
    }

   /* private static class eAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    } */
}

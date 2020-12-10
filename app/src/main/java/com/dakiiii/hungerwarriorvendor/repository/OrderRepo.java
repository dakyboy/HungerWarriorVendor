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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderRepo {

    private OrderDao eOrderDao;
    private VolleySingleton eVolleySingleton;
    List<Order> eOrders = new ArrayList<>();
    LiveData<List<Order>> eLiveDataOrders = new MutableLiveData<>();
    private FirebaseUser eFirebaseUser;
    FirebaseAuth eFirebaseAuth;

    public OrderRepo(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eOrderDao = foodRoomDatabase.eOrderDao();
        eLiveDataOrders = eOrderDao.getOrders();
        eFirebaseAuth = FirebaseAuth.getInstance();
        eVolleySingleton = VolleySingleton.getInstance(application);

        eFirebaseUser = eFirebaseAuth.getCurrentUser();


        new getVendorOrdersAsyncTask(eVolleySingleton, eFirebaseAuth, eOrderDao).execute();
    }

    public List<Order> getOrders() {
        return eOrders;
    }

    public LiveData<List<Order>> getOrderss() {
        return eLiveDataOrders;
    }

//    Async Classes

    private static class getVendorOrdersAsyncTask extends AsyncTask<Void, Void, Void> {
        public final static String API_ORDERS_VENDOR = "https://hungerwarrior.herokuapp.com/api/orderItemsByVendor/";
        VolleySingleton eVolleySingleton;
        FirebaseUser eFirebaseUser;
        FirebaseAuth eFirebaseAuth;
        OrderDao eOrderDao;

        public getVendorOrdersAsyncTask(VolleySingleton volleySingleton, FirebaseAuth auth, OrderDao orderDao) {
            eVolleySingleton = volleySingleton;
            eFirebaseAuth = auth;
            eFirebaseUser = auth.getCurrentUser();
            eOrderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getOrders();
            return null;
        }

        private void getOrders() {
            String vendorName = eFirebaseUser.getDisplayName();
            JsonArrayRequest jsonArrayRequestOrders = new JsonArrayRequest(Request.Method.GET
                    , API_ORDERS_VENDOR + vendorName, null
                    , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("whosssss", response.toString());

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject orderJsonObject = response.getJSONObject(i);
                            int order_id = orderJsonObject.getInt("order_id");
                            String customer_id = orderJsonObject.getString("customer_id");
                            String orderedOn = orderJsonObject.getString("created_at");

                            //create new order and save it to db
                            Order order = new Order(order_id);
                            order.setCustomerId(customer_id);
                            order.setOrderedOn(orderedOn);

                            FoodRoomDatabase.databaseWriteEXECUTOR_SERVICE.execute(new Runnable() {
                                @Override
                                public void run() {
                                    eOrderDao.insert(order);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
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

    private static class saveOrdersToDbAsyncTask extends AsyncTask<Void, Void, Void> {
        OrderDao eOrderDao;
        Order eOrder;

        public saveOrdersToDbAsyncTask(OrderDao orderDao, Order order) {
            eOrderDao = orderDao;
            eOrder = order;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eOrderDao.insert(eOrder);
            return null;
        }
    }


   /* private static class eAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    } */
}

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
import com.dakiiii.hungerwarriorvendor.db.dao.OrderItemDao;
import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.model.OrderItem;
import com.dakiiii.hungerwarriorvendor.networking.VolleySingleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class OrderRepo {

    private final OrderDao eOrderDao;
    private final OrderItemDao eOrderItemDao;
    private final VolleySingleton eVolleySingleton;
    LiveData<List<Order>> eLiveDataOrders = new MutableLiveData<>();
    private final FirebaseUser eFirebaseUser;
    FirebaseAuth eFirebaseAuth;
    public final static String API_ORDERS_VENDOR = "https://hungerwarrior.herokuapp.com/api/orderItemsByVendor/";

    public OrderRepo(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eOrderDao = foodRoomDatabase.eOrderDao();
        eOrderItemDao = foodRoomDatabase.eOrderItemDao();
        eLiveDataOrders = eOrderDao.getOrders();
        eFirebaseAuth = FirebaseAuth.getInstance();
        eVolleySingleton = VolleySingleton.getInstance(application);

        eFirebaseUser = eFirebaseAuth.getCurrentUser();


//        new getVendorOrdersAsyncTask(eVolleySingleton, eFirebaseAuth, eOrderDao, eOrderItemDao).execute();
    }


    public LiveData<List<Order>> getOrderss() {
        return eLiveDataOrders;
    }

    public LiveData<List<OrderItem>> getOrderItemByOrderId(int orderId) {
        return eOrderItemDao.getOrderItemByOrderId(orderId);
    }

    public void getOrders() {

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
//create order item
                        int orderItemId = orderJsonObject.getInt("id");
                        String foodName = orderJsonObject.getString("food_name");
                        String status = orderJsonObject.getString("status");
                        int quantity = orderJsonObject.getInt("quantity");
                        OrderItem orderItem = new OrderItem(order_id);
                        orderItem.setId(orderItemId);
                        orderItem.setFoodName(foodName);
                        orderItem.setQuantity(quantity);
                        orderItem.setStatus(status);

                        FoodRoomDatabase.databaseWriteEXECUTOR_SERVICE.execute(new Runnable() {
                            @Override
                            public void run() {
                                eOrderItemDao.insert(orderItem);
                            }
                        });

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


    public LiveData<String> getOrderItemStatus(int orderItemId) {
        return null;
    }

    public void setOrderItemStatus(int orderItemId) {

    }

//    Async Classes

    private static class getVendorOrdersAsyncTask extends AsyncTask<Void, Void, Void> {
        public final static String API_ORDERS_VENDOR = "https://hungerwarrior.herokuapp.com/api/orderItemsByVendor/";
        VolleySingleton eVolleySingleton;
        FirebaseUser eFirebaseUser;
        FirebaseAuth eFirebaseAuth;
        OrderDao eOrderDao;
        OrderItemDao eOrderItemDao;

        public getVendorOrdersAsyncTask(VolleySingleton volleySingleton, FirebaseAuth auth, OrderDao orderDao, OrderItemDao orderItemDao) {
            eVolleySingleton = volleySingleton;
            eFirebaseAuth = auth;
            eFirebaseUser = auth.getCurrentUser();
            eOrderDao = orderDao;
            eOrderItemDao = orderItemDao;
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
//create order item
                            int orderItemId = orderJsonObject.getInt("id");
                            String foodName = orderJsonObject.getString("food_name");
                            String status = orderJsonObject.getString("status");
                            int quantity = orderJsonObject.getInt("quantity");
                            OrderItem orderItem = new OrderItem(order_id);
                            orderItem.setId(orderItemId);
                            orderItem.setFoodName(foodName);
                            orderItem.setQuantity(quantity);
                            orderItem.setStatus(status);

                            FoodRoomDatabase.databaseWriteEXECUTOR_SERVICE.execute(new Runnable() {
                                @Override
                                public void run() {
                                    eOrderItemDao.insert(orderItem);
                                }
                            });

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

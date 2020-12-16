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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepo {

    private final OrderDao eOrderDao;
    private final OrderItemDao eOrderItemDao;
    private final VolleySingleton eVolleySingleton;
    LiveData<List<Order>> eLiveDataOrders = new MutableLiveData<>();
    public final static String API_UPDATE_ORDER_ITEM_STATUS = "https://hungerwarrior.herokuapp.com/api/orderItemsUpdateStatus/";
    LiveData<String> eStatusLiveData = new MutableLiveData<>();
    LiveData<List<OrderItem>> orderItemsLiveData = new MutableLiveData<>(
    );
    private final FirebaseUser eFirebaseUser;
    FirebaseAuth eFirebaseAuth;

    public OrderRepo(Application application) {
        FoodRoomDatabase foodRoomDatabase = FoodRoomDatabase.getFoodRoomDatabase(application);
        eOrderDao = foodRoomDatabase.eOrderDao();
        eOrderItemDao = foodRoomDatabase.eOrderItemDao();

        eFirebaseAuth = FirebaseAuth.getInstance();
        eVolleySingleton = VolleySingleton.getInstance(application.getApplicationContext());

        eFirebaseUser = eFirebaseAuth.getCurrentUser();

    }

    public final static String API_ORDERS_VENDOR = "https://hungerwarrior.herokuapp.com/api/orderItemsByVendor/";

    public LiveData<List<OrderItem>> getOrderItemsLiveData() {

        return eOrderItemDao.getAllOrderItems();
    }

    public LiveData<List<Order>> getOrderss() {
        return eOrderDao.getOrders();
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

        return eOrderItemDao.getOrderItemStatus(orderItemId);
    }

    public void setOrderItemStatus(int orderItemId, String status) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT
                , API_UPDATE_ORDER_ITEM_STATUS + orderItemId
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("status", status);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0
                , DefaultRetryPolicy.DEFAULT_MAX_RETRIES
                , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        eVolleySingleton.addToRequestQueue(stringRequest);
    }


}

package com.dakiiii.hungerwarriorvendor.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dakiiii.hungerwarriorvendor.FoodRepository;
import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.adapter.AllFoodsAdapter;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.networking.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FoodsListFragment extends Fragment {

    private RecyclerView eRecyclerView;
    private LiveData<List<Food>> eFoodsLiveData;
    private FoodViewModel eFoodViewModel;
    AllFoodsAdapter eAllFoodsAdapter;
    private FoodRepository eFoodRepository;
    private Context eContext;
    private String foodsUrl = "https://hungerwarrior.herokuapp.com/api/foods";
    private int NEW_FOOD_ACTIVITY_REQUEST_CODE = 1;
    private List<Food> eFoodList = new ArrayList<>();

    public FoodsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_foods_list, container, false);

        eRecyclerView = view.findViewById(R.id.recyclerview_allFoods);
        eAllFoodsAdapter = new AllFoodsAdapter();
        eRecyclerView.setAdapter(eAllFoodsAdapter);
        eRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eFoodRepository = new FoodRepository(getActivity().getApplication());
        eContext = getContext();

//        eFoodList = eFoodRepository.getFoodsFromServer(eContext);


//        addFoods();
        eAllFoodsAdapter.setFoods(eFoodList);
        getFoods();

        return view;
    }


    public void getFoods() {
        eFoodList.clear();
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET
                , foodsUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int foodId = jsonObject.getInt("id");
                        int food_price = jsonObject.getInt("food_price");
                        String food_name = jsonObject.getString("food_name");
                        String food_desc = jsonObject.getString("food_desc");

                        Food food = new Food(food_name, food_price);
                        food.setFoodId(foodId);
                        food.setFoodDescription(food_desc);

                        eFoodList.add(food);

                    }
                    eAllFoodsAdapter.setFoods(eFoodList);
                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
                Log.e(MainActivity.class.toString(), error.toString());
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }
}
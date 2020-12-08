package com.dakiiii.hungerwarriorvendor.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.adapter.AllFoodsAdapter;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.viewmodel.FoodListViewModel;

import java.util.ArrayList;
import java.util.List;


public class FoodsListFragment extends Fragment {

    private RecyclerView eRecyclerView;
    private LiveData<List<Food>> eFoodsLiveData;
    AllFoodsAdapter eAllFoodsAdapter;
    public static String foodsUrl = "https://hungerwarrior.herokuapp.com/api/foods";
    private final int NEW_FOOD_ACTIVITY_REQUEST_CODE = 1;
    private final List<Food> eFoodList = new ArrayList<>();
    private FoodListViewModel eFoodListViewModel;

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
        eRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        eFoodListViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getActivity().getApplication())
                .create(FoodListViewModel.class);

        eFoodListViewModel.getLiveDataFoods().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                eAllFoodsAdapter.setFoods(foods);
            }
        });


        return view;
    }

}
package com.dakiiii.hungerwarriorvendor.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.adapter.AllFoodsAdapter;
import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.List;


public class FoodsListFragment extends Fragment {

    private RecyclerView eRecyclerView;
    private LiveData<List<Food>> eFoodsLiveData;
    private FoodViewModel eFoodViewModel;
    private List<Food> eAllFoodsFoods;

    public FoodsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_foods_list, container, false);

        eRecyclerView = view.findViewById(R.id.recyclerview_allFoods);
        final AllFoodsAdapter allFoodsAdapter = new AllFoodsAdapter();
        eRecyclerView.setAdapter(allFoodsAdapter);
        eRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        eFoodViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity()
                .getApplication()).create(FoodViewModel.class);
        eFoodViewModel.getFoodsListLiveData().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                allFoodsAdapter.setFoods(foods);
            }
        });


        return view;
    }


}
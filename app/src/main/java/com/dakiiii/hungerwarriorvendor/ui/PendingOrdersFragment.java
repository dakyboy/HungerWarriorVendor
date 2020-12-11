package com.dakiiii.hungerwarriorvendor.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.adapter.OrdersAdapter;
import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.viewmodel.OrderViewModel;

import java.util.List;

public class PendingOrdersFragment extends Fragment {

    OrderViewModel eOrderViewModel;
    OrdersAdapter eOrdersAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_orders, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_orders);
        eOrdersAdapter = new OrdersAdapter();
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(eOrdersAdapter);

        eOrderViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getActivity().getApplication())
                .create(OrderViewModel.class);

        eOrderViewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                eOrdersAdapter.setOrders(orders);
            }
        });
        return view;
    }
}
package com.dakiiii.hungerwarriorvendor.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dakiiii.hungerwarriorvendor.R;

public class AddFoodFragment extends Fragment {

//    public interface On

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);

        return view;
    }
}
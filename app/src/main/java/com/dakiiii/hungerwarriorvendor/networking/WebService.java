package com.dakiiii.hungerwarriorvendor.networking;

import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.List;

public class WebService {
    private final String API_FOODS_URL = "http://hungerwarrior.herokuapp.com/api/foods";
    public List<Food> eFoods;

    public List<Food> getFoods() {
        return eFoods;
    }

    public void setFoods(List<Food> foods) {
        eFoods = foods;
    }
}

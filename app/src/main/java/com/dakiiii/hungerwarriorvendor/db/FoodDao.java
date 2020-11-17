package com.dakiiii.hungerwarriorvendor.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Food food);

    @Query("DELETE FROM food_table")
    void deleteAll();

    @Query("SELECT * FROM food_table")
    List<Food> getFoods();


}

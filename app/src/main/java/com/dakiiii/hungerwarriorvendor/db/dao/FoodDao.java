package com.dakiiii.hungerwarriorvendor.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Food food);

    @Query("DELETE FROM foods_table")
    void deleteAll();

    @Query("SELECT * FROM foods_table")
    LiveData<List<Food>> getFoods();


}

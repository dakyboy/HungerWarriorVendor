package com.dakiiii.hungerwarriorvendor.db.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Food.class, version = 1, exportSchema = false)
public abstract class FoodRoomDatabase extends RoomDatabase {
    public abstract FoodDao eFoodDao();
    private static volatile FoodRoomDatabase sFoodRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteEXECUTOR_SERVICE =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FoodRoomDatabase getFoodRoomDatabase(final Context context) {
        if (sFoodRoomDatabase == null) {
            synchronized (FoodRoomDatabase.class) {
                sFoodRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        FoodRoomDatabase.class, "food_database")
                .build();
            }
        }

        return sFoodRoomDatabase;
    }
}

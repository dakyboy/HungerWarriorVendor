package com.dakiiii.hungerwarriorvendor.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dakiiii.hungerwarriorvendor.db.dao.FoodDao;
import com.dakiiii.hungerwarriorvendor.db.dao.OrderDao;
import com.dakiiii.hungerwarriorvendor.db.dao.OrderItemDao;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.model.OrderItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Food.class, Order.class, OrderItem.class}, version = 1, exportSchema = false)
public abstract class FoodRoomDatabase extends RoomDatabase {
    public abstract FoodDao eFoodDao();

    public abstract OrderDao eOrderDao();

    public abstract OrderItemDao eOrderItemDao();

    private static volatile FoodRoomDatabase sFoodRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteEXECUTOR_SERVICE =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FoodRoomDatabase getFoodRoomDatabase(final Context context) {
        if (sFoodRoomDatabase == null) {
            synchronized (FoodRoomDatabase.class) {
                sFoodRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        FoodRoomDatabase.class, "food_database")
                        .addCallback(sCallback)
                        .build();
            }
        }

        return sFoodRoomDatabase;
    }

    private static final RoomDatabase.Callback sCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteEXECUTOR_SERVICE.execute(() -> {
                FoodDao foodDao = sFoodRoomDatabase.eFoodDao();
                foodDao.deleteAll();

            });
        }
    };
}

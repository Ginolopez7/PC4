package com.gino.evaluacionfinal.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gino.evaluacionfinal.model.ShowEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ShowEntity.class}, version = 1)
public abstract class ECDatabase extends RoomDatabase {

    public abstract ShowDao showDao();

    private static  volatile ECDatabase db;

    public static final ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static ECDatabase getInstance(Context context) {
        if (db == null) {
            synchronized (ECDatabase.class) {
                if (db == null){
                    db = Room.databaseBuilder(context.getApplicationContext(), ECDatabase.class, "shows-database").build();
                }
            }
        }
        return db;
    }
}

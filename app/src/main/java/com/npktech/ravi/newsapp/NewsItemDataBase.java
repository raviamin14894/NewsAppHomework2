package com.npktech.ravi.newsapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsItemDataBase extends RoomDatabase {

    public abstract NewsItemDao newsItemDao();

    private static NewsItemDataBase INSTANCE;

    public static NewsItemDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (NewsItemDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , NewsItemDataBase.class, "newsdatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

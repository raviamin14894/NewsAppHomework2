package com.npktech.ravi.newsapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;



@Dao
public interface NewsItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<NewsItem> newsItem);

    @Query("DELETE FROM news_item")
    void clearAll();

    @Query("SELECT * FROM news_item")
    LiveData<List<NewsItem>> loadAllNewsItem();

    @Query("SELECT * FROM news_item")
    List<NewsItem> loadAllNewsItemWithoutLiveData();


}

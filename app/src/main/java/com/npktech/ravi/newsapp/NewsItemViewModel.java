package com.npktech.ravi.newsapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;



public class NewsItemViewModel extends AndroidViewModel {


    NewsItemRepository newsItemRepository;
    NewsItemDao newsItemDao;

    public NewsItemViewModel(Application application) {
        super(application);
        this.newsItemRepository = new NewsItemRepository(application);
        newsItemDao =newsItemRepository.getNewsItemDao();
    }



    public void refreshData(){
        newsItemRepository.addDataFromServerToLocal();
    }



    public MutableLiveData<List<NewsItem>> getAlldata(){
       return newsItemRepository.getAllNewsItem();
    }

    public LiveData<List<NewsItem>> getAlldataFromLiveData(){
        return newsItemDao.loadAllNewsItem();
    }


}

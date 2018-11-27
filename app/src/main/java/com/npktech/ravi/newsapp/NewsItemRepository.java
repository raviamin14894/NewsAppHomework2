package com.npktech.ravi.newsapp;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;



public class NewsItemRepository {

    NewsItemDao newsItemDao;
    NewsItemDataBase newsItemDataBase;



    public NewsItemRepository(Context context) {
        newsItemDataBase = NewsItemDataBase.getDatabase(context);
        newsItemDao = newsItemDataBase.newsItemDao();
    }


    public MutableLiveData<List<NewsItem>> getAllNewsItem() {
        try {
            return new loadAllData().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used to get NewsItemDao class reference...
     *
     * @return
     */

    public NewsItemDao getNewsItemDao() {
        return newsItemDao;
    }

    /**
     * load all data from local using LiveData
     */

    private class loadAllData extends AsyncTask<Void, Void, MutableLiveData<List<NewsItem>>> {

        @Override
        protected MutableLiveData<List<NewsItem>> doInBackground(Void... voids) {
            MutableLiveData<List<NewsItem>> listMutableLiveData = new MutableLiveData<>();
            listMutableLiveData.postValue(newsItemDao.loadAllNewsItemWithoutLiveData());
            return listMutableLiveData;
        }
    }


    /**
     * Fetch data from server and clear all local
     * data from data base and set new data in database.
     */
    public void addDataFromServerToLocal() {
        new getNewsApiResponse().execute();
    }

    /**
     * Fetch data from server and clear all local
     * data from data base and set new data in database.
     */
    public class getNewsApiResponse extends AsyncTask<Void, Void, Void> {

        String response;

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                response = NetworkUtils.getResponseFromHttpUrl
                        (new URL(NetworkUtils.buildURL(NetworkUtils.base_url, NetworkUtils.apiKey)));

                if (JsonUtils.parseData(response).size() != 0) {
                    newsItemDao.clearAll();
                    newsItemDao.insert(JsonUtils.parseData(response));
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }

    }
}

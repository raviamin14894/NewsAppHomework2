package com.npktech.ravi.newsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class JsonUtils {

    public static List<NewsItem> parseData(String json) {

        List<NewsItem> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("status")) {
                if (jsonObject.getString("status").trim().equalsIgnoreCase("ok")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    list.clear();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject articles = jsonArray.getJSONObject(i);

                        NewsItem newsItem = new NewsItem(articles.getString("title"),
                                articles.getString("description"),
                                articles.getString("url"),
                                articles.getString("urlToImage"),
                                articles.getString("publishedAt"),
                                articles.getString("author"));
                        list.add(newsItem);

                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}

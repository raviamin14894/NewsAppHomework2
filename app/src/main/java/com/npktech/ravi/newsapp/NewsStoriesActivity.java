package com.npktech.ravi.newsapp;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class NewsStoriesActivity extends AppCompatActivity {

    NewsAdapter adapter;
    NewsItemViewModel newsStoriesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_stories);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new NewsAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NewsStoriesActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        newsStoriesViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);

        newsStoriesViewModel.getAlldata().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                adapter.setNewsItems(newsItems);
            }
        });


    }

    private void loadData() {

        final ProgressDialog progressDialog = new ProgressDialog(NewsStoriesActivity.this);
        progressDialog.setTitle("Please wait..");
        progressDialog.setMessage("Fetching news stories...");
        progressDialog.show();

        newsStoriesViewModel.refreshData();

        newsStoriesViewModel.getAlldata().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                adapter.setNewsItems(newsItems);
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.refresh) {
            if (isOnline(NewsStoriesActivity.this)) {
                loadData();
            } else {
                Toast.makeText(this, "There is no internet connection, Please try again...", Toast.LENGTH_LONG).show();
            }
        }

        return true;
    }

    public static boolean isOnline(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) return false;
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}

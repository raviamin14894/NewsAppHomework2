package com.npktech.ravi.newsapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;



@Entity(tableName = "news_item")
public class NewsItem {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "webUrl")
    private String webUrl;

    @ColumnInfo(name = "imageUrL")
    private String imageUrL;

    @ColumnInfo(name = "publishedAt")
    private String publishedAt;

    @ColumnInfo(name = "author")
    private String author;

    public NewsItem(@NonNull int id, String title, String description, String webUrl, String imageUrL, String publishedAt, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.webUrl = webUrl;
        this.imageUrL = imageUrL;
        this.publishedAt = publishedAt;
        this.author = author;
    }

    /**
     * 2nd constructor ignores int id
     * which is autoincrement in our table...
     * @param title
     * @param description
     * @param webUrl
     * @param imageUrL
     * @param publishedAt
     * @param author
     */

    @Ignore
    public NewsItem(String title, String description, String webUrl, String imageUrL, String publishedAt, String author) {
        this.title = title;
        this.description = description;
        this.webUrl = webUrl;
        this.imageUrL = imageUrL;
        this.publishedAt = publishedAt;
        this.author = author;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getImageUrL() {
        return imageUrL;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getAuthor() {
        return author;
    }
}

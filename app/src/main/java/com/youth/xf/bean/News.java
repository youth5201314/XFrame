package com.youth.xf.bean;

import java.io.Serializable;


public class News implements Serializable {
    /**
     * 单图布局样式
     */
    public static final int TYPE_SINGLE_PICTURE = 0;
    /**
     * 无图布局样式
     */
    public static final int TYPE_NONE_PICTURE = 1;

    private int newsType;
    private String title;
    private String imageUrl;
    private String author;
    private String time;

    public News(int newsType, String title, String imageUrl, String author, String time) {
        this.newsType = newsType;
        this.title = title;
        this.imageUrl = imageUrl;
        this.author = author;
        this.time = time;
    }

    public News(int newsType, String title, String author, String time) {
        this.newsType = newsType;
        this.title = title;
        this.author = author;
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "newsType=" + newsType + "\n" +
                "title =" + title + "\n" +
                "imageUrl =" + imageUrl + "\n" +
                "author =" + author + "\n" +
                "time = " + time;
    }
}

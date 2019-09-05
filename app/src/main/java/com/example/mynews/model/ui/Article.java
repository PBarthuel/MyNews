package com.example.mynews.model.ui;

public class Article {

    private String imageUrl;
    private String title;
    private String topic;
    private String date;
    private String url;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public Article(String imageUrl, String title, String topic, String date, String url) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.topic = topic;
        this.date = date;
        this.url = url;
    }
}

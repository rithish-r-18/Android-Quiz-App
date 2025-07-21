package com.example.quizapp;

public class Book {
    private String title;
    private int imageRes;
    private String buyUrl;

    public Book(String title, int imageRes, String buyUrl) {
        this.title = title;
        this.imageRes = imageRes;
        this.buyUrl = buyUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getBuyUrl() {
        return buyUrl;
    }
}

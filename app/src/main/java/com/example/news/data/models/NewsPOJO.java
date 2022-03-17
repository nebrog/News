package com.example.news.data.models;

import com.google.gson.annotations.SerializedName;

public class NewsPOJO {



    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("urlToImage")
    public String image;

    @Override
    public String toString() {
        return "NewsPOJO{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

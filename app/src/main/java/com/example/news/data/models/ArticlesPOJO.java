package com.example.news.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesPOJO {
    @SerializedName("articles")
    public List<NewsPOJO> articles;
}

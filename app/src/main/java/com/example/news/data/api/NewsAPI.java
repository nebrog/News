package com.example.news.data.api;

import com.example.news.data.models.ArticlesPOJO;
import com.example.news.data.models.NewsPOJO;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.util.List;

import io.reactivex.Single;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.http.GET;

import retrofit2.http.Query;

public interface NewsAPI {

    static NewsAPI getApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        NewsAPI newsAPI = retrofit.create(NewsAPI.class);
        newsAPI.loadNews("ru","Russia","347d40fa99b74141a0e40d3ce326b6cd");

        return newsAPI;
    }

    @GET("v2/everything")
    Single<ArticlesPOJO> loadNews(@Query("language")String language,
                                   @Query("q") String source,
                                   @Query("apiKey")String key);



}

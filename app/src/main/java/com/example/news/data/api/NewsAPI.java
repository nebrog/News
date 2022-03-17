package com.example.news.data.api;

import com.example.news.data.models.ArticlesPOJO;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {

    static NewsAPI getApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        NewsAPI newsAPI = retrofit.create(NewsAPI.class);

        return newsAPI;
    }

    @GET("v2/everything")
    Single<ArticlesPOJO> loadNews(@Query("language")String language,
                                  @Query("q") String source,
                                  @Query("apiKey")String key);



}

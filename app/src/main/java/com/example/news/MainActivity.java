package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.news.data.NewsAdapter;
import com.example.news.data.api.NewsAPI;
import com.example.news.data.models.ArticlesPOJO;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    NewsAPI newsAPI = NewsAPI.getApi();
    NewsAdapter newsAdapter = new NewsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayout =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(newsAdapter);
        loadNews();


    }
    public void loadNews(){
        newsAPI.loadNews("ru", "Russia", "347d40fa99b74141a0e40d3ce326b6cd")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<ArticlesPOJO>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ArticlesPOJO articlesPOJO) {
                        newsAdapter.setNews(articlesPOJO.articles);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("nebrog",e.toString(),e);

                    }
                });
    }

}
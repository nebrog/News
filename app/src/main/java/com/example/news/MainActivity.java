package com.example.news;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.data.api.NewsAPI;
import com.example.news.data.models.ArticlesPOJO;
import com.example.news.ui.NewsAdapter;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final NewsAPI newsAPI = NewsAPI.getApi();
    private final NewsAdapter newsAdapter = new NewsAdapter();

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button refreshButton;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViews();
        setupRecycler();
        setupListeners();
        loadNews();
    }

    private void findAllViews() {
        recyclerView = findViewById(R.id.recycler);
        errorText = findViewById(R.id.error_text);
        refreshButton = findViewById(R.id.refresh_button);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(newsAdapter);
    }

    private void setupListeners() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNews();
            }
        });
    }

    public void loadNews() {
        errorText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        refreshButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        newsAPI
                .loadNews("ru", "Russia", "347d40fa99b74141a0e40d3ce326b6cd")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<ArticlesPOJO>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ArticlesPOJO articlesPOJO) {
                        newsAdapter.setNews(articlesPOJO.articles);
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("nebrog", e.toString(), e);
                        progressBar.setVisibility(View.GONE);
                        errorText.setVisibility(View.VISIBLE);
                        refreshButton.setVisibility(View.VISIBLE);
                    }
                });
    }
}
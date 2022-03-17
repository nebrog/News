package com.example.news.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.data.models.NewsPOJO;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
     List<NewsPOJO> newsPOJOS = new ArrayList<>();

    public void setNews(List<NewsPOJO> newsPOJOList){
        newsPOJOS = newsPOJOList;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsPOJO news = newsPOJOS.get(position);
        holder.title.setText(news.title);
        holder.description.setText(news.description);
        holder.url.setText(news.url);
        Glide
                .with(holder.image)
                .load(news.image)
                .into(holder.image);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent,false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(v);
        return newsViewHolder;
    }


    @Override
    public int getItemCount() {
        return newsPOJOS.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        TextView description;
        TextView url;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            url = itemView.findViewById(R.id.url);
        }
    }
}

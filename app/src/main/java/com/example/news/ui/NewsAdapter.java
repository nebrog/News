package com.example.news.ui;

import android.content.Intent;
import android.net.Uri;
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
        Glide
                .with(holder.image)
                .load(news.image)
                .centerCrop()
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.url));
                holder.itemView.getContext().startActivity(browserIntent);
            }
        });
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

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
        }
    }
}

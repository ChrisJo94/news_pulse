package com.example.news_app_2;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.Source;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecViewAdapter extends RecyclerView.Adapter<NewsRecViewAdapter.ViewHolder>{

    List<MultiResource.Articles> articlesList;
    Context context;

    public NewsRecViewAdapter(List<MultiResource.Articles> articlesList, Context context) {
        this.articlesList = articlesList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_row,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MultiResource.Articles article=articlesList.get(position);
//        Source source=articlesList.get(position).getSource();
        holder.title.setText(article.getTitle());
        holder.source.setText(article.getSource().getName());
        Glide.with(context).load(article.getImage())
                .error(R.drawable.noun_567)
                .placeholder(R.drawable.noun_567)
                .into(holder.img);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(view.getContext(), web_load.class); //webview
                Intent intent=new Intent(view.getContext(), News_Article.class);
                intent.putExtra("img",article.getImage());
                intent.putExtra("title",article.getTitle());
                intent.putExtra("description",article.getDescription());
                intent.putExtra("content",article.getContent());
                intent.putExtra("url",article.getUrl());
                intent.putExtra("source",article.getSource().getName());
//                intent.putExtra("url",article.getUrl());      // webview
                view.getContext().startActivity(intent);

            }
        });



    }

    public void addArticle(List<MultiResource.Articles> Data){
        articlesList.clear();
        articlesList.addAll(Data);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private LinearLayout parent;
        private TextView title,source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            parent=itemView.findViewById(R.id.parent);
            title=itemView.findViewById(R.id.title);
            source=itemView.findViewById(R.id.source);

        }
    }

}

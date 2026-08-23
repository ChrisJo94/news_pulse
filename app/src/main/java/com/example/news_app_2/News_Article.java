package com.example.news_app_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class News_Article extends AppCompatActivity {
    private String img,title,content,description,desp_con,trunc,url,source;
    private ImageView news_img,read_img;
    private TextView headline,news_content,source_name;
    private RelativeLayout news_source;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_article);

        read_img=findViewById(R.id.read_img);
        news_source=findViewById(R.id.news_source);

        news_img=findViewById(R.id.news_img);
        headline=findViewById(R.id.headline);

        news_content=findViewById(R.id.news_content);
        source_name=findViewById(R.id.source_name);


        img=getIntent().getStringExtra("img");
        title=getIntent().getStringExtra("title");
        description=getIntent().getStringExtra("description");
        content=getIntent().getStringExtra("content");
        trunc=(description+" "+content).replaceAll("\\[.*?]","");
        desp_con=trunc;
        source=getIntent().getStringExtra("source");




        Glide.with(this)
                .load(img)
                .error(R.drawable.noun_567)
                .placeholder(R.drawable.noun_567)
                .into(news_img);

        Glide.with(this)
                .load(img)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(45,3)))
                .into(read_img);


        headline.setText(title);
        news_content.setText(desp_con);
        source_name.setText(source);

        url=getIntent().getStringExtra("url");

        news_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(News_Article.this, web_load.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });













    }
}
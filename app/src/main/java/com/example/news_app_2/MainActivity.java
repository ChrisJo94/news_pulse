package com.example.news_app_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRecView;
    private NewsRecViewAdapter adapter;
    private ProgressBar pbar3;
    private String query;
    private TabLayout tabs;

    List<MultiResource.Articles> articlesList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        pbar3=findViewById(R.id.pbar3);
        Button log_out=findViewById(R.id.log_out);


        newsRecView=findViewById(R.id.newsRecView);
        newsRecView.setLayoutManager(new LinearLayoutManager(this));
        tabs=findViewById(R.id.tabs);
        getNews("General",null);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabText=tab.getText().toString().toLowerCase();
                getNews(tabText,null);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        log_out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(MainActivity.this, loginotp.class));
//                finish();
//            }
//        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("Log Out")
                        .setMessage("Do you want to log out?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(MainActivity.this, loginotp.class));
                                finish();
                            }
                        });
                AlertDialog dialog=builder.create();
                dialog.show();

            }

        });


    }


    public void setRecAdapter(){
        adapter= new NewsRecViewAdapter(articlesList,this);
        newsRecView.setAdapter(adapter);
    }
    public void barVisibility (boolean show){
        if(show){
            pbar3.setVisibility(View.VISIBLE);
        }
        else{
            pbar3.setVisibility(View.GONE);
        }
    }

    public void getNews(String category,String query){
        barVisibility(true);
        newsRecView.setVisibility(View.GONE);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://gnews.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterface apiInterface=retrofit.create(APIInterface.class);

        Call<MultiResource> call=apiInterface.getNewsArticle(category);
        call.enqueue(new Callback<MultiResource>() {
            @Override
            public void onResponse(Call<MultiResource> call, Response<MultiResource> response) {
                if(response.isSuccessful() && response.body()!=null){


                    newsRecView.setVisibility(View.VISIBLE);
                    barVisibility(false);

                    articlesList=response.body().getArticles();

                    setRecAdapter();



                }
            }

            @Override
            public void onFailure(Call<MultiResource> call, Throwable throwable) {

            }
        });

    }


}
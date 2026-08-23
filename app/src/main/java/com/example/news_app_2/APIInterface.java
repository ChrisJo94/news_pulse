package com.example.news_app_2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/api/v4/top-headlines?apikey=aa7c99efe544fc31969ba2405e36f061&lang=en")
    Call<MultiResource> getNewsArticle(@Query("category") String category);

}

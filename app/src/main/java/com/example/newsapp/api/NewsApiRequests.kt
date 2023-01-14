package com.example.newsapp.api

import com.example.newsapp.model.Articles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiRequests {
    @GET("everything")
    fun getArticles(@Query("q") searchWord: String, @Query("apiKey") apiKey: String): Call<Articles>
}
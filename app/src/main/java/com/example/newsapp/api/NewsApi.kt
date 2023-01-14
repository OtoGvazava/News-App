package com.example.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApi {
    private const val baseUrl = "https://newsapi.org/v2/"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val requests: NewsApiRequests by lazy {
        getInstance().create(NewsApiRequests::class.java)
    }
}
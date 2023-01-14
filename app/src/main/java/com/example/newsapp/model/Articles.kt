package com.example.newsapp.model

data class Articles(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

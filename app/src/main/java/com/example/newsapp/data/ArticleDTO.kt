package com.example.newsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.model.Source
import java.util.*


@Entity(tableName = "article_table")
data class ArticleDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val content: String,
    val source: String
)

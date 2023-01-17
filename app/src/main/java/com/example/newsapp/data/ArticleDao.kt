package com.example.newsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addArticle(articleDTO: ArticleDTO)

    @Query("SELECT * FROM article_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<ArticleDTO>>
}
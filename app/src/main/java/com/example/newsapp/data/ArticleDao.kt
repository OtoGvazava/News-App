package com.example.newsapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addArticle(articleDTO: ArticleDTO)

    @Delete
    suspend fun deleteArticle(articleDTO: ArticleDTO)

    @Query("SELECT * FROM article_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<ArticleDTO>>

}
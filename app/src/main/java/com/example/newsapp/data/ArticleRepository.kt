package com.example.newsapp.data

import androidx.lifecycle.LiveData

class ArticleRepository(private val articleDao: ArticleDao) {

    val readAllData: LiveData<List<ArticleDTO>> = articleDao.readAllData()

    suspend fun addArticle(articleDTO: ArticleDTO)
    {
        articleDao.addArticle(articleDTO)
    }

    suspend fun deleteArticle(articleDTO: ArticleDTO)
    {
        articleDao.deleteArticle(articleDTO)
    }
}
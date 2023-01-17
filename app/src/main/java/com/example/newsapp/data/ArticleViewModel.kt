package com.example.newsapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<ArticleDTO>>
    private val repository: ArticleRepository

    init {
        val articleDao = ArticleDatabase.getDatabase(application).articleDao()
        repository = ArticleRepository(articleDao)
        readAllData = repository.readAllData
    }

    fun addArticle(articleDTO: ArticleDTO)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(articleDTO)
        }
    }
}
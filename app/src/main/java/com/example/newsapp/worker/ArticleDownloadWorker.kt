package com.example.newsapp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.newsapp.AppData
import com.example.newsapp.api.NewsApi
import com.example.newsapp.data.ArticleDTO
import com.example.newsapp.model.Articles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleDownloadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val call = NewsApi.requests.getArticles(AppData.searchKeyword, AppData.apiKey)
        call.enqueue(object : Callback<Articles> {
            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                when (response.isSuccessful) {
                    true -> {
                        val articles = response.body()!!
                        articles.articles.forEach {
                            AppData.mArticleViewModel.addArticle(
                                ArticleDTO(
                                    0,
                                    it.author,
                                    it.title,
                                    it.description,
                                    it.url,
                                    it.urlToImage,
                                    it.content,
                                    it.source.name
                                )
                            )
                        }
                    }
                    false -> {}
                }
            }

            override fun onFailure(call: Call<Articles>, t: Throwable) {
            }
        })
        return Result.success()
    }
}
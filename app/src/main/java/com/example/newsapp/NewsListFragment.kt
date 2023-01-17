package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.api.NewsApi
import com.example.newsapp.data.ArticleViewModel
import com.example.newsapp.model.Articles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListFragment : Fragment() {
    private val apiKey = "23acad7735bb43409aebb9e559a84523"

    private lateinit var mArticleViewModel: ArticleViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        mArticleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)



        fun getTextFromSearch(): String? {
            val text = view.findViewById<EditText>(R.id.editTextSearchText).text.toString()
            return text.ifEmpty {
                Toast.makeText(view.context, "Please Fill Search Field!", Toast.LENGTH_LONG).show()
                null
            }
        }

        fun loadData() {
            val searchText = getTextFromSearch()
            if (searchText != null) {
                val call = NewsApi.requests.getArticles(searchText, apiKey)
                call.enqueue(object : Callback<Articles> {
                    override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                        when (response.isSuccessful) {
                            true -> {
                                val articles = response.body()!!
                                recyclerView.adapter = ArticleAdapter(articles, mArticleViewModel, requireContext())
                            }
                            false -> Toast.makeText(view.context, "Unsuccessfully response", Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<Articles>, t: Throwable) {
                        Toast.makeText(view.context, "Request fail", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        view.findViewById<Button>(R.id.buttonSearch).setOnClickListener {
            loadData()
        }

        loadData()
        return view
    }
}
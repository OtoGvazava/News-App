package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.ArticleViewModel


class SavedArticleFragment : Fragment() {

    private lateinit var mArticleViewModel: ArticleViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_saved_article, container, false)
        mArticleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        val adapter = SavedArticleAdapter(requireContext(), mArticleViewModel)
        val recyclerView = view.findViewById<RecyclerView>(R.id.savedArticleList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mArticleViewModel.readAllData.observe(viewLifecycleOwner, Observer { article->
            adapter.setData(article)
        })

        return view
    }


}
package com.example.newsapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.model.Articles
import com.squareup.picasso.Picasso

class ArticleAdapter(private val articles: Articles): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(articleView: View): RecyclerView.ViewHolder(articleView) {
        val title: TextView = articleView.findViewById(R.id.item_title)
        val description: TextView = articleView.findViewById(R.id.item_description)
        val image: ImageView = articleView.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles.articles[position]
        holder.title.text = article.title
        holder.description.text = article.description
        Picasso.get().load(Uri.parse(article.urlToImage)).into(holder.image)
    }

    override fun getItemCount(): Int {
        return articles.articles.size
    }
}
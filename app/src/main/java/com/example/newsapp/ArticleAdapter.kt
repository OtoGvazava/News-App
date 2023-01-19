package com.example.newsapp

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.ArticleDTO
import com.example.newsapp.data.ArticleViewModel
import com.example.newsapp.model.Articles
import com.example.newsapp.notification.NotificationService
import com.squareup.picasso.Picasso

class ArticleAdapter(private val articles: Articles, private val mArticleViewModel: ArticleViewModel, private val context: Context): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(articleView: View): RecyclerView.ViewHolder(articleView) {
        val title: TextView = articleView.findViewById(R.id.item_title)
        val description: TextView = articleView.findViewById(R.id.item_description)
        val image: ImageView = articleView.findViewById(R.id.item_image)
        val cardView: CardView = articleView.findViewById(R.id.cardViewArticle)
        val addBtn: Button = articleView.findViewById(R.id.addBtn)
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
        holder.addBtn.setOnClickListener {

            val articleDTO = ArticleDTO(0, article.author, article.title, article.description, article.url, article.urlToImage, article.content, article.source.name)
            val service = NotificationService(context)
            mArticleViewModel.addArticle(articleDTO)
            Toast.makeText(context, "Successfully saved Article!", Toast.LENGTH_LONG).show()

            service.showNotification(articleDTO)
        }
        holder.cardView.setOnClickListener {
            val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(article.description, article.title, article.urlToImage, article.content, article.url, article.author, article.source.name)
            holder.cardView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return articles.articles.size
    }


}
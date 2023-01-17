package com.example.newsapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.ArticleDTO
import com.squareup.picasso.Picasso

class SavedArticleAdapter: RecyclerView.Adapter<SavedArticleAdapter.ViewHolder>() {
    private var articleList = emptyList<ArticleDTO>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.item_title)
        val description: TextView = itemView.findViewById(R.id.item_description)
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val cardView: CardView = itemView.findViewById(R.id.cardViewArticle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.saved_article_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.title.text = article.title
        holder.description.text = article.description
        Picasso.get().load(Uri.parse(article.urlToImage)).into(holder.image)

        holder.cardView.setOnClickListener {
            val action = SavedArticleFragmentDirections.actionSavedArticleFragmentToNewsDetailFragment(article.description, article.title, article.urlToImage, article.content, article.url, article.author, article.source)
            holder.cardView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return  articleList.size
    }

    fun setData(articles: List<ArticleDTO>){
        this.articleList = articles
        notifyDataSetChanged()
    }
}
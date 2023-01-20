package com.example.newsapp

import android.app.AlertDialog
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
import com.squareup.picasso.Picasso

class SavedArticleAdapter(
    private val context: Context,
    private val mArticleViewModel: ArticleViewModel
) : RecyclerView.Adapter<SavedArticleAdapter.ViewHolder>() {
    private var articleList = emptyList<ArticleDTO>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.item_title)
        val description: TextView = itemView.findViewById(R.id.item_description)
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val cardView: CardView = itemView.findViewById(R.id.cardViewArticle)
        val deleteBtn: Button = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.saved_article_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.title.text = article.title
        holder.description.text = article.description
        Picasso.get().load(Uri.parse(article.urlToImage)).into(holder.image)

        holder.deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Yes") { _, _ ->
                mArticleViewModel.deleteArticle(article)
                Toast.makeText(context, "Deleted ${article.title}", Toast.LENGTH_LONG).show()
                notifyItemRemoved(position)
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${article.title}?")
            builder.setMessage("Are you sure you want to delete ${article.title}?")
            builder.create().show()
        }
        holder.cardView.setOnClickListener {
            val action =
                SavedArticleFragmentDirections.actionSavedArticleFragmentToNewsDetailFragment(
                    article.description,
                    article.title,
                    article.urlToImage,
                    article.content,
                    article.url,
                    article.author,
                    article.source
                )
            holder.cardView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setData(articles: List<ArticleDTO>) {
        this.articleList = articles
        notifyDataSetChanged()
    }
}
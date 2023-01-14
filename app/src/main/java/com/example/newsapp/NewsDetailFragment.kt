package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso


class NewsDetailFragment : Fragment() {
    private val args: NewsDetailFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)
        Picasso.get().load(Uri.parse(args.imageUri)).into(view.findViewById<ImageView>(R.id.article_image))
        view.findViewById<TextView>(R.id.article_title).text = args.title
        view.findViewById<TextView>(R.id.article_content).text = args.content
        view.findViewById<TextView>(R.id.article_author).text = "Author: " + args.author
        view.findViewById<TextView>(R.id.article_description).text = args.description
        view.findViewById<TextView>(R.id.article_source).text = "Source: " + args.source
        view.findViewById<Button>(R.id.article_open_button).setOnClickListener {
            val uri = Uri.parse(args.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        return view
    }
}
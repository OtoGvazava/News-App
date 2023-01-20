package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.data.ArticleViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNav)
        val navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.newsListFragment,
                    R.id.savedArticleFragment,
                    R.id.appSettings
                )
            )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

        AppData.mArticleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
    }
}
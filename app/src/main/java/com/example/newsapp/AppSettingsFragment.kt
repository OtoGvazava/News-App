package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.newsapp.worker.ArticleDownloadWorker
import java.util.concurrent.TimeUnit

class AppSettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_app_settings, container, false)

        view.findViewById<Button>(R.id.periodicSetButton).setOnClickListener {
            AppData.searchKeyword = view.findViewById<EditText>(R.id.editTextSearchKeyWord).text.toString()
            val timeHour =
                view.findViewById<EditText>(R.id.editTextTimeHour).text.toString().toLong()
            val articleDownloadRequest =
                PeriodicWorkRequestBuilder<ArticleDownloadWorker>(timeHour, TimeUnit.MINUTES).build()
            WorkManager.getInstance(view.context).enqueue(articleDownloadRequest)
            Toast.makeText(view.context, "Auto download set successfully!", Toast.LENGTH_LONG).show()
        }

        return view
    }
}
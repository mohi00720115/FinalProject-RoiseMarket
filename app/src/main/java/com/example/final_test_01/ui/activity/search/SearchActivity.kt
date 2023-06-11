package com.example.final_test_01.ui.activity.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_test_01.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
}
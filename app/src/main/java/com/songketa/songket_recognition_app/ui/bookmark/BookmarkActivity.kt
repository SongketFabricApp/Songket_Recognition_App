package com.songketa.songket_recognition_app.ui.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.songketa.songket_recognition_app.R

class BookmarkActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        init()
    }

    private fun init() {
        navigation = findViewById(R.id.bottomNavigationView)
    }
}
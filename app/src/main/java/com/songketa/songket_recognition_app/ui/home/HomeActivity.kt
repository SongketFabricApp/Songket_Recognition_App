package com.songketa.songket_recognition_app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.databinding.ActivityHomeBinding
import com.songketa.songket_recognition_app.databinding.ActivityMainBinding
import com.songketa.songket_recognition_app.ui.bookmark.BookmarkActivity
import com.songketa.songket_recognition_app.ui.camera.CameraActivity
import com.songketa.songket_recognition_app.ui.profile.ProfileActivity

class   HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.rvSongket.layoutManager = layoutManager

        val layoutManager2 = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.rvMenu.layoutManager = layoutManager2
    }




}
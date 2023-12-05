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
    private lateinit var navigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        navigationListener()

        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.rvSongket.layoutManager = layoutManager

        val layoutManager2 = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.rvMenu.layoutManager = layoutManager2
    }

    private fun init() {
        navigation = findViewById(R.id.bottomNavigationView)
    }

    private fun navigationListener() {
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    true
                }
                R.id.nav_scan -> {
                    startActivity(Intent(this, CameraActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left)
                    finish()
                    true
                }
                R.id.nav_bookmark -> {
                    startActivity(Intent(this, BookmarkActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_account -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
//    private fun updateBottomNavColor(colorResId: Int) {
//        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        bottomNavigationView.itemIconTintList = getColorStateList(colorResId)
//        bottomNavigationView.itemTextColor = getColorStateList(colorResId)
//    }
}
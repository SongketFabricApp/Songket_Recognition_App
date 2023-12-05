package com.songketa.songket_recognition_app.ui.camera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.ui.bookmark.BookmarkActivity
import com.songketa.songket_recognition_app.ui.home.HomeActivity
import com.songketa.songket_recognition_app.ui.profile.ProfileActivity

class CameraActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        init()
        navigationListener()
    }
    private fun init() {
        navigation = findViewById(R.id.bottomNavigationView)
    }

    private fun navigationListener() {
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_scan -> {
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
}
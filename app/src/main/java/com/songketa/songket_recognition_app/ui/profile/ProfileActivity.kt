package com.songketa.songket_recognition_app.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.ui.bookmark.BookmarkActivity
import com.songketa.songket_recognition_app.ui.camera.CameraActivity
import com.songketa.songket_recognition_app.ui.home.HomeActivity
import com.songketa.songket_recognition_app.ui.signin.SignInActivity
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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
                    startActivity(Intent(this, CameraActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_bookmark -> {
                    startActivity(Intent(this, BookmarkActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_account -> {
                    true
                }
                else -> false
            }
        }
    }
}
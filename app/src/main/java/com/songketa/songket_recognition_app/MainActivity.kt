package com.songketa.songket_recognition_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.songketa.songket_recognition_app.databinding.ActivityMainBinding
import com.songketa.songket_recognition_app.ui.bookmark.BookmarkActivity
import com.songketa.songket_recognition_app.ui.camera.CameraActivity
import com.songketa.songket_recognition_app.ui.home.HomeActivity
import com.songketa.songket_recognition_app.ui.profile.ProfileActivity
import com.songketa.songket_recognition_app.ui.splashscreen.SplashScreenActivity
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}
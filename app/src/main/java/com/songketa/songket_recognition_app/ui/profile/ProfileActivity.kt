package com.songketa.songket_recognition_app.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.ui.home.HomeActivity
import com.songketa.songket_recognition_app.ui.signin.SignInActivity
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var tvText: TextView
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
        // Atur warna teks item
        navigation.itemTextColor = getColorStateList(R.color.white)

        // Atur warna ikon item
        navigation.itemIconTintList = getColorStateList(R.color.white)

        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
//                    return@setOnItemSelectedListener true
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_scan -> {
                    return@setOnItemSelectedListener true
                }
                R.id.nav_bookmark -> {
                    return@setOnItemSelectedListener true
                }
                R.id.nav_account -> {
//                    return@setOnItemSelectedListener true
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            false
        }
    }
}
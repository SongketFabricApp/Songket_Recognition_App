package com.songketa.songket_recognition_app.ui.detailsongket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.songketa.songket_recognition_app.MainActivity
import com.songketa.songket_recognition_app.databinding.ActivityDetailSongketBinding

class DetailSongketActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView
    private lateinit var binding : ActivityDetailSongketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSongketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.homebutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    companion object{
        const val ID = ""
    }

}
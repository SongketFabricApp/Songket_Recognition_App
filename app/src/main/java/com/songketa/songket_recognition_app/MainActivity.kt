package com.songketa.songket_recognition_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.songketa.songket_recognition_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
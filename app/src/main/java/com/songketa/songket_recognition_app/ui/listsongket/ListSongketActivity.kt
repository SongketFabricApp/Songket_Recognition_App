package com.songketa.songket_recognition_app.ui.listsongket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.databinding.ActivityListSongketBinding
import com.songketa.songket_recognition_app.di.Injection
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.ui.bookmark.BookmarkActivity
import com.songketa.songket_recognition_app.ui.camera.CameraActivity
import com.songketa.songket_recognition_app.ui.home.HomeActivity
import com.songketa.songket_recognition_app.ui.profile.ProfileActivity

class ListSongketActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView
    private lateinit var viewModel: ListSongketViewModel
    private lateinit var adapter: ListSongketAdapter
    private lateinit var binding: ActivityListSongketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSongketBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel = ViewModelProvider(this, ViewModelFactory(Injection.provideRepository())).get(ListSongketViewModel::class.java)
//        adapter = ListSongketAdapter()

        val recyclerView: RecyclerView = binding.rvListSongket
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.data.observe(this, Observer { newData ->
            adapter.setData(newData)
        })
//        viewModel.getListSongket()
    }

}
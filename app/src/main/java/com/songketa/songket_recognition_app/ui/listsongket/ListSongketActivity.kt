package com.songketa.songket_recognition_app.ui.listsongket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.songketa.songket_recognition_app.adapter.ListSongketAdapter
import com.songketa.songket_recognition_app.databinding.ActivityListSongketBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.data.Result
import com.songketa.songket_recognition_app.data.response.DatasetItem

class ListSongketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListSongketBinding
    private val viewModel by viewModels<ListSongketViewModel>{
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSongketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getStory()

        val layoutManager = LinearLayoutManager(this)
        binding.rvListSongket.layoutManager = layoutManager
    }

    private fun songketAdapter(listSongket: List<DatasetItem>) {
        val adapter = ListSongketAdapter(this@ListSongketActivity)
        adapter.submitList(listSongket)
        binding.rvListSongket.adapter = adapter
    }
    private fun getStory(){
        viewModel.getStories().observe(this){songket ->
            if(songket != null){
                when(songket){
                    is Result.Loading ->{
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        val listStory = songket.data
                        songketAdapter(listStory)
                    }
                    is Result.Error -> {
                        showLoading(false)
                        showToast(songket.error)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
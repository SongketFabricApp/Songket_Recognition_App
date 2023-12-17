package com.songketa.songket_recognition_app.ui.listsongket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.songketa.songket_recognition_app.adapter.ListSongketAdapter
import com.songketa.songket_recognition_app.databinding.ActivityListSongketBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.data.Result
import com.songketa.songket_recognition_app.data.database.SongketEntity
import com.songketa.songket_recognition_app.ui.bookmark.BookmarkViewModel

class ListSongketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListSongketBinding
    private var isIconChange = false
    private val viewModel by viewModels<ListSongketViewModel>{
        ViewModelFactory.getInstance(this)
    }

    private lateinit var listSongketAdapter: ListSongketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSongketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getStory()

        val layoutManager = LinearLayoutManager(this)
        binding.rvListSongket.layoutManager = layoutManager
        setupSearchView()

        listSongketAdapter = ListSongketAdapter(
            context = this,
            onBookmarkClick = { songket ->
                // Implement the logic you want to perform on bookmark click
            },
            onItemClickListener = { songket ->
                // Implement the logic you want to perform on item click
            },
            bookmarkViewModel = BookmarkViewModel(application)
        )

        binding.rvListSongket.adapter = listSongketAdapter
    }

    private fun setupSearchView() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { performSearch(it) }
                return false
            }
        })
    }

    private fun performSearch(query: String) {
        viewModel.searchSongket(query).observe(this@ListSongketActivity) { result ->
            when (result) {
                is Result.Loading -> {
                    // Tampilkan indikator loading jika diperlukan
                }
                is Result.Success -> {
                    // Convert DatasetItem to SongketEntity
                    val songketEntities = result.data.map { datasetItem ->
                        SongketEntity(
                            idfabric = datasetItem.idfabric,
                            fabricname = datasetItem.fabricname,
                            origin = datasetItem.origin,
                            imgUrl = datasetItem.imgUrl
                        )
                    }
                    listSongketAdapter.submitList(songketEntities)
                }
                is Result.Error -> {
                    showToast(result.error)
                }
            }
        }
    }

    private fun getStory() {
        viewModel.getStories().observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    val listDatasetItem = result.data
                    val listSongketEntity = listDatasetItem.map { datasetItem ->
                        // Convert DatasetItem to SongketEntity
                        SongketEntity(
                            idfabric = datasetItem.idfabric,
                            fabricname = datasetItem.fabricname,
                            origin = datasetItem.origin,
                            imgUrl = datasetItem.imgUrl
                        )
                    }
                    songketAdapter(listSongketEntity)
                }
                is Result.Error -> {
                    showLoading(false)
                    showToast(result.error)
                }
            }
        }
    }

    private fun songketAdapter(listSongket: List<SongketEntity>) {
        // Update data pada adapter
        listSongketAdapter.submitList(listSongket)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

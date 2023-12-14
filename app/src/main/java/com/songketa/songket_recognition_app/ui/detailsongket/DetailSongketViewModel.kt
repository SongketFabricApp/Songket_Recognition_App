package com.songketa.songket_recognition_app.ui.detailsongket

import androidx.lifecycle.ViewModel
import com.songketa.songket_recognition_app.data.Repository

class DetailSongketViewModel(private val repository: Repository) : ViewModel() {

    fun getDetailStory(id : String) = repository.getDetailStory(id)

}
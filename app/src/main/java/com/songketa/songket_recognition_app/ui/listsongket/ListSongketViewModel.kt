package com.songketa.songket_recognition_app.ui.listsongket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.data.response.DatasetItem
import kotlinx.coroutines.launch

class ListSongketViewModel(private val repository: Repository) : ViewModel() {
    private val _data = MutableLiveData<List<DatasetItem>>()
    val data: LiveData<List<DatasetItem>> get() = _data

    fun fetchData() {
        viewModelScope.launch {
            _data.value = repository.getListSongket()
        }
    }
}

package com.songketa.songket_recognition_app.ui.listsongket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.data.model.User
import kotlinx.coroutines.launch

class ListSongketViewModel(private val repository: Repository) : ViewModel() {
    fun getStories() = repository.getSongket()

    fun getSession(): LiveData<User> {
        return repository.getSession().asLiveData()
    }


//    private val _data = MutableLiveData<List<DatasetItem>>()
//    val data: LiveData<List<DatasetItem>> get() = _data

//    fun getListSongket() {
//        viewModelScope.launch {
//            _data.value = repository.getListSongket()
//        }
//    }
}

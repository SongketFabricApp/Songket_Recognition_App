package com.songketa.songket_recognition_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.data.model.Menu
import com.songketa.songket_recognition_app.data.model.User

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _menuList = MutableLiveData<List<Menu>>()
    val menuList: LiveData<List<Menu>> get() = _menuList
    fun getMenu() = repository.getMenuData()
}
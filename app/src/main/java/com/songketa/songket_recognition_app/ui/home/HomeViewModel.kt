package com.songketa.songket_recognition_app.ui.home

import androidx.lifecycle.ViewModel
import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.data.model.Menu

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun getItems(): List<Menu> {
        return repository.getMenuData()
    }

    fun getItemById(itemId: Int): Menu? {
        return repository.getMenuById(itemId)
    }
}
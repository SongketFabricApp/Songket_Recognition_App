package com.songketa.songket_recognition_app.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.data.model.User

class WelcomeViewModel (private val repository: Repository) : ViewModel() {
    fun getSession(): LiveData<User> {
        return repository.getSession().asLiveData()
    }
}
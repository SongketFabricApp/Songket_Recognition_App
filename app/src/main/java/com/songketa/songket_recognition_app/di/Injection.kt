package com.songketa.songket_recognition_app.di

import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.data.api.ApiConfig

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}

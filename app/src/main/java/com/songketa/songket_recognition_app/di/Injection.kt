package com.songketa.songket_recognition_app.di

import com.songketa.songket_recognition_app.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}
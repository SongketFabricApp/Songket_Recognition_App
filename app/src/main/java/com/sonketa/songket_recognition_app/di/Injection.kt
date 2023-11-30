package com.sonketa.songket_recognition_app.di

import com.sonketa.songket_recognition_app.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}
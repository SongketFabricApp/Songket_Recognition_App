package com.songketa.songket_recognition_app.di

import android.content.Context
import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.data.api.ApiConfig
import com.songketa.songket_recognition_app.data.api.ml.ApiMlConfig
import com.songketa.songket_recognition_app.utils.UserPreferences
import com.songketa.songket_recognition_app.utils.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        val apiMlService = ApiMlConfig.getApiMlService()
        return Repository.getInstance(pref,apiService,apiMlService)
    }
}

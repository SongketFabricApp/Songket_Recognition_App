package com.songketa.songket_recognition_app.data

import com.songketa.songket_recognition_app.data.api.ApiService
import com.songketa.songket_recognition_app.data.response.DatasetItem

class Repository (private val apiService : ApiService){
    suspend fun getListSongket():List<DatasetItem>{
        return apiService.getListSongket()
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(apiService: ApiService): Repository =
            instance ?: synchronized(this) {
                Repository(apiService).apply {
                    instance = this
                }
            }
    }
}
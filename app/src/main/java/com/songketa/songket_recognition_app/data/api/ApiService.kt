package com.songketa.songket_recognition_app.data.api

import com.songketa.songket_recognition_app.data.response.DatasetItem
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("")
    fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Call<DatasetItem>

    @GET("dataset")
    suspend fun getListSongket(): List<DatasetItem>
}
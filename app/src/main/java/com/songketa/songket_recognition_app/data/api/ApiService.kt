package com.songketa.songket_recognition_app.data.api

import com.songketa.songket_recognition_app.data.response.DatasetItem
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("")
    fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Call<DatasetItem>
}
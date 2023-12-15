package com.songketa.songket_recognition_app.data.api.ml

import com.songketa.songket_recognition_app.data.response.MachineLearningResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiMlService {
    @Multipart
    @POST("predict")
    suspend fun postImage(
        @Part file: MultipartBody.Part
    ): MachineLearningResponse
}
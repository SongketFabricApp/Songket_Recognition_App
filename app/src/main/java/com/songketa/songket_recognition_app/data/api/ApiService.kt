package com.songketa.songket_recognition_app.data.api

import com.songketa.songket_recognition_app.data.response.DetailSongketResponse
import com.songketa.songket_recognition_app.data.response.LoginResponse
import com.songketa.songket_recognition_app.data.response.MachineLearningResponse
import com.songketa.songket_recognition_app.data.response.PostResponse
import com.songketa.songket_recognition_app.data.response.RegisterResponse
import com.songketa.songket_recognition_app.data.response.SongketDataResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("dataset")
    suspend fun getListSongket(
    ): SongketDataResponse

    @GET("dataset/{id}")
    suspend fun getDetailStory(
        @Path("id") id: String
    ): DetailSongketResponse

//    @Multipart
//    @POST("predict")
//    suspend fun postImage(
//        @Part file: MultipartBody.Part,
//    ): MachineLearningResponse
}
package com.songketa.songket_recognition_app.data.api

import com.songketa.songket_recognition_app.data.response.DatasetItem
import com.songketa.songket_recognition_app.data.response.LoginResponse
import com.songketa.songket_recognition_app.data.response.RegisterResponse
import com.songketa.songket_recognition_app.data.response.SongketResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
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
    ): SongketResponse

//    @GET("stories")
//    suspend fun getListSongket(
//    ): SongketResponse

//    @Multipart
//    @POST("")
//    fun uploadImage(
//        @Part file: MultipartBody.Part
//    ): Call<DatasetItem>

//    @GET("dataset")
//    suspend fun getListSongket(): List<SongketResponse>

//    @GET("stories/{id}")
//    suspend fun getDetailStory(
//        @Path("id") id: String
//    ): DetailStoryResponse
}
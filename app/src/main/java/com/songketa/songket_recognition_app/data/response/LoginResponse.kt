package com.songketa.songket_recognition_app.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @field:SerializedName("loginResult")
    val loginResult: LoginResult,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
) : Parcelable

@Parcelize
data class LoginResult(

    @field:SerializedName("username")
    val name: String,

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("token")
    val token: String

) : Parcelable

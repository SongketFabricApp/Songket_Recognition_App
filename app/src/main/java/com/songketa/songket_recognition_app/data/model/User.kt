package com.songketa.songket_recognition_app.data.model

data class User(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)

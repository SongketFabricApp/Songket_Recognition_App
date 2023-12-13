package com.songketa.songket_recognition_app.data.model

data class User(
    val name: String,
    val email: String,
    val phone: String,
    val token: String,
    val isLogin: Boolean = false
)

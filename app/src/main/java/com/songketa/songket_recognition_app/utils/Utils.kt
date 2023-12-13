package com.songketa.songket_recognition_app.utils

import android.text.TextUtils
import android.util.Patterns


fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validateMinLength(password: String): Boolean {
    return !TextUtils.isEmpty(password) && password.length >= Constant.MIN_LENGTH_PASSWORD
}




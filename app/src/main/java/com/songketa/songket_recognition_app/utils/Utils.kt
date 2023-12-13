package com.songketa.songket_recognition_app.utils

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity


fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validateMinLength(password: String): Boolean {
    return !TextUtils.isEmpty(password) && password.length >= Constant.MIN_LENGTH_PASSWORD
}


fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun softkeyboard(context: Context, view: View) {
    (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view.windowToken, 0)
}



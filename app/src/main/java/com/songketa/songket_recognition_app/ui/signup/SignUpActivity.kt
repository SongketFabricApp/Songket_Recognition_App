package com.songketa.songket_recognition_app.ui.signup

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.songketa.songket_recognition_app.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val tvSignInHere = findViewById<TextView>(R.id.tv_signin_here)
        tvSignInHere.paintFlags = tvSignInHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG

    }
}
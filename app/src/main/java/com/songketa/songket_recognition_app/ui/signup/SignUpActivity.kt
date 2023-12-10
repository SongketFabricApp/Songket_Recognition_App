package com.songketa.songket_recognition_app.ui.signup

import android.graphics.Paint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.viewModels
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.databinding.ActivitySignUpBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private val viewModel by viewModels<SignUpViewModel>{
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val tvSignInHere = findViewById<TextView>(R.id.tv_signin_here)
        tvSignInHere.paintFlags = tvSignInHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG

    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()

    }
}
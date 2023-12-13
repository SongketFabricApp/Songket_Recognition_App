package com.songketa.songket_recognition_app.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.songketa.songket_recognition_app.databinding.ActivitySplashScreenBinding
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity
import com.songketa.songket_recognition_app.utils.Constant

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        val countdownTimer = object : CountDownTimer(Constant.TIMER_1, Constant.TIMER_2) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                navigateToActivity()
            }
        }
        countdownTimer.start()
    }

    private fun navigateToActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}
package com.songketa.songket_recognition_app.ui.splashscreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
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
        playAnimation()

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

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -30f, 30f).apply {
            duration = Constant.SPLASH_SCREEN_TIMER
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

}
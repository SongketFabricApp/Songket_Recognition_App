package com.songketa.songket_recognition_app.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Paint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.songketa.songket_recognition_app.MainActivity
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.databinding.ActivitySignUpBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.ui.signin.SignInActivity
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity
import com.songketa.songket_recognition_app.data.Result

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>{
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSigninHere.setOnClickListener {
            navigateToSignInActivity()
        }

        val tvSignInHere = binding.tvSigninHere
        tvSignInHere.paintFlags = tvSignInHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//
//        val tvSignInHere = findViewById<TextView>(R.id.tv_signin_here)
//        tvSignInHere.paintFlags = tvSignInHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        playAnimation()
        setupView()
        setupAction()
    }

    override fun onBackPressed() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
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
    private fun setupAction() {
        binding.tvSigninHere.setOnClickListener{
            navigateToSignInActivity()
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.emailField.text.toString()
            val name = binding.usernameField.text.toString()
            val phone = binding.phoneField.text.toString()
            val pass = binding.passwordField.text.toString()

            val title = getString(R.string.head_notif)
            val message = getString(R.string.register_succes_notif)
            val next = getString(R.string.next_notif)

            viewModel.register(email = email, name = name, phone = phone,password = pass).observe(this){result  ->
                when(result){
                    is Result.Loading ->{
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        showSuccessDialog(title, message, next)
                    }
                    is Result.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }

        }
    }
    private fun showSuccessDialog(title: String, message: String, next: String) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(next) { _, _ ->
                val intent = Intent(context, SignInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            create()
            show()
        }
    }

    private fun playAnimation() {
        val titleregister = ObjectAnimator.ofFloat(binding.tvSignup, View.ALPHA, 1f).setDuration(200)
        val titleusername = ObjectAnimator.ofFloat(binding.tvUsername, View.ALPHA, 1f).setDuration(200)
        val usernameedit = ObjectAnimator.ofFloat(binding.tilUsername, View.ALPHA, 1f).setDuration(200)
        val titlemail = ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(200)
        val emailedit = ObjectAnimator.ofFloat(binding.tilEmail, View.ALPHA, 1f).setDuration(200)
        val titlphone = ObjectAnimator.ofFloat(binding.tvPhone, View.ALPHA, 1f).setDuration(200)
        val phoneedit = ObjectAnimator.ofFloat(binding.tilPhone, View.ALPHA, 1f).setDuration(200)
        val titlepass = ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(200)
        val passedit = ObjectAnimator.ofFloat(binding.tilPassword, View.ALPHA, 1f).setDuration(200)
        val titlepassconfirm = ObjectAnimator.ofFloat(binding.tilConfirmPassword, View.ALPHA, 1f).setDuration(200)
        val passeditconfirm = ObjectAnimator.ofFloat(binding.confirmPasswordField, View.ALPHA, 1f).setDuration(200)
        val login = ObjectAnimator.ofFloat(binding.btnSignUp, View.ALPHA, 1f).setDuration(200)

        AnimatorSet().apply {
            playSequentially(titleregister,titleusername,usernameedit,titlemail,
                emailedit,titlphone,phoneedit,titlepass,passedit,titlepassconfirm,passeditconfirm,login)
            start()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}
package com.songketa.songket_recognition_app.ui.signup

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

        val tvSignInHere = findViewById<TextView>(R.id.tv_signin_here)
        tvSignInHere.paintFlags = tvSignInHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        setupView()
        setupAction()
        setBind()
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
//        binding.seePassword.setOnCheckedChangeListener { _, isChecked ->
//            binding.passwordEditText.transformationMethod = if (isChecked) {
//                HideReturnsTransformationMethod.getInstance()
//            } else {
//                PasswordTransformationMethod.getInstance()
//            }
//            binding.passwordEditText.text?.let { binding.passwordEditText.setSelection(it.length) }
//        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.emailField.text.toString()
            val name = binding.usernameField.text.toString()
            val phone = binding.phoneField.text.toString()
            val pass = binding.passwordField.text.toString()

            val title = getString(R.string.head_notif)
            val message = getString(R.string.register_succes_notif)
            val next = getString(R.string.next_notif)

            viewModel.register(email = email, name = name, phone = phone,password = pass).observe(this){hoho ->
                when(hoho){
                    is Result.Loading ->{
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        AlertDialog.Builder(this).apply {
                            setTitle(title)
                            setMessage(message)
                            setPositiveButton(next) { _, _ ->
                                finish()
                            }
                            create()
                            show()
                        }
                    }
                    is Result.Error -> {
                        showLoading(false)
                        showToast(hoho.error)
                    }
                }
            }

        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setBind(){
        binding.tvSigninHere.setOnClickListener{
            navigateToSignInActivity()
        }
    }

    private fun navigateToSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}
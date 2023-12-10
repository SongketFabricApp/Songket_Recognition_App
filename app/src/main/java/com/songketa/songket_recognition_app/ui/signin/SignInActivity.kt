package com.songketa.songket_recognition_app.ui.signin

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
import com.songketa.songket_recognition_app.data.model.User
import com.songketa.songket_recognition_app.databinding.ActivitySignInBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.data.Result
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    private val viewModel by viewModels<SignInViewModel>{
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    private fun setupAction(){
        binding.seePassword.setOnCheckedChangeListener { _, isChecked ->
            binding.passwordEditText.transformationMethod = if (isChecked) {
                HideReturnsTransformationMethod.getInstance()
            } else {
                PasswordTransformationMethod.getInstance()
            }
            binding.passwordEditText.text?.let { binding.passwordEditText.setSelection(it.length) }
        }

        binding.signInButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()

            val title = getString(R.string.head_notif)
            val message = getString(R.string.login_succes_notif)
            val next = getString(R.string.next_notif)

            viewModel.login(email = email, password = pass).observe(this){hoho ->
                when(hoho){
                    is Result.Loading ->{
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        val user = User(
                            token = hoho.data.loginResult.token,
                            email = email,
                        )
                        viewModel.saveSession(user)

                        AlertDialog.Builder(this).apply {
                            setTitle(title)
                            setMessage(message)
                            setPositiveButton(next) { _, _ ->
                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
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
//            if (email.equals(Email)&& pass.equals(Password)){
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//            else{
//                AlertDialog.Builder(this).apply {
//                    setTitle(title)
//                    setMessage("Login Failed")
//                    setPositiveButton("hoho") { _, _ ->
//                        val intent = Intent(context, SignInActivity::class.java)
//                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                        startActivity(intent)
//                        finish()
//                    }
//                    create()
//                    show()
//                }
//            }


        }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
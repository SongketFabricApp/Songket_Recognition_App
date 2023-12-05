package com.songketa.songket_recognition_app.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.songketa.songket_recognition_app.MainActivity
import com.songketa.songket_recognition_app.databinding.ActivitySignInBinding
import com.songketa.songket_recognition_app.ui.home.HomeActivity
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity
import com.songketa.songket_recognition_app.utils.Constant
import com.songketa.songket_recognition_app.utils.Constant.Email
import com.songketa.songket_recognition_app.utils.Constant.Password

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction(){
        binding.signInButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            if (email.equals(Email)&& pass.equals(Password)){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                AlertDialog.Builder(this).apply {
                    setTitle(title)
                    setMessage("Login Failed")
                    setPositiveButton("hoho") { _, _ ->
                        val intent = Intent(context, SignInActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                    create()
                    show()
                }
            }


        }
    }

}
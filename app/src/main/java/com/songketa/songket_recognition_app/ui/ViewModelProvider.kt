package com.songketa.songket_recognition_app.ui

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.di.Injection
import com.songketa.songket_recognition_app.ui.home.HomeViewModel
import com.songketa.songket_recognition_app.ui.listsongket.ListSongketViewModel
import com.songketa.songket_recognition_app.ui.signin.SignInViewModel
import com.songketa.songket_recognition_app.ui.signup.SignUpViewModel
import com.songketa.songket_recognition_app.ui.welcome.WelcomeViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListSongketViewModel::class.java)) {
            return ListSongketViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (instance == null) {
                synchronized(ViewModelFactory::class.java) {
                    instance = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return instance as ViewModelFactory
        }
//        fun getInstance(requireActivity: FragmentActivity): ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(Injection.provideRepository(context))
//            }.also { instance = it }
    }

}

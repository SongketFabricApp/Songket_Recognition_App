package com.songketa.songket_recognition_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.songketa.songket_recognition_app.data.api.ApiConfig
import com.songketa.songket_recognition_app.data.api.ApiService
import com.songketa.songket_recognition_app.data.model.Menu
import com.songketa.songket_recognition_app.data.model.MenuItem
import com.songketa.songket_recognition_app.data.model.User
import com.songketa.songket_recognition_app.data.response.LoginResponse
import com.songketa.songket_recognition_app.data.response.SongketResponse
import com.songketa.songket_recognition_app.utils.UserPreferences
import retrofit2.HttpException

class Repository private constructor(private val userPreference: UserPreferences, private val apiService: ApiService){

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            if(response.error ==false){
                val user = User(
                    email = email,
                    token = response.loginResult.token,
                    isLogin = true
                )
                ApiConfig.token = response.message
                userPreference.saveSession(user)
                emit(Result.Success(response))
            }else{
                emit(Result.Error(response.message))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("Login Failed: $errorMessage"))
        } catch (e: Exception){
            emit(Result.Error("Something Error"))
        }
    }



    suspend fun getListSongket(): List<SongketResponse> {
        return apiService.getListSongket()
    }

    fun getMenuData(): List<Menu> {
        return MenuItem.menu
    }

    suspend fun saveSession(user: User) {
        userPreference.saveSession(user)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(userPreference: UserPreferences,apiService: ApiService): Repository =
            instance ?: synchronized(this) {
                Repository(userPreference,apiService).apply {
                    instance = this
                }
            }
    }
}
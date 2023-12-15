package com.songketa.songket_recognition_app.ui.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.songketa.songket_recognition_app.data.Repository
import com.songketa.songket_recognition_app.data.response.MachineLearningResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.songketa.songket_recognition_app.data.Result
import java.io.File

class CameraViewModel (private val repository: Repository): ViewModel(){
    fun postImage(file: MultipartBody.Part) = repository.postImage(file)


}
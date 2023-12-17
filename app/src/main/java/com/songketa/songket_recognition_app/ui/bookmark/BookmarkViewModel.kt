package com.songketa.songket_recognition_app.ui.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.songketa.songket_recognition_app.data.database.SongketEntity
import com.songketa.songket_recognition_app.data.SongketRepository

class BookmarkViewModel (application: Application) : AndroidViewModel(application) {
    private val songketRepository: SongketRepository = SongketRepository(application)

    fun insert(users: SongketEntity) {
        songketRepository.insertSongket(users)
    }

    fun delete(users: SongketEntity) {
        songketRepository.deleteSongket(users)
    }

    fun getSongket(): LiveData<List<SongketEntity>> {
        return songketRepository.getSongket()
    }
}
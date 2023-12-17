package com.songketa.songket_recognition_app.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SongketDao {
    @Query("SELECT * FROM songket")
    fun getSongket(): LiveData<List<SongketEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSongket(news: SongketEntity)

    @Update
    fun updateSongket(news: SongketEntity)

    @Delete
    fun deleteSongket(news: SongketEntity)

}

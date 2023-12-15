package com.songketa.songket_recognition_app.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "songket")
@Parcelize
class SongketEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "fabricname")
    var fabricname: String = "",

): Parcelable
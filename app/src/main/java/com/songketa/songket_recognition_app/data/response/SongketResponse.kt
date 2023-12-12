package com.songketa.songket_recognition_app.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SongketResponse(

	@field:SerializedName("dataset")
	val listStory: List<DatasetItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String

) : Parcelable

@Parcelize
data class DatasetItem(

	@field:SerializedName("img_url")
	val imgUrl: String,

	@field:SerializedName("origin")
	val origin: String,

	@field:SerializedName("pattern")
	val pattern: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("idfabric")
	val idfabric: String,

	@field:SerializedName("fabricname")
	val fabricname: String
) : Parcelable

//@Parcelize
//data class ListStoryItem(
//
//	@field:SerializedName("photoUrl")
//	val photoUrl: String,
//
//	@field:SerializedName("createdAt")
//	val createdAt: String,
//
//	@field:SerializedName("name")
//	val name: String,
//
//	@field:SerializedName("description")
//	val description: String,
//
//	@field:SerializedName("lon")
//	val lon: Double? = null,
//
//	@field:SerializedName("id")
//	val id: String,
//
//	@field:SerializedName("lat")
//	val lat: Double? = null
//) : Parcelable

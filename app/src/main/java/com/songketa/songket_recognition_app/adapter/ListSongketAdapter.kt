package com.songketa.songket_recognition_app.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.data.database.SongketEntity
import com.songketa.songket_recognition_app.databinding.SongketListItemBinding
import com.songketa.songket_recognition_app.ui.bookmark.BookmarkViewModel
import com.songketa.songket_recognition_app.ui.detailsongket.DetailSongketActivity

class ListSongketAdapter(
    private val context: Context,
    private val onBookmarkClick: (SongketEntity) -> Unit,
    private val onItemClickListener: (SongketEntity) -> Unit,
    private val bookmarkViewModel: BookmarkViewModel
) : ListAdapter<SongketEntity, ListSongketAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private val bookmarkStatusMap = mutableMapOf<String, Boolean>()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            SongketListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songket = getItem(position)
        holder.bind(songket)

        // Set image resource based on the bookmark status
        val isBookmarked =
            bookmarkStatusMap[songket.idfabric] ?: sharedPreferences.getBoolean(songket.idfabric, false)
        val bookmarkImageResource = if (isBookmarked) {
            R.drawable.ic_bookmark_red
        } else {
            R.drawable.ic_bookmark
        }
        holder.binding.ivVectorDrawable.setImageResource(bookmarkImageResource)
        holder.itemView.setOnClickListener {
            val moveDataUserIntent = Intent(holder.itemView.context, DetailSongketActivity::class.java)
            moveDataUserIntent.putExtra(DetailSongketActivity.ID, songket.idfabric)
            holder.itemView.context.startActivity(moveDataUserIntent)
        }


        holder.binding.ivVectorDrawable.setOnClickListener {
            val updatedBookmarkStatus =
                !(bookmarkStatusMap[songket.idfabric] ?: sharedPreferences.getBoolean(songket.idfabric, false))
            bookmarkStatusMap[songket.idfabric] = updatedBookmarkStatus
            sharedPreferences.edit {
                putBoolean(songket.idfabric, updatedBookmarkStatus)
            }
            val updatedBookmarkImageResource = if (updatedBookmarkStatus) {
                R.drawable.ic_bookmark_red
            } else {
                R.drawable.ic_bookmark
            }
            holder.binding.ivVectorDrawable.setImageResource(updatedBookmarkImageResource)

            if (updatedBookmarkStatus) {
                bookmarkViewModel.insert(songket)
            } else {
                bookmarkViewModel.delete(songket)

            }
        }
    }

    class MyViewHolder(val binding: SongketListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(songket: SongketEntity) {
            binding.tvSongketName.text = songket.fabricname
            binding.tvAsalSogket.text = songket.origin
            Glide.with(binding.root.context).load(songket.imgUrl)
                .into(binding.ivSongketImage)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SongketEntity>() {
            override fun areItemsTheSame(oldItem: SongketEntity, newItem: SongketEntity): Boolean {
                return oldItem.idfabric == newItem.idfabric
            }

            override fun areContentsTheSame(
                oldItem: SongketEntity,
                newItem: SongketEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}


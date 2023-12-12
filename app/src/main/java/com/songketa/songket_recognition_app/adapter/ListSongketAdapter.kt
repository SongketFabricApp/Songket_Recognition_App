package com.songketa.songket_recognition_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.songketa.songket_recognition_app.data.response.ListStoryItem
import com.songketa.songket_recognition_app.databinding.SongketListItemBinding
import com.songketa.songket_recognition_app.ui.detailsongket.DetailSongketActivity

class ListSongketAdapter(private val context: Context) : ListAdapter<ListStoryItem, ListSongketAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SongketListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songket = getItem(position)
        holder.bind(songket)
        holder.itemView.setOnClickListener {
            val moveDataUserIntent = Intent(holder.itemView.context, DetailSongketActivity::class.java)
            moveDataUserIntent.putExtra(DetailSongketActivity.ID, songket.id)
            holder.itemView.context.startActivity(moveDataUserIntent)
        }

    }
    class MyViewHolder(val binding: SongketListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(songket: ListStoryItem){
            binding.tvSongketName.text = songket.name
//            binding.tvAsalSogket.text = songket.origin
            Glide.with(binding.root.context).load(songket.photoUrl)
                .into(binding.ivSongketImage)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

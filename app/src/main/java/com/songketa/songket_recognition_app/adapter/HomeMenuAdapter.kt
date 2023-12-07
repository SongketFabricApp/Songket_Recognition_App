package com.songketa.songket_recognition_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.songketa.songket_recognition_app.data.model.Menu
import com.songketa.songket_recognition_app.data.response.DatasetItem
import com.songketa.songket_recognition_app.databinding.SongketListItemBinding
import com.songketa.songket_recognition_app.ui.camera.CameraActivity
import com.songketa.songket_recognition_app.ui.detailsongket.DetailSongketActivity
import com.songketa.songket_recognition_app.ui.maps.MapsActivity
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity

class HomeMenuAdapter(private val context: Context) : ListAdapter<Menu, HomeMenuAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SongketListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menu = getItem(position)
        if (menu.id == 1){
            holder.bind(menu)
            holder.itemView.setOnClickListener {
                val moveDataUserIntent = Intent(holder.itemView.context, CameraActivity::class.java)
                holder.itemView.context.startActivity(moveDataUserIntent)
            }
        }
        else if (menu.id == 2){
            holder.bind(menu)
            holder.itemView.setOnClickListener {
                val moveDataUserIntent = Intent(holder.itemView.context, MapsActivity::class.java)
                holder.itemView.context.startActivity(moveDataUserIntent)
            }
        }
        else if (menu.id == 3){
            holder.bind(menu)
            holder.itemView.setOnClickListener {
                val moveDataUserIntent = Intent(holder.itemView.context, WelcomeActivity::class.java)
                holder.itemView.context.startActivity(moveDataUserIntent)
            }
        }
    }
    class MyViewHolder(val binding: SongketListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu){
            binding.tvSongketName.text = menu.name
            Glide.with(binding.root.context).load(menu.image)
                .into(binding.ivSongketImage)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem == newItem
            }
        }
    }
}
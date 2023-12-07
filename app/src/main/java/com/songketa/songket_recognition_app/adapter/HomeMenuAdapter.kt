package com.songketa.songket_recognition_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.data.model.Menu
import com.songketa.songket_recognition_app.data.response.DatasetItem
import com.songketa.songket_recognition_app.databinding.MenuHomeItemBinding
import com.songketa.songket_recognition_app.databinding.SongketHomeItemBinding
import com.songketa.songket_recognition_app.databinding.SongketListItemBinding
import com.songketa.songket_recognition_app.ui.camera.CameraActivity
import com.songketa.songket_recognition_app.ui.detailsongket.DetailSongketActivity
import com.songketa.songket_recognition_app.ui.maps.MapsActivity
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity

class HomeMenuAdapter(
    private val items: List<Menu>,
    private val onItemClick: (Menu) -> Unit
) : RecyclerView.Adapter<HomeMenuAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuAdapter.MyViewHolder {
        val binding = MenuHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeMenuAdapter.MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }
    override fun getItemCount(): Int = items.size
    class MyViewHolder(val binding: MenuHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu) {
            binding.tvName.text = menu.name
            Glide.with(binding.root.context).load(menu.image)
                .into(binding.ivMenu)
        }
    }
}

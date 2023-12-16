package com.songketa.songket_recognition_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.songketa.songket_recognition_app.data.response.DatasetInfo
import com.songketa.songket_recognition_app.data.response.DatasetItem
import com.songketa.songket_recognition_app.databinding.DialogSongketBinding
import com.songketa.songket_recognition_app.databinding.SongketHomeItemBinding
import com.songketa.songket_recognition_app.ui.detailsongket.DetailSongketActivity

class AlertSongketAdapter(private val context: Context) : ListAdapter<DatasetInfo, AlertSongketAdapter.MyViewHolder>(
    AlertSongketAdapter.DIFF_CALLBACK
)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DialogSongketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songket = getItem(position)
        holder.bind(songket)
        holder.binding.btnCheck.setOnClickListener {
            val moveDataUserIntent = Intent(holder.binding.btnCheck.context, DetailSongketActivity::class.java)
            moveDataUserIntent.putExtra(DetailSongketActivity.ID, songket.idfabric)
            holder.binding.btnCheck.context.startActivity(moveDataUserIntent)
        }

    }
    class MyViewHolder(val binding: DialogSongketBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(songket: DatasetInfo){
            binding.tvFabricname.text = songket.fabricname
            Glide.with(binding.root.context).load(songket.imgUrl)
                .into(binding.ivItemDialogSongketPicture)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DatasetInfo>() {
            override fun areItemsTheSame(oldItem: DatasetInfo, newItem: DatasetInfo): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DatasetInfo, newItem: DatasetInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
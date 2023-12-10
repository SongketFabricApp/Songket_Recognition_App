package com.songketa.songket_recognition_app.ui.listsongket

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.songketa.songket_recognition_app.adapter.ListSongketAdapter.Companion.DIFF_CALLBACK
import com.songketa.songket_recognition_app.data.response.DatasetItem
import com.songketa.songket_recognition_app.databinding.SongketListItemBinding
import com.songketa.songket_recognition_app.ui.detailsongket.DetailSongketActivity

class ListSongketAdapter(private val context: Context) :
    ListAdapter<DatasetItem,ListSongketAdapter.MyViewHolder>(
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
            moveDataUserIntent.putExtra(DetailSongketActivity.ID, songket.idfabric)
            holder.itemView.context.startActivity(moveDataUserIntent)
        }

    }
    class MyViewHolder(val binding: SongketListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(songket: DatasetItem){
            binding.tvSongketName.text = songket.fabricname
            binding.tvAsalSogket.text = songket.origin
            Glide.with(binding.root.context).load(songket.imgUrl)
                .into(binding.ivSongketImage)
        }
    }
//    private var dataList: List<DatasetItem> = emptyList()
//
//    // Inner class ViewHolder
//    class ViewHolder(private val binding: SongketListItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        // Deklarasikan komponen tampilan di sini (contoh: TextView, ImageView, dll.)
//
//        fun bind(songket: DatasetItem) {
//            with(binding) {
//                Glide.with(itemView.context)
//                    .load(songket.imgUrl)
//                    .centerCrop()
//                    .into(ivSongketImage)
//                tvSongketName.text = songket.fabricname
//                tvAsalSogket.text = songket.origin
//            }
//        }
//    }
//
//    // Setter untuk mengatur data
//    fun setData(data: List<DatasetItem>) {
//        dataList = data
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        // Inflate layout item untuk RecyclerView
//        val view = SongketListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        // Mengembalikan instance ViewHolder yang baru dibuat
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // Implementasi untuk mengatur data pada item
//        // Gunakan holder.itemView untuk mendapatkan referensi ke elemen-elemen di dalam item
//        // Contoh: holder.itemView.findViewById<TextView>(R.id.textView).text = dataList[position].namaField
//        holder.bind(dataList[position])
//    }
//
//    override fun getItemCount(): Int {
//        // Mengembalikan jumlah item dalam dataset
//        return dataList.size
//    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DatasetItem>() {
            override fun areItemsTheSame(oldItem: DatasetItem, newItem: DatasetItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DatasetItem, newItem: DatasetItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}

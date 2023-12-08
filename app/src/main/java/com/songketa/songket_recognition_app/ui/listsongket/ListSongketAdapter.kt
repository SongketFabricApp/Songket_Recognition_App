package com.songketa.songket_recognition_app.ui.listsongket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.songketa.songket_recognition_app.data.response.DatasetItem
import com.songketa.songket_recognition_app.databinding.SongketListItemBinding

class ListSongketAdapter : RecyclerView.Adapter<ListSongketAdapter.ViewHolder>() {
    private var dataList: List<DatasetItem> = emptyList()

    // Inner class ViewHolder
    class ViewHolder(private val binding: SongketListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // Deklarasikan komponen tampilan di sini (contoh: TextView, ImageView, dll.)

        fun bind(songket: DatasetItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(songket.imgUrl)
                    .centerCrop()
                    .into(ivSongketImage)
                tvSongketName.text = songket.fabricname
                tvAsalSogket.text = songket.origin
            }
        }
    }

    // Setter untuk mengatur data
    fun setData(data: List<DatasetItem>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate layout item untuk RecyclerView
        val view = SongketListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // Mengembalikan instance ViewHolder yang baru dibuat
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Implementasi untuk mengatur data pada item
        // Gunakan holder.itemView untuk mendapatkan referensi ke elemen-elemen di dalam item
        // Contoh: holder.itemView.findViewById<TextView>(R.id.textView).text = dataList[position].namaField
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        // Mengembalikan jumlah item dalam dataset
        return dataList.size
    }

}

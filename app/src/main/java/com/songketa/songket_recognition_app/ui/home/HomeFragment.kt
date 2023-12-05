package com.songketa.songket_recognition_app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.databinding.ActivityHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        val layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        binding.rvSongket.layoutManager = layoutManager
        binding.rvMenu.layoutManager = layoutManager

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {

    }
}
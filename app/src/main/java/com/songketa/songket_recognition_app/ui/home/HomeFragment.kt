package com.songketa.songket_recognition_app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.adapter.HomeMenuAdapter
import com.songketa.songket_recognition_app.data.model.Menu
import com.songketa.songket_recognition_app.databinding.ActivityHomeBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeViewModel>{
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        binding.rvSongket.layoutManager = layoutManager
        binding.rvMenu.layoutManager = layoutManager

    }
    private fun storyAdapter(listMenu: List<Menu>) {
        val adapter = HomeMenuAdapter(requireContext())
        adapter.submitList(listMenu)
        binding.rvMenu.adapter = adapter
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
package com.songketa.songket_recognition_app.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.adapter.BookmarkAdapter
import com.songketa.songket_recognition_app.databinding.FragmentBookmarkBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.ui.detailsongket.DetailSongketActivity
import com.songketa.songket_recognition_app.ui.home.HomeFragment

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var viewModel: BookmarkViewModel
    private val bookmarkAdapter: BookmarkAdapter by lazy {
        BookmarkAdapter(
            context = requireContext(),
            onBookmarkClick = { /* handle bookmark click logic here */ }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val view = binding.root

        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvListSongket.layoutManager = linearLayoutManager
        binding.rvListSongket.adapter = bookmarkAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext()))
            .get(BookmarkViewModel::class.java)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.replaceFragment(
                    HomeFragment(),
                    R.id.frame_layout
                )
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        viewModel.getSongket().observe(viewLifecycleOwner, Observer {
            bookmarkAdapter.submitList(it)
        })

        bookmarkAdapter.onBookmarkClick = { songket ->
            // Handle bookmark click here
            val moveDataUserIntent =
                Intent(requireContext(), DetailSongketActivity::class.java)
            moveDataUserIntent.putExtra(DetailSongketActivity.ID, songket.idfabric)
            requireContext().startActivity(moveDataUserIntent)
        }
    }

    private fun FragmentManager.replaceFragment(fragment: Fragment, containerId: Int) {
        beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
}

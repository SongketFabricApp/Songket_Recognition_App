package com.songketa.songket_recognition_app.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.slider.Slider.OnChangeListener
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.adapter.HomeSongketAdapter
import com.songketa.songket_recognition_app.databinding.FragmentHomeBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.ui.camera.CameraFragment
import com.songketa.songket_recognition_app.ui.listsongket.ListSongketActivity
import com.songketa.songket_recognition_app.ui.maps.MapsActivity
import com.songketa.songket_recognition_app.data.Result
import com.songketa.songket_recognition_app.data.response.DatasetItem
import com.songketa.songket_recognition_app.ui.signin.SignInActivity
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewPager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8,0,8,0)
    }

    private val viewModel by viewModels<HomeViewModel>{
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSession()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        setupMenuClickListeners()

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvSongket.layoutManager = linearLayoutManager



        return view
    }

    private fun observeSession() {
        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireContext(), SignInActivity::class.java))
                requireActivity().finish()
            } else {
                getStory()
                binding.tvUsername.text = user.name
            }
        }
    }
    private fun setupMenuClickListeners() {
        binding.menuMaps.setOnClickListener {
            startActivity(Intent(requireContext(), MapsActivity::class.java))
        }
        binding.menuScan.setOnClickListener {
            onButtonClicked()
        }
        binding.menuList.setOnClickListener {
            startActivity(Intent(requireContext(), ListSongketActivity::class.java))
        }
    }

    private fun getStory(){
        viewModel.getSongket().observe(viewLifecycleOwner){ story ->
            if(story != null){
                when(story){
                    is Result.Loading ->{
                        showLoading(true)
                    }
                    is Result.Success -> {
                        val listStory = story.data
                        songketAdapter(listStory)
                    }
                    is Result.Error -> {
                        showLoading(false)
                        showToast(story.error)
                    }
                }
            }
        }
    }


    private fun songketAdapter(listStory: List<DatasetItem>) {
        val adapter = HomeSongketAdapter(requireContext())
        adapter.submitList(listStory)
        binding.rvSongket.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun onButtonClicked() {
        val tfFragment = CameraFragment()
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager?.beginTransaction() ?: return
        fragmentTransaction.replace(R.id.frame_layout, tfFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
    }
}
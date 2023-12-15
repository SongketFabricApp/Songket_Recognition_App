package com.songketa.songket_recognition_app.ui.home

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import android.Manifest
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


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val LOCATION_PERMISSION_REQUEST_CODE = 123

    private lateinit var viewPager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8, 0, 8, 0)
    }

    private val viewModel by viewModels<HomeViewModel> {
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

        binding.tvExplore.setOnClickListener {
            startActivity(Intent(requireContext(), ListSongketActivity::class.java))
        }

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
            if (checkLocationPermission()) {
                val keyword = "toko kain"
                val gmmIntentUri = Uri.parse("geo:0,0?q=$keyword")

                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")

                if (mapIntent.resolveActivity(requireContext().packageManager) != null) {
                    startActivity(mapIntent)
                } else {
                    showToast("Aplikasi Google Maps tidak terinstall.")
                }
            } else {
                requestLocationPermission()
            }
        }


        binding.menuScan.setOnClickListener {
            onButtonClicked()
        }

        binding.menuScan.setOnClickListener {
            onButtonClicked()
        }
        binding.menuList.setOnClickListener {
            startActivity(Intent(requireContext(), ListSongketActivity::class.java))
        }
        binding.menuArtikel.setOnClickListener {
            showToast("Features not available yet")
        }
    }

    private fun getStory() {
        viewModel.getSongket().observe(viewLifecycleOwner) { story ->
            if (story != null) {
                when (story) {
                    is Result.Loading -> {
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
        val randomList = listStory.shuffled()
        val limitedList = randomList.take(5)
        val adapter = HomeSongketAdapter(requireContext())
        adapter.submitList(limitedList)
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

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("Izin lokasi diberikan. Coba lagi.")
            } else {
                showToast("Izin lokasi ditolak.")
            }
        }
    }

    companion object {
    }
}
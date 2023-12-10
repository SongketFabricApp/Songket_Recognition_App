package com.songketa.songket_recognition_app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.adapter.HomeMenuAdapter
import com.songketa.songket_recognition_app.data.model.Menu
import com.songketa.songket_recognition_app.databinding.ActivityHomeBinding
import com.songketa.songket_recognition_app.databinding.FragmentHomeBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.ui.camera.CameraActivity
import com.songketa.songket_recognition_app.ui.camera.CameraFragment
import com.songketa.songket_recognition_app.ui.listsongket.ListSongketActivity
import com.songketa.songket_recognition_app.ui.maps.MapsActivity
import com.songketa.songket_recognition_app.ui.welcome.WelcomeActivity
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>{
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
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
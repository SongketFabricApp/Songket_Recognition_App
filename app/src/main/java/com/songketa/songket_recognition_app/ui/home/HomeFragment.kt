package com.songketa.songket_recognition_app.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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
    private val message = R.string.message
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val buttonMarketFinder = view.findViewById<AppCompatImageButton>(R.id.btn_market_finder)
        val buttonScan = view.findViewById<AppCompatImageButton>(R.id.btn_scan)
        val buttonSongketList = view.findViewById<AppCompatImageButton>(R.id.btn_songeket_list)
        val buttonArtikel = view.findViewById<AppCompatImageButton>(R.id.btn_artikel)
        val textView7 = view.findViewById<TextView>(R.id.textView7)

        buttonMarketFinder.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            startActivity(intent)
        }
        buttonScan.setOnClickListener {
            onButtonClicked()
        }
        buttonSongketList.setOnClickListener {
            val intent = Intent(activity, ListSongketActivity::class.java)
            startActivity(intent)
        }
        textView7.setOnClickListener {
            val intent = Intent(activity, ListSongketActivity::class.java)
            startActivity(intent)
        }
        buttonArtikel.setOnClickListener {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
        return view
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
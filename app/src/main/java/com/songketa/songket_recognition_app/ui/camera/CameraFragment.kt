package com.songketa.songket_recognition_app.ui.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.songketa.songket_recognition_app.databinding.FragmentCameraBinding

class CameraFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCamera.setOnClickListener(this)
        binding.btnGallery.setOnClickListener(this)
        binding.btnCheckOut.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnCamera.id -> {
                // Klik tombol kamera
            }
            binding.btnGallery.id -> {
                // Klik tombol galeri
            }
            binding.btnCheckOut.id -> {
                // Klik tombol check out
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

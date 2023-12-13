package com.songketa.songket_recognition_app.ui.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.databinding.FragmentCameraBinding
import com.songketa.songket_recognition_app.ui.home.HomeFragment
import com.songketa.songket_recognition_app.utils.CameraUtils
import java.io.File


class CameraFragment : Fragment(), View.OnClickListener{

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null
    private var CameraUtils = CameraUtils()



    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            binding.ivInputImage.setImageBitmap(result)
//            updateImageViewColor()
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myFile = CameraUtils.uriToFile(selectedImg, requireContext())
            getFile = myFile
            binding.ivInputImage.setImageURI(selectedImg)
        }
    }

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

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                replaceFragment(HomeFragment())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
    override fun onClick(v: View) {
        when (v.id) {
            binding.btnCamera.id -> {
                openCamera()
            }
            binding.btnGallery.id -> {
                openGallery()
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

    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.resolveActivity(requireActivity().packageManager)

            CameraUtils.createTempFile(requireActivity().application).also { tempFile ->
                val photoURI: Uri = FileProvider.getUriForFile(
                    requireContext(),
                    "${requireContext().packageName}.provider",
                    tempFile
                )

                currentPhotoPath = tempFile.absolutePath
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                launcherIntentCamera.launch(intent)
            }
        }
        else{
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
    }
        private fun openGallery() {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            val chooser = Intent.createChooser(intent, "Choose a Picture")
            launcherIntentGallery.launch(chooser)
        }
//    private fun updateImageViewColor() {
//        val themeColor: Int
//        if (isDarkTheme()) {
//            themeColor = ContextCompat.getColor(requireContext(), R.color.secondary)
//        } else {
//            themeColor = ContextCompat.getColor(requireContext(), R.color.secondary)
//        }
//        binding.ivInputImage.setColorFilter(themeColor)
//    }
    private fun isDarkTheme(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 123
    }
}

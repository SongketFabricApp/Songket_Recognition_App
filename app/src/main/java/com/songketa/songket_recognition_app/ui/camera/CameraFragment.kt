package com.songketa.songket_recognition_app.ui.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.songketa.songket_recognition_app.databinding.FragmentCameraBinding
import com.songketa.songket_recognition_app.utils.CameraUtils
import java.io.File


class CameraFragment : Fragment(), View.OnClickListener, LocationListener {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private lateinit var locationManager: LocationManager
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null
    private var CameraUtils = CameraUtils()
    private var lat: Float = -7.482145F
    private var lon: Float = 110.77294F



    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            binding.ivInputImage.setImageBitmap(result)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
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

        getLocation()
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnCamera.id -> {
//                openCamera()
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

    private fun openGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }


    private fun getLocation() {
        locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this, requireActivity().mainLooper)
    }

    override fun onLocationChanged(location: Location) {
        lon = location.longitude.toFloat()
        lat = location.latitude.toFloat()
    }
}

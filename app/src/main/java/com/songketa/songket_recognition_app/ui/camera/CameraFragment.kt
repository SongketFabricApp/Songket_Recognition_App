package com.songketa.songket_recognition_app.ui.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.fragment.app.viewModels
import com.songketa.songket_recognition_app.MainActivity
import com.songketa.songket_recognition_app.R
import com.songketa.songket_recognition_app.databinding.ActivityMainBinding
import com.songketa.songket_recognition_app.databinding.FragmentCameraBinding
import com.songketa.songket_recognition_app.ui.ViewModelFactory
import com.songketa.songket_recognition_app.ui.home.HomeFragment
import com.songketa.songket_recognition_app.ui.home.HomeViewModel
import com.songketa.songket_recognition_app.utils.CameraUtils
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import java.io.File
import com.songketa.songket_recognition_app.data.Result
import com.songketa.songket_recognition_app.utils.getImageUri
import com.songketa.songket_recognition_app.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody


class CameraFragment : Fragment(), View.OnClickListener{

    private var _binding: FragmentCameraBinding? = null
    private val CAMERA_PERMISSION_REQUEST_CODE = 123

    private val binding get() = _binding!!

    private var currentImageUri: Uri? = null
    private val viewModel by viewModels<CameraViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }


    private val launcherIntentCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                showImage()
            }
        }

    private val launcherIntentGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val selectedImg: Uri? = result.data?.data
                selectedImg?.let {
                    binding.ivInputImage.setImageURI(selectedImg)
                    currentImageUri = selectedImg
                }
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
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnCamera.id -> {
                checkCameraPermission()
            }
            binding.btnGallery.id -> {
                openGallery()
            }
            binding.btnCheckOut.id -> {
                uploadImage()
            }
        }
    }
    private fun checkCameraPermission() {
        val cameraPermission = Manifest.permission.CAMERA
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                cameraPermission
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Camera permission already granted, open the camera
                openCamera()
            }
            shouldShowRequestPermissionRationale(cameraPermission) -> {
                // Display a rationale for needing the permission
                showPermissionRationaleDialog()
            }
            else -> {
                // Request the permission
                requestPermissions(arrayOf(cameraPermission), CAMERA_PERMISSION_REQUEST_CODE)
            }
        }
    }

    private fun showPermissionRationaleDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Camera Permission Needed")
            .setMessage("This app requires camera permission to take pictures.")
            .setPositiveButton("OK") { _, _ ->
                requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun openCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.ivInputImage.setImageURI(it)
        }
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, requireContext())

            if (imageFile != null) {
                showLoading(true)

                val requestImageFile: RequestBody =
                    imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                val multipartBody = MultipartBody.Part.createFormData(
                    "photo",
                    imageFile.name,
                    requestImageFile
                )

                viewModel.postImage(multipartBody).observe(viewLifecycleOwner) { upload ->
                    when (upload) {
                        is Result.Success -> {
                            showLoading(false)
                            showToast("Image uploaded successfully")
                        }
                        is Result.Error -> {
                            showLoading(false)
                            showToast("Error uploading image: ${upload.error}")
                        }
                        else -> {
                            // Handle other cases if needed
                        }
                    }
                }
            } else {
                showToast("Failed to get file from URI")
            }
        } ?: showToast(getString(R.string.message))
    }

    private fun openGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        launcherIntentGallery.launch(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

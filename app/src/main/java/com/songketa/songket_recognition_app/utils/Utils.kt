package com.songketa.songket_recognition_app.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage
import com.songketa.songket_recognition_app.BuildConfig
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())
private const val MAXIMAL_SIZE = 1000000


fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validateMinLength(password: String): Boolean {
    return !TextUtils.isEmpty(password) && password.length >= Constant.MIN_LENGTH_PASSWORD
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun softkeyboard(context: Context, view: View) {
    (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view.windowToken, 0)
}

fun getImageUri(context: Context): Uri {
//    var uri: Uri? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val contentValues = ContentValues().apply {
//            put(MediaStore.MediaColumns.DISPLAY_NAME, "$timeStamp.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/MyCamera/")
        }
        val uri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
        return uri ?: throw IllegalStateException("Failed to create image URI")
    }
    else {
        return getImageUriForPreQ(context)
    }
}

private fun getImageUriForPreQ(context: Context): Uri {
    val filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File(filesDir, "/MyCamera/.jpeg")
    if (imageFile.parentFile?.exists() == false) imageFile.parentFile?.mkdir()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        return FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.fileprovider",
            imageFile
        )
    } else {
        return Uri.fromFile(imageFile)
    }
}

//fun uriToFile(uri: Uri, context: Context): File? {
//    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
//    inputStream?.let {
//        try {
//            val file = createImageFile(context)
//            val outputStream = FileOutputStream(file)
//            val buffer = ByteArray(4 * 1024) // or other buffer size
//            var read: Int
//            while (inputStream.read(buffer).also { read = it } != -1) {
//                outputStream.write(buffer, 0, read)
//            }
//            outputStream.flush()
//            outputStream.close()
//            inputStream.close()
//            return file
//        } catch (e: IOException) {
//            Log.e("UriToFile", "Error converting Uri to File: ${e.message}")
//        }
//    }
//    return null
//}
//@Throws(IOException::class)
//private fun createImageFile(context: Context): File {
//    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//    return File.createTempFile(
//        "JPEG_${timeStamp}_", /* prefix */
//        ".jpg", /* suffix */
//        storageDir /* directory */
//    )
//}
//fun uriToFile(uri: Uri, context: Context): File? {
//    val contentResolver = context.contentResolver
//    val projection = arrayOf(MediaStore.Images.Media.DATA)
//    val cursor = contentResolver.query(uri, projection, null, null, null)
//    cursor?.use {
//        val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        it.moveToFirst()
//        val filePath = it.getString(columnIndex)
//        return File(filePath)
//    }
//    return null
//}

fun uriToFile(imageUri: Uri, context: Context): File {
    val myFile = createCustomTempFile(context)
    val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
    inputStream?.use { input ->
        val outputStream = FileOutputStream(myFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (input.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }
        outputStream.close()
    }
    return myFile
}
fun createCustomTempFile(context: Context): File {
    val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())
    val filesDir = context.externalCacheDir
    return File.createTempFile(timeStamp, ".jpg", filesDir)
}

//fun File.reduceFileImage(): File {
//    val file = this
//    val bitmap = BitmapFactory.decodeFile(file.path).getRotatedBitmap(file)
//    var compressQuality = 100
//    var streamLength: Int
//    do {
//        val bmpStream = ByteArrayOutputStream()
//        bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
//        val bmpPicByteArray = bmpStream.toByteArray()
//        streamLength = bmpPicByteArray.size
//        compressQuality -= 5
//    } while (streamLength > MAXIMAL_SIZE)
//    bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
//    return file
//}

//fun Bitmap.getRotatedBitmap(file: File): Bitmap? {
//    val orientation = ExifInterface(file).getAttributeInt(
//        ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED
//    )
//    return when (orientation) {
//        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(this, 90)
//        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(this, 180)
//        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(this, 270)
//        ExifInterface.ORIENTATION_NORMAL -> this
//        else -> this
//    }
//}


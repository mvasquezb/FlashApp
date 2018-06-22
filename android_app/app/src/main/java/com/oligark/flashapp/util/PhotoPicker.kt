package com.oligark.flashapp.util

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.SupportActivity
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.Toast
import com.oligark.flashapp.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class PhotoPicker(
        val context: SupportActivity
) {
    companion object {
        @JvmField val TAG = PhotoPicker::class.java.simpleName
        @JvmField val REQUEST_CAMERA = 1141
        @JvmField val REQUEST_GALLERY = 8238
        @JvmField val PERMISSION_CAMERA_REQUEST = 6546
        @JvmField val PERMISSION_GALLERY_REQUEST = 8741
    }

    interface PhotoPickerCallback {
        fun onBitmapPicked(bitmap: Bitmap)
    }

    var photoUri: Uri? = null
        private set
    private var callback: PhotoPickerCallback? = null

    constructor(context: SupportActivity, callback: PhotoPickerCallback): this(context) {
        this.callback = callback
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CAMERA_REQUEST -> {
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchCamera()
                } else {
                    // Permission denied
                }
            }
            PERMISSION_GALLERY_REQUEST -> {
                // Ok
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "Req: $requestCode | result: $resultCode | data: $data | ok: $RESULT_OK")
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            REQUEST_CAMERA -> {
                val bitmap = createBitmap(photoUri)
                callback?.onBitmapPicked(bitmap)
            }
            REQUEST_GALLERY -> {
                if (data == null) {
                    return
                }
                photoUri = data.data
                val bitmap = createBitmap(photoUri)
                callback?.onBitmapPicked(bitmap)
            }
        }
    }

    private fun createBitmap(uri: Uri?): Bitmap {
        val input = context.contentResolver.openInputStream(uri)
        val src = BitmapFactory.decodeStream(input)
        input.close()
        val width = src.width * 0.8
        val height = src.height * 0.8
        val dest = Bitmap.createScaledBitmap(src, width.toInt(), height.toInt(), true)
        return dest
    }

    fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(context.packageManager) == null) {
            return
        }
        try {
            val photo = createImageFile()
            photoUri = FileProvider.getUriForFile(
                    context, context.getString(R.string.file_provider_authority), photo)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            context.startActivityForResult(intent, REQUEST_CAMERA)
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
            println(ex.localizedMessage)
            ex.printStackTrace()
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.absolutePath
        return image
    }

    fun requestGallery() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_PICK
        }
        context.startActivityForResult(
                Intent.createChooser(intent, context.getString(R.string.complete_action_using)),
                REQUEST_GALLERY)
    }

    fun requestCamera() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    context,
                    arrayOf(Manifest.permission.CAMERA),
                    PERMISSION_CAMERA_REQUEST)
        } else {
            launchCamera()
        }
    }
}
package com.oligark.flashapp.view


import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.oligark.flashapp.R
import com.oligark.flashapp.databinding.FragmentUserProfileBinding
import com.oligark.flashapp.util.PhotoPicker
import com.squareup.picasso.Picasso
import java.io.InputStream

class UserProfileFragment : Fragment(), PhotoPicker.PhotoPickerCallback {
    companion object {
        @JvmField val TAG = UserProfileFragment::class.java.simpleName
    }
    private lateinit var binding: FragmentUserProfileBinding
    private lateinit var photoPicker: PhotoPicker
    private var userImage: Bitmap? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        photoPicker = PhotoPicker(activity!!, this)
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user_profile, container, false)
        val rootView = binding.root
        binding.userSelectImg.setOnClickListener { selectUserImage() }
        return rootView
    }

    private var pickPhotoDialog: AlertDialog? = null

    private fun selectUserImage() {
        val dialogRoot = layoutInflater.inflate(R.layout.dialog_select_picture, null)
        val cameraOption = dialogRoot.findViewById<View>(R.id.select_from_camera)
        val galleryOption = dialogRoot.findViewById<View>(R.id.select_from_gallery)
        cameraOption.setOnClickListener {
            photoPicker.requestCamera()
        }
        galleryOption.setOnClickListener {
            photoPicker.requestGallery()
        }
        if (pickPhotoDialog == null) {
            pickPhotoDialog = AlertDialog.Builder(activity)
                    .setTitle(getString(R.string.select_user_prompt))
                    .setView(dialogRoot)
                    .create()
        }
        pickPhotoDialog?.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        photoPicker.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "Req: $requestCode | result: $resultCode | data: $data | ok: $RESULT_OK")
        super.onActivityResult(requestCode, resultCode, data)
        photoPicker.onActivityResult(requestCode, resultCode, data)
        pickPhotoDialog?.dismiss()
    }

    override fun onBitmapPicked(bitmap: Bitmap) {
        // Setting user image bitmap
        this.userImage = bitmap
        binding.userImg.setImageBitmap(bitmap)
//        Picasso.get().load(photoPicker.photoUri).into(binding.userImg)
    }
}

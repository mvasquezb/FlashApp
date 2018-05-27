package com.oligark.flashapp.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.oligark.flashapp.viewmodel.UserViewModel

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            val key = "UserProfileViewModel"
            if(hashMapViewModel.containsKey(key)){
                return getViewModel(key) as T
            } else {
                addViewModel(key, UserViewModel())
                return getViewModel(key) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        val hashMapViewModel = HashMap<String, ViewModel>()
        fun addViewModel(key: String, viewModel: ViewModel){
            hashMapViewModel[key] = viewModel
        }
        fun getViewModel(key: String): ViewModel? {
            return hashMapViewModel[key]
        }
    }
}
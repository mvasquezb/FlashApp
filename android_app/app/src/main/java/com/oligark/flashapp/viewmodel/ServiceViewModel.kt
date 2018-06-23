
package com.oligark.flashapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.oligark.flashapp.model.Service

class ServiceViewModel : ViewModel() {
    var serviceList = MutableLiveData<List<Service>>()

    init {

    }
}

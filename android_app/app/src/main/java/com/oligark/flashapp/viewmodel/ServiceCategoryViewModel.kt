
package com.oligark.flashapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.oligark.flashapp.model.ServiceCategory

class ServiceCategoryViewModel : ViewModel() {
    var categoryList = MutableLiveData<List<ServiceCategory>>()

    init {

    }
    private fun sendRequestAndPrintResponse() {


    }
}

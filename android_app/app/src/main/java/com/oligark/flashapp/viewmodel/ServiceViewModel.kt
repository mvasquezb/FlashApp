
package com.oligark.flashapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.oligark.flashapp.di.Dependencies
import com.oligark.flashapp.model.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceViewModel : ViewModel() {
    var serviceList = MutableLiveData<List<Service>>()

    init {
        Dependencies.getInstance().serviceService.findAll().enqueue(object : Callback<List<Service>> {
            override fun onFailure(call: Call<List<Service>>?, t: Throwable?) {
                serviceList.value = null
            }

            override fun onResponse(call: Call<List<Service>>?, response: Response<List<Service>>?) {
                serviceList.value = response?.body()
            }
        })
    }
}

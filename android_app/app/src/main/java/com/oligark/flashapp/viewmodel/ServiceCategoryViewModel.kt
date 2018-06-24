
package com.oligark.flashapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.oligark.flashapp.di.Dependencies
import com.oligark.flashapp.model.ServiceCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceCategoryViewModel : ViewModel() {
    var categoryList = MutableLiveData<List<ServiceCategory>>()

    init {

        Dependencies.getInstance().categoryService.findAll().enqueue(object : Callback<List<ServiceCategory>> {
            override fun onFailure(call: Call<List<ServiceCategory>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<ServiceCategory>>?, response: Response<List<ServiceCategory>>?) {
                if (response == null || !response.isSuccessful) {
                    println("Error de get ps")
                    return
                }
                val categories = response.body()!!
                categoryList.value =  categories;

            }

        })
    }
    private fun sendRequestAndPrintResponse() {


    }
}

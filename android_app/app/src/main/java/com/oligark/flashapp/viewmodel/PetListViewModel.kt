package com.oligark.flashapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v7.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.oligark.flashapp.model.Pet
import org.json.JSONObject
import java.util.ArrayList
import com.google.gson.reflect.TypeToken



class PetListViewModel(application: Application) : AndroidViewModel(application) {
    val petList = MutableLiveData<List<Pet>>()
    init {
        sendRequestAndPrintResponse()
    }

    private fun sendRequestAndPrintResponse() {
        var url = "http://10.100.242.60/FlashApp-Backend/public/api/users/1/pets"
        var mRequestQueue = Volley.newRequestQueue(this.getApplication())
        var sStringRequest = StringRequest(Request.Method.GET, url, object : Response.Listener<String> {
            override fun onResponse(response: String) {
                try {
                    val pets = mutableListOf<Pet>()
                    val gson = Gson()
                    val obj = JSONObject(response)
                    val data = obj.getJSONArray("data")
                    for (i in 0 until data.length()) {
                        val petJson = data.get(i).toString()
                        val pet = gson.fromJson<Pet>(petJson, Pet::class.java)
                        pets.add(pet)
                    }
                    petList.value = pets
                    /*
                    int size = data.length();
                    int i;
                    for (i = 0;i<size;i++)
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(mascotas.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage(data.getJSONObject(i).toString());
                        alertDialog.show();
                    }
                    */
                } catch (e: Exception) {
                    val alertDialog = AlertDialog.Builder(this@PetListViewModel.getApplication()).create()
                    alertDialog.setTitle("Alert")
                    alertDialog.setMessage(e.toString())
                    alertDialog.show()
                }

            }
        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError) {
                /*
                val alertDialog = AlertDialog.Builder(this@PetListViewModel.getApplication()).create()
                alertDialog.setTitle("Alert")
                alertDialog.setMessage(error.toString())
                alertDialog.show()
                */
                println(error.toString())
            }
        })
        mRequestQueue.add(sStringRequest)
    }
}

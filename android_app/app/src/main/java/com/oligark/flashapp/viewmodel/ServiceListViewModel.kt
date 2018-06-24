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
import com.oligark.flashapp.model.Service
import org.json.JSONObject
import java.util.ArrayList
import com.google.gson.reflect.TypeToken



class ServiceListViewModel(application: Application) : AndroidViewModel(application) {
    val serviceList = MutableLiveData<List<Service>>()
    init {
        sendRequestAndPrintResponse()
    }

    private fun sendRequestAndPrintResponse() {
        var url = "http://demo6557625.mockable.io/services"
        var mRequestQueue = Volley.newRequestQueue(this.getApplication())
        var sStringRequest = StringRequest(Request.Method.GET, url, object : Response.Listener<String> {
            override fun onResponse(response: String) {
                try {
                    val services = mutableListOf<Service>()
                    val gson = Gson()
                    val obj = JSONObject(response)
                    val listaServicios = obj.getJSONArray("listaServicios")
                    for (i in 0 until listaServicios.length()) {
                        val serviceJson = listaServicios.get(i).toString()
                        val service = gson.fromJson<Service>(serviceJson, Service::class.java)
                        services.add(service)
                    }
                    serviceList.value = services
                    println("gg")
                    println(response)
                    println("JP es mi gestor")
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
                    //val alertDialog = AlertDialog.Builder(this@ServiceListViewModel.getApplication()).create()
                    //alertDialog.setTitle("Alert")
                    //alertDialog.setMessage(e.toString())
                    //alertDialog.show()
                    println(e.toString())
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

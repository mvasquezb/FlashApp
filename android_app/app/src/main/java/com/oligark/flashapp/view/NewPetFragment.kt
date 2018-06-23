package com.oligark.flashapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.oligark.flashapp.R
import com.oligark.flashapp.service.api.BaseApi
import java.util.HashMap
import android.provider.MediaStore
import android.os.Environment.getExternalStorageDirectory
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.ImageView
import java.io.File


class NewPetFragment : Fragment() {
    lateinit var btn_add_pet: Button
    lateinit var nombre: TextInputEditText
    lateinit var tipo: TextInputEditText
    lateinit var raza: TextInputEditText
    lateinit var sexo: TextInputEditText
    lateinit var img: ImageView
    companion object {
        val TAG = NewPetFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_new_pet, container, false)
        btn_add_pet = rootView.findViewById(R.id.btnpetn_crear) as Button
        nombre = rootView.findViewById(R.id.petn_name) as TextInputEditText
        tipo = rootView.findViewById(R.id.petn_tipo) as TextInputEditText
        raza = rootView.findViewById(R.id.petn_raza) as TextInputEditText
        sexo = rootView.findViewById(R.id.petn_sexo) as TextInputEditText
        img = rootView.findViewById(R.id.petn_img) as ImageView
        // Inflate the layout for this fragment
        btn_add_pet.setOnClickListener { addpet() }
        img.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 0)
        }
        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val bitmap = data!!.extras!!.get("data") as Bitmap
        img.setImageBitmap(bitmap)
    }

    private fun addpet() {
        val mRequestQueue = Volley.newRequestQueue(activity)
        val api = BaseApi.apiUrl
        //val url = "http://10.100.242.60/FlashApp-Backend/public/api/pets"
        val url = api + "pets"
        val postRequest = object : StringRequest(Request.Method.POST, url,
                object : Response.Listener<String> {
                    override fun onResponse(response: String) {
                        // response
                        val alertDialog = AlertDialog.Builder(activity).create()
                        alertDialog.setTitle("MSG")
                        alertDialog.setMessage(response)
                        alertDialog.show()
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError) {
                        // error
                        val alertDialog = AlertDialog.Builder(activity).create()
                        alertDialog.setTitle("ALERT")
                        alertDialog.setMessage(error.message)
                        alertDialog.show()
                    }
                }
        ) {
            protected override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()

                params["name"] = nombre.getText().toString()
                params["gender"] = sexo.getText().toString()
                params["breed"] = raza.getText().toString()
                params["tipo"] = tipo.getText().toString()
                params["user_id"] = "1"
                return params
            }
        }
        mRequestQueue.add(postRequest)
    }



}

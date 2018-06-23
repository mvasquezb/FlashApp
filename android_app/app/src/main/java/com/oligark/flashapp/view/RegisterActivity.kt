
package com.oligark.flashapp.view


import android.content.Intent
import android.app.AlertDialog
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.oligark.flashapp.R
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap


class User {
    var nombre: String = ""
    var apellido: String = ""
    var email: String = ""
    var contraseña: String = ""
    var direccion: String = ""

}

class RegisterActivity : AppCompatActivity() {

    //private var btn: Button? = null
    private var reg_user: Button? =null
    private var imageview: ImageView? = null
    private val GALLERY = 0
    private val CAMERA = 1
    private val user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //btn = findViewById<View>(R.id.btn) as Button
        reg_user = findViewById<View>(R.id.reg_user) as Button
        imageview = findViewById<View>(R.id.imuser) as ImageView

        imageview!!.setOnClickListener {
            showPictureDialog()
        }

        //btn!!.setOnClickListener { showPictureDialog() }

        reg_user!!.setOnClickListener { registerUser() }
    }



    private fun registerUser() {
        val textname = findViewById<View>(R.id.nombre) as TextView
        val textlastname = findViewById<View>(R.id.apellido) as TextView
        val textemail = findViewById<View>(R.id.email) as TextView
        //val textcontrasena = findViewById<View>(R.id.password) as TextView
        val textdireccion = findViewById<View>(R.id.direccion) as TextView

        val nombre = textname.text.toString()
        val apellido = textlastname.text.toString()
        val email = textemail.text.toString()
        //val contrasena = textcontrasena.text.toString()
        val direccion = textdireccion.text.toString()


        val mRequestQueue = Volley.newRequestQueue(this)
        //String url = "http://httpbin.org/post";
        val url = "http://flashapp-20181.herokuapp.com/api/users"
        val postRequest = object: StringRequest(Request.Method.POST, url,
                object: Response.Listener<String> {
                    override fun onResponse(response:String) {
                        // response
                        //val alertDialog = AlertDialog.Builder(this@RegisterActivity).create()
                        //alertDialog.setTitle("MSG")
                        //alertDialog.setMessage(response)
                        //alertDialog.show()
                        Toast.makeText(this@RegisterActivity, "Usuario Creado!", Toast.LENGTH_SHORT).show()
                    }
                },
                object:Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError) {
                        // error
                        val alertDialog = AlertDialog.Builder(this@RegisterActivity).create()
                        alertDialog.setTitle("ALERT")
                        //alertDialog.setMessage(error.getMessage())
                        //alertDialog.show()
                    }
                }
        ) {
            override fun getParams():Map<String, String> {
                val params = HashMap<String, String> ()
                params["firstName"] = nombre
                params["lastName"] = apellido
                params["birthday"] = "1981-02-25"
                params["address"] = direccion
                params["email"] = email
                return params
            }
        }
        mRequestQueue.add(postRequest)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }



    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Opcion")
        val pictureDialogItems = arrayOf("Selecciona Foto de Galeria", "Tomar Foto de Camara")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    Toast.makeText(this@RegisterActivity, "Imagen Guardada!", Toast.LENGTH_SHORT).show()
                    imageview!!.setImageBitmap(bitmap)

                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@RegisterActivity, "Fallo!", Toast.LENGTH_SHORT).show()
                }

            }

        }
        else if (requestCode == CAMERA)
        {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imageview!!.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            Toast.makeText(this@RegisterActivity, "Image Guardada!", Toast.LENGTH_SHORT).show()
        }

    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
                (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                    arrayOf(f.getPath()),
                    arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "Archivo Guardada::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/demonuts"
    }


}

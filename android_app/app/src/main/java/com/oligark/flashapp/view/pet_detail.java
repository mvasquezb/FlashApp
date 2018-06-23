package com.oligark.flashapp.view;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Pet;
import com.oligark.flashapp.service.api.BaseApi;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

public class pet_detail extends Fragment {
    Pet pet;
    TextInputEditText nombre;
    TextInputEditText tipo;
    TextInputEditText raza;
    TextInputEditText sexo;
    ImageView img;
    public pet_detail() {
        // Required empty public constructor
    }
    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pet = new Gson().fromJson(this.getArguments().getString("pet"), Pet.class);
        System.out.println(pet.getImageUrl());
        Button btneditar = (Button) findViewById(R.id.btnpetd_editar);
        Button btndelete = (Button) findViewById(R.id.btnped_delete)
    }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pet = new Gson().fromJson(this.getArguments().getString("pet"), Pet.class);
        View vista = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        Button btneditar = vista.findViewById(R.id.btnpetd_editar);
        Button btndelete = vista.findViewById(R.id.btnped_delete);
        nombre = vista.findViewById(R.id.petd_name);
        img = vista.findViewById(R.id.petd_img);
        tipo = vista.findViewById(R.id.petd_tipo);
        raza = vista.findViewById(R.id.petd_raza);
        sexo = vista.findViewById(R.id.petd_sexo);
        nombre.setText(pet.getNombre());
        tipo.setText(pet.getAnimal().getDescription());
        raza.setText(pet.getRaza());
        sexo.setText(pet.getSexo());
        Picasso.get().load(pet.getImageUrl()).into(img);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        return vista;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        img.setImageBitmap(bitmap);
    }

    private void update(){
        final Bitmap bitmapimg = ((BitmapDrawable)img.getDrawable()).getBitmap();
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        String api = BaseApi.INSTANCE.getApiUrl();
        //String url = "http://10.100.242.60/FlashApp-Backend/public/api/pets/update";
        String url = api + "pets/update";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle("MSG");
                        alertDialog.setMessage(response);
                        alertDialog.show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle("ALERT");
                        alertDialog.setMessage(error.getMessage());
                        alertDialog.show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                String imageData = imageToString(bitmapimg);
                params.put("name", nombre.getText().toString());
                params.put("gender", sexo.getText().toString());
                params.put("breed", raza.getText().toString());
                params.put("tipo", tipo.getText().toString());
                params.put("id", String.valueOf(pet.getId()));
                params.put("image",imageData);
                return params;
            }
        };
        mRequestQueue.add(postRequest);
    }

    private void delete(){
        //String url = "http://10.100.242.60/FlashApp-Backend/public/api/pets/delete/" + pet.getId();
        String api = BaseApi.INSTANCE.getApiUrl();
        String url = api + "pets/delete/" + pet.getId();
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        StringRequest sStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage(response);
                    alertDialog.show();
                    // cb.onDeletePet
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                catch (Exception e){
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage(e.toString());
                    alertDialog.show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage(error.toString());
                alertDialog.show();
            }
        });
        mRequestQueue.add(sStringRequest);
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imgageBytes = outputStream.toByteArray();
        String econdedimage = Base64.encodeToString(imgageBytes, Base64.DEFAULT);
        return econdedimage;
    }



}

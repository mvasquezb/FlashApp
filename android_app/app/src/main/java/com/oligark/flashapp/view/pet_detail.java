package com.oligark.flashapp.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Pet;

import java.util.HashMap;
import java.util.Map;


public class pet_detail extends Fragment {
    Pet pet;
    TextInputEditText nombre;
    TextInputEditText tipo;
    TextInputEditText raza;
    TextInputEditText sexo;
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
        tipo = vista.findViewById(R.id.petd_tipo);
        raza = vista.findViewById(R.id.petd_raza);
        sexo = vista.findViewById(R.id.petd_sexo);
        nombre.setText(pet.getNombre());
        tipo.setText(pet.getAnimal().getDescription());
        raza.setText(pet.getRaza());
        sexo.setText(pet.getSexo());
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
        return vista;
    }

    private void update(){
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        //String url = "http://httpbin.org/post";
        String url = "http://10.100.242.60/FlashApp-Backend/public/api/pets/update";
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

                params.put("name", nombre.getText().toString());
                params.put("gender", sexo.getText().toString());
                params.put("breed", raza.getText().toString());
                params.put("tipo", tipo.getText().toString());
                params.put("id", String.valueOf(pet.getId()));
                return params;
            }
        };
        mRequestQueue.add(postRequest);
    }

    private void delete(){
        String url = "http://10.100.242.60/FlashApp-Backend/public/api/pets/delete/" + pet.getId();
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
}

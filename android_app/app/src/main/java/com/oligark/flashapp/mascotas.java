package com.oligark.flashapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class mascotas extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.lista);
        ListView lista;

        //ListView lista;
        ArrayAdapter<String> adaptador;

        lista = (ListView)findViewById(R.id.listView);

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        lista.setAdapter(adaptador);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);


    }
}

package com.oligark.flashapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class mascotas extends AppCompatActivity {

    ListView listaDatos;
    ArrayList<Datos> Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaDatos = (ListView) findViewById(R.id.listView);

        Lista = new ArrayList<mascota>();

        Lista.add(new mascota("nombre","animal","raza","sexo","imgurl"));
        Lista.add(new mascota("nombre2","animal2","raza2","sexo2","imgurl2"));
        Lista.add(new mascota("nombre3","animal3","raza3","sexo3","imgurl3"));

        Adaptador miadaptador = new Adaptador(getApplicationContext(),Lista);

        listaDatos.setAdapter(miadaptador);

        /*
        ArrayAdapter<String> adaptador;

        lista = (ListView)findViewById(R.id.listView);

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        lista.setAdapter(adaptador);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        */


    }
}

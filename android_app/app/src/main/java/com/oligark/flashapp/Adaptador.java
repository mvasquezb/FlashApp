package com.oligark.flashapp;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
public class Adaptador extends BaseAdapter {
    Context context;

    List<mascota> ListaObject;

    public Adaptador(Context context, List<mascota> listaObject) {
        this.context = context;
        ListaObject = listaObject;
    }

    @Override
    public int getCount() {
        return ListaObject.size();
    }

    @Override
    public Object getItem(int i) {
        return ListaObject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ListaObject.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista =inflate.inflate(R.layout.itemListview,null);
        ImageView image = (ImageView) vista.findViewById(R.id.imageView4);
        TextView nombre = (TextView) vista.findViewById(R.id.editText);
        TextView animal = (TextView) vista.findViewById(R.id.editText2);
        TextView raza = (TextView) vista.findViewById(R.id.editText3);
        TextView sexo = (TextView) vista.findViewById(R.id.editText4);

        nombre.setText(ListaObject.getPosition).getNombre().ToString();
        animal.setText(ListaObject.getPosition).getAnimal().ToString();
        raza.setText(ListaObject.getPosition).getRaza().ToString();
        sexo.setText(ListaObject.getPosition).getSexo().ToString();
        image.
        return vista;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}

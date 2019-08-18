package com.example.obnativasp1;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter implements ListAdapter {

    ArrayList<String> arrayList;
    Context context;
    ArrayList<String> imagenes;
    ArrayList<String> direcciones;

    public CustomAdapter(Context context, ArrayList<String> arrayList, ArrayList<String> imagenes, ArrayList<String> direcciones) {
        this.arrayList=arrayList;
        this.context=context;
        this.imagenes = imagenes;
        this.direcciones = direcciones;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*Como obtengo un vector, necesito devolver las cosas de a una, entonces cargo
        * en cada item lo que quiero*/
        String subjectData = arrayList.get(position);
        String imagen = imagenes.get(position);
        String dirmapa = direcciones.get(position);

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.ativity_list_view_categorias, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            /* Cargo el tiulo de las pelis*/
            TextView tittle = convertView.findViewById(R.id.titulo);


            /* AHOra la imagen*/
            ImageView imag = convertView.findViewById(R.id.imagen);
            tittle.setText(subjectData);
            Picasso.with(context).load("http://apiort.montevideo-gh.com/img/"+imagen).into(imag);

            /* cargo la direccion para el mapa*/
            TextView mapa = convertView.findViewById(R.id.direccionMapa);
            mapa.setText(dirmapa);

        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}


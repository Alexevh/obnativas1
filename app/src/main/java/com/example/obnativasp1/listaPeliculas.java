package com.example.obnativasp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class listaPeliculas  extends Activity implements asyncResponse {


    ListView listView;
    List<String> listaPeliculas = new ArrayList<String>();

    List<String> listaImagenes = new ArrayList<String>();
    List<String> listaDirecciones = new ArrayList<String>();

    EditText textBox;
    TextView text;
    String[] values = listaPeliculas.toArray(new String[0]);
    ArrayAdapter<String> adapter = null;
    Context context;
    String direccion = "";
    int posicion =0;

    /* El adaptador custom se hace para que la lista refresque las cosas cuando itera un item*/
    CustomAdapter apaptador = new CustomAdapter(this, (ArrayList<String>) listaPeliculas, (ArrayList<String>) listaImagenes, (ArrayList<String>) listaDirecciones);
    ImageView imagen =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_peliculas_layout);
        textBox = (EditText) findViewById(R.id.textBoxBuscar);
        text = (TextView) findViewById(R.id.text);

        //Obtrengo el listview from xml
        listView = (ListView) findViewById(R.id.listaPeliculas);
         imagen = (ImageView) findViewById(R.id.imagen) ;

        //setActionBar();

        List<String> parametros = new ArrayList<String>();
        String url = "http://apiort.montevideo-gh.com/public/pelicula/";
        // String url = "https://apiort.portalbase.uy/categoria";
        parametros.add(url);

        /* ojo on el orden*/
        String[] paramVector = new String[parametros.size()];
        paramVector = parametros.toArray(paramVector);
        String resultado = "";

        /*escuchamos los cambnios en el filtro*/
        textBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        try {
            resultado = new resPeliculas(this).execute(paramVector).get();

            JSONObject retorno = new JSONObject(resultado.substring(resultado.indexOf("{"), resultado.lastIndexOf("}") + 1));


            /* Aca tengo todo el array de la dec , el JSON viene muy entreverado, y dentro de el hay una seccion o un JSON propio
             * que es el descripcion, ahi dentro estan las pelis con los id y el titulo, dentro de sescripcion hay muchos subjsons uno
             * por cada pelicula, por eso hacemos un jsonarray y lo buscamod por etiqueta, en este caso description*/
            JSONArray params = retorno.getJSONArray("descripcion");


            /* Como la lista recibe un vector de strings creamos una lista*/
            // List<String> listaCategorias = new ArrayList<>();


            /* Iteramos sobre el array de jsons y obtenemos los subjsons*/
            for (int i = 0; i < params.length(); i++) {
                JSONObject pelicula = params.getJSONObject(i);
                System.out.println("el nombre de la peli es " + pelicula.getString("titulo"));
                /* Obtenemos el string que corresponde a la etiqueta titulo y se lo asignamos a la lista*/
                listaPeliculas.add(pelicula.getString("titulo"));
                listaImagenes.add(pelicula.getString("foto"));
                listaDirecciones.add(pelicula.getString("mapa"));



                /* cargo la imagen*/

               //Picasso.with(this).load("http://apiort.montevideo-gh.com/img/tonto.jpeg").into(imagen);
            }


            /* Convertimos el arraylist en un vector*/
            String[] values = listaPeliculas.toArray(new String[0]);


            // Definimos un adaptador nuevo
            // primer  parametro - Contexto
            // Segundo parametro - Layout for the row
            // Tercer parametro - ID del TextView donde escribimos
            // cuarto - los datos

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);


            // Asignamos el adaptador a la listview
           listView.setAdapter(apaptador);


            // Agreganos un listener al click de la lista
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;
                    posicion = position;

                    System.out.println("la posicion es"+position);

                    // ListView Clicked item value
                    String itemValue = (String) listView.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(getApplicationContext(),
                            "Position :" + itemPosition + "  ListItem : " + direccion, Toast.LENGTH_LONG)
                            .show();

                }

            });




        } catch (Exception error) {
            System.out.println(error.getMessage());
            ;
        }

    }


    @Override
    public void processFinish(String output) {

        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        System.out.println(output);
    }

    /* en el XML que recoge todas las vistas estaba categoriasy ahra esta peliculas*/
    public  void buscarMapa(View view) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        TextView mapa = null;
        mapa = findViewById(R.id.titulo);
        String nombrepelicula = (String) mapa.getText();

        System.out.println("el titulo  que me llega es "+ nombrepelicula);

        String dir = "";


        dir = listaDirecciones.get(posicion);

        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("valorMapa", direccion);
        startActivity(i);


        System.out.println("La direccion que me llega es "+dir);
    getMapa(view, dir);

    }

    public void getMapa(View view, String direccion)
    {
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("valorMapa", direccion);
        startActivity(i);
    }


}
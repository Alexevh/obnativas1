package com.example.obnativasp1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class listaCategorias extends Activity implements asyncResponse {


    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_list_view_categorias);

        //Obtrengo el listview from xml
        listView = (ListView) findViewById(R.id.listaCategorias);

        List<String> parametros = new ArrayList<String>();
        String url = "http://apiort.montevideo-gh.com/public/categoria/";
       // String url = "https://apiort.portalbase.uy/categoria";
        parametros.add(url);

        /* ojo on el orden*/
        String[] paramVector = new String[parametros.size()];
        paramVector = parametros.toArray(paramVector);
        String resultado = "";

        try {
            resultado = new restCategorias(this).execute(paramVector).get();

            JSONObject retorno = new JSONObject(resultado.substring(resultado.indexOf("{"), resultado.lastIndexOf("}") + 1));


            /* Aca tengo todo el array de la dec , el JSON viene muy entreverado, y dentro de el hay una seccion o un JSON propio
             * que es el descripcion, ahi dentro estan las pelis con los id y el titulo, dentro de sescripcion hay muchos subjsons uno
             * por cada pelicula, por eso hacemos un jsonarray y lo buscamod por etiqueta, en este caso description*/
            JSONArray params = retorno.getJSONArray("descripcion");


            /* Como la lista recibe un vector de strings creamos una lista*/
            List<String> listaCategorias = new ArrayList<>();


            /* Iteramos sobre el array de jsons y obtenemos los subjsons*/
            for (int i = 0; i < params.length(); i++) {
                JSONObject pelicula = params.getJSONObject(i);
                System.out.println( "el nombre de la peli es "+pelicula.getString("nombre"));
                /* Obtenemos el string que corresponde a la etiqueta titulo y se lo asignamos a la lista*/
                listaCategorias.add(pelicula.getString("nombre"));
            }


            /* Convertimos el arraylist en un vector*/
            String[] values = listaCategorias.toArray(new String[0]);


            // Definimos un adaptador nuevo
            // primer  parametro - Contexto
            // Segundo parametro - Layout for the row
            // Tercer parametro - ID del TextView donde escribimos
            // cuarto - los datos

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);


            // Asignamos el adaptador a la listview
            listView.setAdapter(adapter);

            // Agreganos un listener al click de la lista
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) listView.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(getApplicationContext(),
                            "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
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


}

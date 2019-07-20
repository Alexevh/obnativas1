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

public class listaCategorias extends Activity implements asyncResponse{


    ListView listView ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_list_view_categorias);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listaCategorias);

        List<String> parametros= new ArrayList<String>();
        //String url = "http://apiort.montevideo-gh.com/public/categoria/";
        String url = "https://apiort.portalbase.uy/categoria";
        parametros.add(url);

        /* ojo on el orden*/
        String[] paramVector = new String[parametros.size()];
        paramVector = parametros.toArray(paramVector);
        String resultado= "";

try {
     resultado = new restCategorias(this).execute(paramVector).get();

    JSONObject retorno =  new JSONObject(resultado.substring(resultado.indexOf("{"), resultado.lastIndexOf("}") + 1));


    /* Aca tengo todo el array de la dec*/
    JSONArray params = retorno.getJSONArray("description");


    List<String> listaCategorias = new ArrayList<>();


    for (int i=0; i<params.length();i++)
    {
        JSONObject pelicula = params.getJSONObject(i);
        System.out.println( "el nombre de la peli es "+pelicula.getString("titulo"));
        listaCategorias.add(pelicula.getString("titulo"));
    }


    String[] values = listaCategorias.toArray(new String[0]);



/*
    for (int i = 0; i < keys.length (); ++i) {

        String key = keys.getString (i); // Here's your key
        String value = lista.getString (key); // Here's your value
        System.out.println( "el primer valor es "+value);
        System.out.println( "el primer valor es "+value);

    }

*/

    // Define a new Adapter
    // First parameter - Context
    // Second parameter - Layout for the row
    // Third parameter - ID of the TextView to which the data is written
    // Forth - the Array of data

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, values);


    // Assign adapter to ListView
    listView.setAdapter(adapter);

    // ListView Item Click Listener
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {

            // ListView Clicked item index
            int itemPosition     = position;

            // ListView Clicked item value
            String  itemValue    = (String) listView.getItemAtPosition(position);

            // Show Alert
            Toast.makeText(getApplicationContext(),
                    "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                    .show();

        }

    });


} catch (Exception error)
{
  System.out.println( error.getMessage());;
}






    }

    @Override
    public void processFinish(String output) {

        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        System.out.println(output);
    }


}

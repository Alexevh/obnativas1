package com.example.obnativasp1;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class registro  extends AsyncTask<String, Void, String>{

    private Activity m_activity;

    public registro(Activity activity) {
        m_activity = activity;
    }


    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);

            /* Obtengo parametros*/
            String nombre = urls[1];
            String apellido = urls[2];
            String telefono = urls[3];
            String password = urls[4];
            String mail = urls[5];


            JSONObject usuario = new JSONObject();
            usuario.put("nombre", nombre);
            usuario.put("apellido", apellido);
            usuario.put("telefono", telefono);
            usuario.put("password", password);
            usuario.put("mail", mail);




            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            /* genero los parametros en el properties*/
            //con.setRequestProperty ("Authorization", basicAuth);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            //con.setRequestProperty("Content-Length", "" + postData.getBytes().length);


            con.setRequestProperty("nombreUsuario", nombre);
            con.setRequestProperty("apellidoUsuario", apellido);
            con.setRequestProperty("emailUsuario", mail);
            con.setRequestProperty("telefonoUsuario", telefono);

            /*UUID que deberiamos sacar del aparato*/
            con.setRequestProperty("uuid", "123456789");

            /**/


            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);



            con.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String valor = reader.readLine();
            reader.close();
            con.disconnect();
            JSONObject respuesta = new JSONObject(valor);
            return respuesta.getJSONObject("value").getString("descripcion");

        } catch (Exception e) {
            Log.e("Error", e.getMessage());

            return null;
        }


    }

    @Override
    protected void onPostExecute(String result)
    {
        if (m_activity!=null)
        {
            TextView texto =(TextView)m_activity.findViewById(R.id.texto);
            texto.setText(result);
        }
    }

}

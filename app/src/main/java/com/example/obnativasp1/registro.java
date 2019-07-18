package com.example.obnativasp1;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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


            /* Obtengo parametros*/
            String nombre = urls[1];
            String apellido = urls[2];
            String telefono = urls[3];
            String password = urls[4];
            String mail = urls[5];

            String query_url = urls[0];

            /* Armo el objeto json*/
            JSONObject usuario = new JSONObject();
            usuario.put("nombreUsuario", nombre);
            usuario.put("apellidoUsuario", apellido);
            usuario.put("telefonoUsuario", telefono);
            usuario.put("passwordUsuario", password);
            usuario.put("emailUsuario", mail);
            usuario.put("statusUsuario", "ACTIVO");

            /* lo convierto a string para pasarlo a POST*/
            String json =  usuario.toString();

            /* Abro la conexion y seteo los parametros*/
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            /* Como el JSON va en el cuerpo lo metemos en el outpustream*/
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();


            // leemos ahora la respuesta, usamos la clase IOUTils de apache
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
           // System.out.println(" Me llega en result"+ result);


            in.close();
            conn.disconnect();

            /* El resultado anterior por algun motivo me regresa tanto el json que yo le mande como la
            * respuesta del servidor,a  mi solo me interesa la ultima parte asi que me hago un JSON
            * nuevo con ese substring, es una chanchada pero funciona*/
            JSONObject retorno =  new JSONObject(result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1));

            /* Obtengo el valor para la clave descricopn qye es lo que retorno*/
            return retorno.getString("descripcion");

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

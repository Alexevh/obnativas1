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

            JSONObject usuario = new JSONObject();
            usuario.put("nombreUsuario", nombre);
            usuario.put("apellidoUsuario", apellido);
            usuario.put("telefonoUsuario", telefono);
            usuario.put("passwordUsuario", password);
            usuario.put("emailUsuario", mail);
            usuario.put("statusUsuario", "ACTIVO");


            String json =  usuario.toString();

            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");

            //hardcodeo el resultado a ver si le falta la coma
            //String resultadohard = "{'status':1000, 'descripcion':'sarasa'}";
            System.out.println(" Me llega en result"+ result);
            System.out.println("result after Reading JSON Response");


            //JSONObject myResponse = new JSONObject(result);
           /* fUNCIONA EL INGRESO, PERO A PARTIR DE ACA SE ROMPE COMO QUE NO PUEDE
           * CONSTRUIR EL JSON A PARTIR DEL RESULT*/

            in.close();
            conn.disconnect();

            JSONObject retorno =  new JSONObject(result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1));
            //String d = retorno.getJSONObject("descripcion").getString("descripcion").toString();
            //System.out.println(" Me devolveria " +retorno.getString("descripcion"));

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

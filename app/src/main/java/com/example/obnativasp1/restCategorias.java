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

public class restCategorias extends AsyncTask<String, Void, String> {

    private Activity m_activity;

    public restCategorias(Activity activity) {
        m_activity = activity;
    }






    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String valor = reader.readLine();
            reader.close();
            con.disconnect();
            //JSONObject respuesta = new JSONObject(valor);
            //return respuesta.getJSONObject("value").getString("joke");\
            return valor;

        } catch (Exception e) {
            Log.e("Error", e.getMessage());

            return null;
        }



    }

    @Override
    protected void onPostExecute(String result) {

    }


}

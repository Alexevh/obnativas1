package com.example.obnativasp1;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class restRanking   extends AsyncTask<String, Void, String> {


    private Activity m_activity;

    public restRanking(Activity activity) {
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

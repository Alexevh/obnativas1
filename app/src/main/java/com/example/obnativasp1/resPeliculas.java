package com.example.obnativasp1;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class resPeliculas  extends AsyncTask<String, Void, String> {

    private Activity m_activity;

    public resPeliculas(Activity activity) {
        m_activity = activity;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {


            ///SSLContext sc = SSLContext.getInstance("SSL");
            ////sc.init(null, trustAllCerts, new java.security.SecureRandom());
            //HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL url = new URL(urls[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            // leemos ahora la respuesta, usamos la clase IOUTils de apache
            InputStream in = new BufferedInputStream(con.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            con.disconnect();

            return result;


        } catch (Exception e) {
            Log.e("Error", e.getMessage());

            return null;
        }


    }

    @Override
    protected void onPostExecute(String result) {

    }
}

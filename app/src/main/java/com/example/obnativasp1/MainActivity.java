package com.example.obnativasp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void registrar(View v)
    {

        boolean control = validar();

        if (!validar())
        {

            List<String> parametros= new ArrayList<String>();
            String url = "http://apiort.montevideo-gh.com/public/usuario";
            EditText nombre =  findViewById(R.id.nombre);
            EditText apellido = findViewById(R.id.apellido);
            EditText telefono =  findViewById(R.id.telefono);
            EditText password =  findViewById(R.id.password);
            EditText mail =  findViewById(R.id.mail);


            parametros.add(url);

            parametros.add(nombre.getText().toString());


            parametros.add(apellido.getText().toString());
            parametros.add(telefono.getText().toString());
            parametros.add(password.getText().toString());
            parametros.add(mail.getText().toString());

            //parametros.add("nombre222");
            //parametros.add("apellid2222o");
            //parametros.add("2334556644444");
            //parametros.add("paswear3333");
            //parametros.add("nombre@mail222.com");



            /* ojo on el orden*/
            String[] paramVector = new String[parametros.size()];
            paramVector = parametros.toArray(paramVector);


            new registro(this).execute(paramVector);
        }


    }


    public boolean  validar()
    {

        EditText nombre =  findViewById(R.id.nombre);
        EditText apellido = findViewById(R.id.apellido);
        EditText telefono =  findViewById(R.id.telefono);
        EditText password =  findViewById(R.id.password);
        EditText mail =  findViewById(R.id.mail);
        boolean error = false;

        if( TextUtils.isEmpty(nombre.getText())){
            error = true;
            nombre.setError( "El nombre es requerido!" );

        }

        if( TextUtils.isEmpty(apellido.getText())){
            error = true;
            apellido.setError( "El apellido es requerido!" );

        }
        if( TextUtils.isEmpty(telefono.getText())){
            error = true;
            telefono.setError( "El telefono es requerido!" );

        }
        if( TextUtils.isEmpty(password.getText())){
            error = true;
            password.setError( "El password es requerido!" );

        }
        if( TextUtils.isEmpty(mail.getText())){
            error = true;
            mail.setError( "El mail es requerido!" );

        }

            return error;

    }


}

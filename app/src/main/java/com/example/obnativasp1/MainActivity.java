package com.example.obnativasp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void registrar(View v)
    {
        List<String> parametros= new ArrayList<String>();
        String url = "http://obligatorio.local.ort.edu.uy/usuario";
        EditText nombre =  (EditText)v.findViewById(R.id.nombre);
        EditText apellido =  (EditText)v.findViewById(R.id.apellido);
        EditText telefono =  (EditText)v.findViewById(R.id.telefono);
        EditText password =  (EditText)v.findViewById(R.id.password);
        EditText mail =  (EditText)v.findViewById(R.id.mail);


        parametros.add(url);
        parametros.add(nombre.getText().toString());
        parametros.add(apellido.getText().toString());
        parametros.add(telefono.getText().toString());
        parametros.add(password.getText().toString());
        parametros.add(mail.getText().toString());



        new registro(this).execute("http://obligatorio.local.ort.edu.uy/usuario");
    }



}

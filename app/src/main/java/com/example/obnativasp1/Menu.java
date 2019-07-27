package com.example.obnativasp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        /* Seteamos los listeners */
        Button v = (Button) this.findViewById(R.id.btnRegistro);
        v.setOnClickListener(this);
        v = (Button) this.findViewById(R.id.btnPeliculas);
        v.setOnClickListener(this);
        v = (Button) this.findViewById(R.id.btnRanking);
        v.setOnClickListener(this);
        v = (Button) this.findViewById(R.id.btnCategorias);
        v.setOnClickListener(this);
        v = (Button) this.findViewById(R.id.btnAcercade);
        v.setOnClickListener(this);
        v = (Button) this.findViewById(R.id.btnSalir);
        v.setOnClickListener(this);
    }

    /* Este metodo escucha los clicks, obtengo el id del
     * elemento clickeado y ejecuto el metodo que quiero*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistro:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.btnPeliculas:
               peliculas();
                break;
            case R.id.btnCategorias:
                categorias();
                break;
            case R.id.btnRanking:
                ranking();
                break;
            case R.id.btnAcercade:
                acercade();
                break;
            case R.id.btnSalir:
                finish();
                break;
        }
    }

    public void peliculas()
    {
        Intent i = new Intent(this, listaPeliculas.class);
        startActivity(i);
    }

    public void categorias()
    {
        Intent i = new Intent(this, listaCategorias.class);
        startActivity(i);
    }
    public void ranking()
    {
        Intent i = new Intent(this, listaRanking.class);
        startActivity(i);
    }

    public void acercade()
    {
        Intent i = new Intent(this, Acercade.class);
        startActivity(i);
    }

}

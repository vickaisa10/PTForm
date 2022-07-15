package com.example.ptform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.ptform.BD.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrarBD();
    }

    private void CrarBD() {
        //Creacion de la base de datos
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

    }

    public void vis(View view) {
        Intent vis = new Intent(this, VisualizarAntiguos.class);
        startActivity(vis);
    }

    public void nuevo(View view) {
        Intent nuevo = new Intent(this, RegistroFormulario.class);
        startActivity(nuevo);
    }
}











































































































































































































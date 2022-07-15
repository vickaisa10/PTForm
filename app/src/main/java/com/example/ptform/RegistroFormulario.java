package com.example.ptform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptform.BD.ClaseGlobal;

public class RegistroFormulario extends AppCompatActivity {

    EditText txtElabora,txtNomProyecto, txtFecha ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_formulario);
        txtElabora = findViewById(R.id.txtElabora);
        txtNomProyecto = findViewById(R.id.txtNomProyecto);
        txtFecha = findViewById(R.id.txtFecha);
    }

    public void ini(View view) {

        String elabora = txtElabora.getText().toString();
        String Proyecto= txtNomProyecto.getText().toString();
        String Fecha = txtFecha.getText().toString();

        if(elabora.length()>0 && Proyecto.length()>0 && Fecha.length()>0){
            ClaseGlobal objGlobalInsert = (ClaseGlobal)getApplicationContext();
            objGlobalInsert.setElabora((elabora));
            objGlobalInsert.setProyecto((Proyecto));
            objGlobalInsert.setFecha((Fecha));

            Intent ini = new Intent(this, Levantamiento.class);
            startActivity(ini);

        }else{
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
        }

    }
}
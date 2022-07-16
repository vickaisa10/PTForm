package com.example.ptform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptform.BD.ClaseGlobal;

import java.util.Calendar;

public class RegistroFormulario extends AppCompatActivity {

    EditText txtElabora,txtNomProyecto,txtFecha;
    Button btnFecha;
    private int dia, mes, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_formulario);

        txtElabora = findViewById(R.id.txtElabora);
        txtNomProyecto = findViewById(R.id.txtNomProyecto);
        txtFecha = findViewById(R.id.txtFecha);

        btnFecha = findViewById(R.id.btnFecha);

        btnFecha.setOnClickListener(v -> {
            if(v == btnFecha){
                final Calendar i = Calendar.getInstance();
                dia = i.get(Calendar.DAY_OF_MONTH);
                mes = i.get(Calendar.MONTH);
                ano = i.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                txtFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }
                        , dia, mes, ano);
                        datePickerDialog.show();
            }
        });
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
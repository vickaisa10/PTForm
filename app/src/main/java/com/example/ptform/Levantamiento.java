package com.example.ptform;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ptform.BD.ClaseGlobal;
import com.example.ptform.BD.DbRegistro;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class Levantamiento extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    Button tomarF, coord;
    EditText lon, lat , txtExpediente, txtOperador , txtProyecto , txtTipoinfra , txtIdinfra , txtCaracter , txtTipoadec , txtObervaciones  ;
    private FusedLocationProviderClient fusedLocationClient;
    private Spinner spiEstado , spiAdecuacion ,  spiCalidad   ;
    String Stringelabora , StringProyecto , StringFecha , latitud, longitud ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levantamiento);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        tomarF = (Button) findViewById(R.id.btnTomar);

        lat = (EditText) findViewById(R.id.txtNorte);
        lon = (EditText) findViewById(R.id.txtEste);

        coord = (Button) findViewById(R.id.btnRegistrar);

        tomarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                capturarDAtos ();

            }
        });
        coord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Levantamiento.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                } else {
                    getCoord();
                }
            }

            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                Levantamiento.super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                if (requestCode == REQUEST_CODE && grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        getCoord();
                    } else {
                        Toast.makeText(Levantamiento.this, "No se dieron los permisos necesarios", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @SuppressLint("MissingPermission")
            private void getCoord() {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(Levantamiento.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    double latitud = location.getLatitude();
                                    double longitude = location.getLongitude();
                                    lat.setText(String.valueOf(latitud));
                                    lon.setText(String.valueOf(longitude));
                                    ClaseGlobal objGlobalInsert = (ClaseGlobal)getApplicationContext();
                                    objGlobalInsert.setEste((latitud));
                                    objGlobalInsert.setNorte((longitude));

                                }
                            }
                        });
            }
        });

        txtExpediente = findViewById(R.id.txtExpediente);
        txtOperador = findViewById(R.id.txtOperador);
        txtProyecto = findViewById(R.id.txtProyecto);
        txtTipoinfra = findViewById(R.id.txtTipoinfra);
        txtIdinfra = findViewById(R.id.txtIdinfra);
        txtCaracter = findViewById(R.id.txtCaracter);
        txtTipoadec = findViewById(R.id.txtTipoadec);
        txtObervaciones = findViewById(R.id.txtObervaciones);
        spiEstado = findViewById(R.id.spiEstado);
        spiAdecuacion = findViewById(R.id.spiAdecuacion);
        spiCalidad = findViewById(R.id.spiCalidad);

        ClaseGlobal objGlobalExtraer = (ClaseGlobal)getApplicationContext();
        Stringelabora  =objGlobalExtraer.getElabora();
        StringProyecto  =objGlobalExtraer.getProyecto();
        StringFecha  =objGlobalExtraer.getFecha();

    }

    private void capturarDAtos() {

        String Expediente = txtExpediente.getText().toString();
        String Operador = txtOperador.getText().toString();
        String Proyecto = txtProyecto.getText().toString();
        String Tipoinfra = txtTipoinfra.getText().toString();
        String Idinfra = txtIdinfra.getText().toString();
        String Caracter = txtCaracter.getText().toString();
        String Tipoadec = txtTipoadec.getText().toString();
        String Obervaciones = txtObervaciones.getText().toString();

        String TxtspiEstado = spiEstado.getSelectedItem().toString();
        String TxtspiAdecuacion = spiAdecuacion.getSelectedItem().toString();
        String TxtspiCalidad = spiCalidad.getSelectedItem().toString();
        latitud = lat.getText().toString();
        longitud = lon.getText().toString();



        if(Expediente.length()>0 && Operador.length()>0 && Proyecto.length()>0  && Tipoinfra.length()>0 && Idinfra.length()>0 && Caracter.length()>0
                && Tipoadec.length()>0 && Obervaciones.length()>0 && TxtspiEstado.length()>0 && TxtspiAdecuacion.length()>0
                && TxtspiCalidad.length()>0 ){

            DbRegistro dbRegistro = new DbRegistro(Levantamiento.this );
            long id = dbRegistro.insertarRegistro(Stringelabora, StringProyecto, StringFecha, Expediente , Operador ,Proyecto , Tipoinfra , Idinfra , Caracter ,
                    Tipoadec,  Obervaciones ,  TxtspiEstado ,  TxtspiAdecuacion , TxtspiCalidad , latitud , longitud );
            if(id>0){

                Intent tom = new Intent(Levantamiento.this, TomarFotografia.class);
                startActivity(tom);
            }else{
                Toast.makeText(Levantamiento.this, "Error agregrando el registro, intente de nuevo", Toast.LENGTH_SHORT).show();
            }

    }else{
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
        }
}

}

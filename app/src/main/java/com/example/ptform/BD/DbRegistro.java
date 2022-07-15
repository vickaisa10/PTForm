package com.example.ptform.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DbRegistro extends  DbHelper{

    Context context ;
    FileWriter fileWriter;

    public DbRegistro(@Nullable Context context) {
        super(context);
        this.context = context;
    }
   //Inserto los datos enviados desde el formulario
    public long insertarRegistro(String elabora, String proyecto1 , String fecha, String expediente, String operador, String proyecto
            , String tipoInfraestructura, String idInfraestructura, String caracteristicas, String estado, String adecuacion
            , String tipoAdecuacion, String calidad, String observaciones,   String esteCoordenada, String norteCoordenada){

        long id = 0 ;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("elabora", elabora);
            values.put("proyecto1", proyecto1);
            values.put("fecha", fecha);
            values.put("expediente", expediente);
            values.put("operador", operador);
            values.put("proyecto", proyecto);
            values.put("tipoInfraestructura", tipoInfraestructura);
            values.put("idInfraestructura", idInfraestructura);
            values.put("caracteristicas", caracteristicas);
            values.put("estado", estado);
            values.put("adecuacion", adecuacion);
            values.put("tipoAdecuacion", tipoAdecuacion);
            values.put("calidad", calidad);
            values.put("observaciones", observaciones);
            values.put("esteCoordenada", esteCoordenada);
            values.put("norteCoordenada", norteCoordenada);

             id = db.insert(TABLE_REGISTRO, null, values);

        }catch (Exception e){

        }

        return  id;

    }

    public ArrayList<BD_Listas> mostrarListas (){

        //consulto los datos guardados en el BD

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<BD_Listas> verListas = new ArrayList<>();
        BD_Listas bd_listas = null ;
        Cursor cursorListas = null ;

        cursorListas = db.rawQuery("SELECT * FROM "+TABLE_REGISTRO + " WHERE id ORDER BY id DESC" ,null);
        if(cursorListas.moveToFirst()){

            do {

                bd_listas = new BD_Listas();
                bd_listas.setId(cursorListas.getInt(0));
                bd_listas.setElabora(cursorListas.getString(1));
                bd_listas.setProyecto1(cursorListas.getString(2));
                bd_listas.setFecha(cursorListas.getString(3));
                bd_listas.setExpediente(cursorListas.getString(4));
                bd_listas.setOperador(cursorListas.getString(5));
                bd_listas.setProyecto(cursorListas.getString(6));
                bd_listas.setTipoInfraestructura(cursorListas.getString(7));
                bd_listas.setIdInfraestructura(cursorListas.getString(8));
                bd_listas.setCaracteristicas(cursorListas.getString(9));
                bd_listas.setEstado(cursorListas.getString(10));
                bd_listas.setAdecuacion(cursorListas.getString(11));
                bd_listas.setTipoAdecuacion(cursorListas.getString(12));
                bd_listas.setCalidad(cursorListas.getString(13));
                bd_listas.setObservaciones(cursorListas.getString(14));
                bd_listas.setEsteCoordenada(cursorListas.getString(15));
                bd_listas.setNorteCoordenada(cursorListas.getString(16));
                verListas.add(bd_listas);

            } while (cursorListas.moveToNext());

        }

        cursorListas.close();
        return verListas ;

    }





}

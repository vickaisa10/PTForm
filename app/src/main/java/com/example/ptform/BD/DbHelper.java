package com.example.ptform.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "bdregistro.db";
    public static final String TABLE_REGISTRO = "tb_registro";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Con etsa estrcutura se crea la base de datos y la tabla correspondiente
      sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_REGISTRO + "(" +
              "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
              "elabora VARCHAR(40) NOT NULL," +
              "fecha TEXT NOT NULL," +
              "expediente VARCHAR(20) NOT NULL," +
              "operador VARCHAR(100) NOT NULL," +
              "proyecto VARCHAR(200) NOT NULL,"+
              "tipoInfraestructura VARCHAR(50) NOT NULL,"+
              "idInfraestructura VARCHAR(20) NOT NULL,"+
              "caracteristicas VARCHAR(255) NOT NULL,"+
              "estado TEXT NOT NULL,"+
              "adecuacion TEXT NOT NULL,"+
              "tipoAdecuacion VARCHAR(50) NOT NULL,"+
              "calidad TEXT NOT NULL,"+
              "observaciones VARCHAR(255) NOT NULL,"+
              "esteCoordenada VARCHAR NOT NULL,"+
              "norteCoordenada VARCHAR NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

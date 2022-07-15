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
              "elabora TEXT NOT NULL," +
              "proyecto1 TEXT NOT NULL," +
              "fecha TEXT NOT NULL," +
              "expediente TEXT NOT NULL," +
              "operador TEXT NOT NULL," +
              "proyecto TEXT NOT NULL,"+
              "tipoInfraestructura TEXT NOT NULL,"+
              "idInfraestructura TEXT NOT NULL,"+
              "caracteristicas VARCHAR NOT NULL,"+
              "estado TEXT NOT NULL,"+
              "adecuacion TEXT NOT NULL,"+
              "tipoAdecuacion TEXT NOT NULL,"+
              "calidad TEXT NOT NULL,"+
              "observaciones VARCHAR NOT NULL,"+
              "esteCoordenada VARCHAR NOT NULL,"+
              "norteCoordenada VARCHAR NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

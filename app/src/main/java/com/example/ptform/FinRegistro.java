package com.example.ptform;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.ptform.BD.DbHelper;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;

public class FinRegistro extends AppCompatActivity {

    Button maps, nuevo, exportar;
    int contador=0;

    public static final String TABLE_REGISTRO = "tb_registro";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_registro);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(ContextCompat.checkSelfPermission(FinRegistro.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(FinRegistro.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }

        nuevo = (Button) findViewById(R.id.btnNuevo);

        maps = (Button) findViewById(R.id.btnVisualizar);

        exportar = (Button) findViewById(R.id.btnExportar);

        exportar.setOnClickListener(v -> exportarCSV());

        nuevo.setOnClickListener(view -> {
            Intent punt = new Intent(FinRegistro.this, Levantamiento.class);
            startActivity(punt);
        });

        maps.setOnClickListener(view -> {
            Intent map = new Intent(FinRegistro.this, Maps.class);
            startActivity(map);
        });

    }

    private void exportarCSV() {
        try {


       //Exporto los datos a excel , la ubicación del archivo esta en data dentro del paquete de la aplicación en la carpeta android
        Workbook workbook = new HSSFWorkbook();
        Cell cell;
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        Sheet sheet;
        sheet = workbook.createSheet("Lista de registros");
        Row row;
        row= sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue("ELABORA");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue("PROYECTO 1");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3);
            cell.setCellValue("FECHA");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4);
            cell.setCellValue("EXPEDIENTE");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5);
            cell.setCellValue("OPERADOR");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(6);
            cell.setCellValue("PROYECTO");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(7);
            cell.setCellValue("TIPO INFRAESTRUCTURA");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(8);
            cell.setCellValue("ID INFRAESTRUCTURA");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(9);
            cell.setCellValue("CARACTERISTICAS");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(10);
            cell.setCellValue("ESTADO");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(11);
            cell.setCellValue("ADECUACION");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(12);
            cell.setCellValue("TIPO DE ADECUACION");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(13);
            cell.setCellValue("CALIDAD");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(14);
            cell.setCellValue("OBSERVACIONES");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(15);
            cell.setCellValue("COORDENADA ESTE");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(16);
            cell.setCellValue("COORDENADA NORTE");
            cell.setCellStyle(cellStyle);

        DbHelper dbHelper = new DbHelper(FinRegistro.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursorListas;

        cursorListas = db.rawQuery("SELECT * FROM "+TABLE_REGISTRO + " WHERE id ORDER BY id ASC" ,null);
        if(cursorListas.moveToFirst()){

            do {
                contador++;
                row= sheet.createRow(contador);

                cell = row.createCell(0);
                cell.setCellValue(cursorListas.getString(0));
                cell = row.createCell(1);
                cell.setCellValue(cursorListas.getString(1));
                cell = row.createCell(2);
                cell.setCellValue(cursorListas.getString(2));
                cell = row.createCell(3);
                cell.setCellValue(cursorListas.getString(3));
                cell = row.createCell(4);
                cell.setCellValue(cursorListas.getString(4));
                cell = row.createCell(5);
                cell.setCellValue(cursorListas.getString(5));
                cell = row.createCell(6);
                cell.setCellValue(cursorListas.getString(6));
                cell = row.createCell(7);
                cell.setCellValue(cursorListas.getString(7));
                cell = row.createCell(8);
                cell.setCellValue(cursorListas.getString(8));
                cell = row.createCell(9);
                cell.setCellValue(cursorListas.getString(9));
                cell = row.createCell(10);
                cell.setCellValue(cursorListas.getString(10));
                cell = row.createCell(11);
                cell.setCellValue(cursorListas.getString(11));
                cell = row.createCell(12);
                cell.setCellValue(cursorListas.getString(12));
                cell = row.createCell(13);
                cell.setCellValue(cursorListas.getString(13));
                cell = row.createCell(14);
                cell.setCellValue(cursorListas.getString(14));
                cell = row.createCell(15);
                cell.setCellValue(cursorListas.getString(15));
                cell = row.createCell(16);
                cell.setCellValue(cursorListas.getString(16));

            } while (cursorListas.moveToNext());
        }

        cursorListas.close();

        File file = new File(getExternalFilesDir(null),"Registros.xls");
        FileOutputStream outputStream;

        try {

            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            Toast.makeText(this, "Exporte exitoso", Toast.LENGTH_SHORT).show();

        }catch (Exception e){ }
        }catch (Exception e){ }
    }
}
package com.example.ptform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ptform.BD.Adaptador_Lista;
import com.example.ptform.BD.BD_Listas;
import com.example.ptform.BD.DbHelper;
import com.example.ptform.BD.DbRegistro;

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
import java.util.ArrayList;

public class VisualizarAntiguos extends AppCompatActivity {

    RecyclerView recyclerLista ;
    ArrayList<BD_Listas>verLista;

    int contador=0;

    public static final String TABLE_REGISTRO = "tb_registro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_antiguos);

        if(ContextCompat.checkSelfPermission(VisualizarAntiguos.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(VisualizarAntiguos.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }

        recyclerLista = findViewById(R.id.recyclerLista);
        mostrarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void  mostrarLista(){

        //Se muestran los datos guardados en un recyclerview

        recyclerLista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DbRegistro dbRegistro = new DbRegistro(VisualizarAntiguos.this);
        verLista = new ArrayList<>();
        Adaptador_Lista adaptador_lista = new Adaptador_Lista( VisualizarAntiguos.this, dbRegistro.mostrarListas());
        recyclerLista.setAdapter(adaptador_lista);

    }

    private void exportarCSV() {
        try {

            /*Exporto los datos a excel , la ubicación del archivo esta en data dentro del paquete
            de la aplicación en la carpeta android*/

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

            DbHelper dbHelper = new DbHelper(VisualizarAntiguos.this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        int id = menu.getItemId();

        if(id == R.id.exp){
            exportarCSV();

            return true;
        }

        return super.onOptionsItemSelected(menu);
    }
}
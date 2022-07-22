package com.example.ptform

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File
import java.io.OutputStream

class TomarFotografia : AppCompatActivity() {

    private lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomar_fotografia)

        val btnTakePicture = findViewById<Button>(R.id.btnTomarFoto)
        val btnSave = findViewById<Button>(R.id.btnRegistrarPunto)

        btnSave.setOnClickListener{
            saveImage()
            val save = Intent(this, FinRegistro::class.java)
            startActivity(save)
        }

        btnTakePicture.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    createPhotoFile()
                    val photoUri: Uri = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID +".fileprovider", file)
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            startForResult.launch(intent)
        }
    }

    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("PT_", ".jpg", dir)
    }

    private fun saveImage() {
        //Variable que guarda la función que crea un contenedor para la imagen
        val content = createContent()

        //Variable que guarda una función que guarda el contenedor que se creo antes
        val uri = save(content)

        //Función para limpiar el contenedor
        clearContent(content, uri)

        //Se crea un mensaje que le avise al usuario que efectivamente se pudo guardar la imagen en la galeria
        Toast.makeText(this, "La imagen fue guardada exitosamente en la Galeria",
        Toast.LENGTH_SHORT).show()
    }

    private fun createContent(): ContentValues {
        val fileName = file.name
        val typeFile = "image/jpg"
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.Files.FileColumns.MIME_TYPE, typeFile)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
    }

    private fun save(content: ContentValues): Uri {
        var outputStream:OutputStream?
        var uri:Uri?
        application.contentResolver.also { resolver ->
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
            outputStream = resolver.openOutputStream(uri!!)
        }
        outputStream.use { output ->
            getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, output)
        }
        return uri!!
    }

    private fun clearContent(content: ContentValues, uri: Uri) {
        content.clear()
        content.put(MediaStore.MediaColumns.IS_PENDING, 0)
        contentResolver.update(uri,content, null, null)
    }

    private fun getBitmap():Bitmap {
        return BitmapFactory.decodeFile(file.toString())
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
            if(result.resultCode == Activity.RESULT_OK){
                val imageBitmap = getBitmap()
                val imageView = findViewById<ImageView>(R.id.imageView2)
                imageView.setImageBitmap(imageBitmap)
        }
    }

}
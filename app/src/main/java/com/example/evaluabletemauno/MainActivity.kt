package com.example.evaluabletemauno

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val abrirConfig = findViewById<ImageButton>(R.id.botonConfig)
        abrirConfig.setOnClickListener {
            val intent = Intent(this,ConfiguracionActivity::class.java)
            startActivity(intent)
        }

        val abrirJuego = findViewById<Button>(R.id.botonJuego)
        abrirJuego.setOnClickListener {
            val intent = Intent(this,JuegoActivity::class.java)
            startActivity(intent)
        }

        // Declarar y señalar boton
        val abrirURL = findViewById<Button>(R.id.botonURL)
        // Accion para abrir una URL con un boton
        abrirURL.setOnClickListener {
            val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://realjaen.com/"))
            startActivity(urlIntent)
        }

        // Declarar y señalar boton
        val abrirMaps = findViewById<Button>(R.id.botonMaps)
        // Accion para abrir aplicacion de Google Maps
        abrirMaps.setOnClickListener{
            val gmmIntentUri = Uri.parse("geo:37.77660707200881, -3.7887659085828433")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }

        // Declarar y señalar boton
        val abrirAlarma = findViewById<Button>(R.id.botonAlarma)
        // Accion para activar la alarma
        abrirAlarma.setOnClickListener {
            alarmaEnDosMinutos()
        }

        // Declarar y señalar boton
        val abrirLlamada = findViewById<Button>(R.id.botonLlamada)
        abrirLlamada.setOnClickListener {
           val intent = Intent(this,SegundoActivity::class.java)
            startActivity(intent)
        }

    }

    // Funcion que crea la alarma
    private fun alarmaEnDosMinutos(){
        val horaActual = Calendar.getInstance()
        // Esto suma 2 minutos a la hora actual
        horaActual.add(Calendar.MINUTE, 2)

        // Recogemos la hora y minutos sumando esos dos minutos
        val hora = horaActual.get(Calendar.HOUR_OF_DAY)
        val minutos = horaActual.get(Calendar.MINUTE)

        // Crea el Intent para la alarma añadiendo los dos minutos
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma programada")
            putExtra(AlarmClock.EXTRA_HOUR, hora)
            putExtra(AlarmClock.EXTRA_MINUTES, minutos)
        }

        // Lanzamos el activity y lo coloco dentro de un bloque try catch por si existe algun tipo de error
        try {
            startActivity(intent)
            Toast.makeText(this, "La alarma sonará en 2 minutos", Toast.LENGTH_SHORT).show()
        }catch (e: ActivityNotFoundException){
            Toast.makeText(this,"Error en la configuracion de la alarma", Toast.LENGTH_SHORT).show()
        }


    }
}
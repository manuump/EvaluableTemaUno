package com.example.evaluabletemauno

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class ChistesActivity : AppCompatActivity() {

    private lateinit var chisteTextView: TextView
    private lateinit var cambiarChisteButton: Button
    private lateinit var leerChisteButton: Button
    private lateinit var textToSpeech: TextToSpeech

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chistes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        chisteTextView = findViewById(R.id.chisteTextView)
        cambiarChisteButton = findViewById(R.id.cambiarChisteButton)
        leerChisteButton = findViewById(R.id.leerChisteButton)

        // Inicializa TextToSpeech
        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale("es", "ES"))
            }
        }

        // Mostrar el primer chiste
        mostrarChiste()

        // Configura el botón para cambiar de chiste
        cambiarChisteButton.setOnClickListener {
            cambiarChiste()
        }

        // Configura el botón para leer el chiste
        leerChisteButton.setOnClickListener {
            leerChiste()
        }


    }

    private fun mostrarChiste() {
        chisteTextView.text = ListaChistes.chistes[currentIndex]
    }

    private fun cambiarChiste() {
        currentIndex = (currentIndex + 1) % ListaChistes.chistes.size
        mostrarChiste()
    }

    private fun leerChiste() {
        val chiste = ListaChistes.chistes[currentIndex]
        textToSpeech.speak(chiste, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
    }
}
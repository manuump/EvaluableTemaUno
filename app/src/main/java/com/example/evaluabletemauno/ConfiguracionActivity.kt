package com.example.evaluabletemauno

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfiguracionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_configuracion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.spinner)
        val button: Button = findViewById(R.id.button)
        val toggleButton: ToggleButton = findViewById(R.id.toggleButton)

        val options = arrayOf("Opción 1", "Opción 2", "Opción 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        button.setOnClickListener {
            Toast.makeText(this, "¡Botón pulsado!", Toast.LENGTH_SHORT).show()
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            val status =
                if (isChecked) "Activado"
                else "Desactivado"
            Toast.makeText(this, "Estado: $status", Toast.LENGTH_SHORT).show()
        }
    }
}

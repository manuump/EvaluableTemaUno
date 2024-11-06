package com.example.evaluabletemauno

import android.content.Intent
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SegundoActivity : AppCompatActivity() {

    private val SOLICITUD_LLAMADA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_segundo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val numeroTelefono = findViewById<EditText>(R.id.editNumTlf)
        val abrirLlamada = findViewById<Button>(R.id.botonLlamar)

        abrirLlamada.setOnClickListener {
            val tlf = numeroTelefono.text.toString().trim()
            if (tlf.isNotEmpty()){
                marcarLlamada(tlf)
            }else{
                Toast.makeText(this, "Por favor, ingrese un número de teléfono", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun marcarLlamada(numeroTlf: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                llamarTlf(numeroTlf)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), SOLICITUD_LLAMADA)
            }
        } else {
            llamarTlf(numeroTlf)
        }
    }

    private fun llamarTlf(numeroTlf: String) {
        val callIntent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$numeroTlf")
        }
        if (callIntent.resolveActivity(packageManager) != null) {
            startActivity(callIntent)
        } else {
            Toast.makeText(this, "No hay aplicación de teléfono disponible", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SOLICITUD_LLAMADA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val editTextPhoneNumber = findViewById<EditText>(R.id.editNumTlf)
                val numeroTlf = editTextPhoneNumber.text.toString().trim()
                if (numeroTlf.isNotEmpty()) {
                    llamarTlf(numeroTlf)
                }
            } else {
                Toast.makeText(this, "Permiso para llamadas no concedido", Toast.LENGTH_SHORT).show()
            }
        }
    }



}
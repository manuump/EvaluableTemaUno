package com.example.evaluabletemauno

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class JuegoActivity : AppCompatActivity() {

    private lateinit var imagenMoneda: ImageView
    private lateinit var comenzarCaraCruz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_juego)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imagenMoneda = findViewById(R.id.imageViewMoneda)
        comenzarCaraCruz = findViewById(R.id.botonTirarMoneda)

        comenzarCaraCruz.setOnClickListener {
            animacionMoneda()
        }
    }

    private fun animacionMoneda() {
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.coin_flip)

        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {

                val result =
                    if ((0..1).random() == 0) "Cara"
                    else "Cruz"


                val imageResource =
                    if (result == "Cara") R.drawable.monedacara
                    else R.drawable.monedacruz
                    imagenMoneda.setImageResource(imageResource)

                Toast.makeText(this@JuegoActivity, result, Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        imagenMoneda.startAnimation(rotateAnimation)
    }
}
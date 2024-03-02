package com.example.examen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditarSistemaSolar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_sistema_solar)
        val nombre = intent.getStringExtra("nombre")
        val localizacion = intent.getStringExtra("localizacion")
        val edad = intent.getLongExtra("edad", 0)
        val tipo_estrella = intent.getStringExtra("tipo_estrella")
        val radio = intent.getDoubleExtra("radio", 0.0)


        val inputNombre = findViewById<EditText>(R.id.input_editar_nombre_sistema)
        inputNombre.hint = nombre
        val inputLocalizacion = findViewById<EditText>(R.id.input_editar_localizacion_sistema)
        inputLocalizacion.hint = localizacion
        val inputEdad = findViewById<EditText>(R.id.input_editar_edad_sistema)
        inputEdad.hint = edad.toString()
        val inputTipoEstrella = findViewById<EditText>(R.id.input_editar_tipoEstrella_sistema)
        inputTipoEstrella.hint = tipo_estrella
        val inputRadio = findViewById<EditText>(R.id.input_editar_radio_sistema)
        inputRadio.hint = radio.toString()

        val botonAceptar = findViewById<Button>(R.id.btn_aceptar_editar_sistema)
        botonAceptar.setOnClickListener {

            val nombreModificado = if (inputNombre.text.isNotEmpty()) {
                inputNombre.text.toString()
            } else {
                nombre
            }
            val localizacionModificada = if (inputLocalizacion.text.isNotEmpty()) {
                inputLocalizacion.text.toString()
            } else {
                localizacion
            }
            val edadModificada = if (inputEdad.text.isNotEmpty()) {
                inputEdad.text.toString().toLong()
            } else {
                edad
            }
            val tipoEstrellaModificada = if (inputTipoEstrella.text.isNotEmpty()) {
                inputTipoEstrella.text.toString()
            } else {
                tipo_estrella
            }
            val radioModificado = if (inputRadio.text.isNotEmpty()) {
                inputRadio.text.toString().toDouble()
            } else {
                radio
            }

            if (nombreModificado != null && localizacionModificada != null && edadModificada
                != null && tipoEstrellaModificada != null && radioModificado != null
            ) {
                devolverRespuesta(
                    nombreModificado,
                    localizacionModificada,
                    edadModificada,
                    tipoEstrellaModificada,
                    radioModificado
                )
            }
        }
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_editar_sistema)
        botonCancelar.setOnClickListener {
            setResult(
                RESULT_OK,
                null
            )
            finish()
        }
    }

    fun devolverRespuesta(
        nombre: String,
        localizacion: String,
        edad: Long,
        tipoEstrella: String,
        radio: Double
    ) {
        //enviamos anteriormente, y ahora revolvemos o recibimos una respuesta
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", nombre)
        intentDevolverParametros.putExtra("localizacionModificada", localizacion)
        intentDevolverParametros.putExtra("edadModificada", edad)
        intentDevolverParametros.putExtra("tipoEstrellaModificada", tipoEstrella)
        intentDevolverParametros.putExtra("radioModificado", radio)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}
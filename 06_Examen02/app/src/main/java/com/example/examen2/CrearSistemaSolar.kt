package com.example.examen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class CrearSistemaSolar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sistema_solar)

        val botonAceptar = findViewById<Button>(R.id.btn_aceptar_crear_sistema)
        botonAceptar.setOnClickListener {
            val inputNombre = findViewById<EditText>(R.id.input_crear_nombre_sistema)
            val inputLocalizacion = findViewById<EditText>(R.id.input_crear_localizacion_sistema)
            val inputEdad = findViewById<EditText>(R.id.input_crear_edad_sistema)
            val inputTipoEstrella = findViewById<EditText>(R.id.input_crear_tipoEstrella_sistema)
            val inputRadio = findViewById<EditText>(R.id.input_crear_radio_sistema)

            val nombreModificado = if (inputNombre.text.isNotEmpty()) {
                inputNombre.text.toString()
            } else {
                mostrarSnackbar("Llene el campo Nombre")
                null
            }
            val localizacionModificada = if (inputLocalizacion.text.isNotEmpty()) {
                inputLocalizacion.text.toString()
            } else {
                mostrarSnackbar("Llene el campo Localización")
                null
            }
            val edadModificada = if (inputEdad.text.isNotEmpty()) {
                inputEdad.text.toString().toLong()
            } else {
                mostrarSnackbar("Llene el campo Edad")
                null
            }
            val tipoEstrellaModificada = if (inputTipoEstrella.text.isNotEmpty()) {
                inputTipoEstrella.text.toString()
            } else {
                mostrarSnackbar("Llene el campo Tipo de Estrella")
                null
            }
            val radioModificado = if (inputRadio.text.isNotEmpty()) {
                inputRadio.text.toString().toDouble()
            } else {
                mostrarSnackbar("Llene el campo Radio")
                null
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
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_crear_sistema)
        botonCancelar.setOnClickListener {
            finish()
        }
    }
    fun devolverRespuesta(
        nombre: String,
        localizacion: String,
        edad: Long,
        tipo_estrella: String,
        radio: Double
    ) {

        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreSistema", nombre)
        intentDevolverParametros.putExtra("localizacionSistema", localizacion)
        intentDevolverParametros.putExtra("edadSistema", edad)
        intentDevolverParametros.putExtra("tipoEstrellaSistema", tipo_estrella)
        intentDevolverParametros.putExtra("radioSistema", radio)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_crear_sistema),
            texto,
            Snackbar.LENGTH_LONG
        )
        snack.show()
    }
}
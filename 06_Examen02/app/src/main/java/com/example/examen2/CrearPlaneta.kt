package com.example.examen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class CrearPlaneta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_planeta)

        val botonAceptar = findViewById<Button>(R.id.btn_aceptar_crear_planeta)
        botonAceptar.setOnClickListener {
            val inputNombre = findViewById<EditText>(R.id.input_crear_nombre_planeta)
            val inputDiametro = findViewById<EditText>(R.id.input_crear_diametro_planeta)
            val inputMasa = findViewById<EditText>(R.id.input_crear_masa_planeta)
            val inputEdad = findViewById<EditText>(R.id.input_crear_edad_planeta)
            val checkBox_habitable = findViewById<CheckBox>(R.id.chb_crear_habitable_planeta)


            val nombreModificado = if (inputNombre.text.isNotEmpty()) {
                inputNombre.text.toString()
            } else {
                mostrarSnackbar("Llene el campo Nombre")
                null
            }
            val diametroModificado = if (inputDiametro.text.isNotEmpty()) {
                inputDiametro.text.toString().toDouble()
            } else {
                mostrarSnackbar("Llene el campo Diámetro")
                null
            }
            val masaModificado = if (inputMasa.text.isNotEmpty()) {
                inputMasa.text.toString().toDouble()
            } else {
                mostrarSnackbar("Llene el campo Masa")
                null
            }
            val edadModificada = if (inputEdad.text.isNotEmpty()) {
                inputEdad.text.toString().toLong()
            } else {
                mostrarSnackbar("Llene el campo Edad")
                null
            }
            val habitable = checkBox_habitable.isChecked

            if (nombreModificado != null && diametroModificado != null && masaModificado
                != null && edadModificada != null
            ) {
                devolverRespuesta(
                    nombreModificado,
                    diametroModificado,
                    masaModificado,
                    habitable,
                    edadModificada
                )
            }
        }
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_crear_planeta)
        botonCancelar.setOnClickListener {
            finish()
        }
    }

    fun devolverRespuesta(
        nombre: String,
        diametro: Double,
        masa: Double,
        habitable: Boolean,
        edad: Long
    ) {
        //enviamos anteriormente, y ahora revolvemos o recibimos una respuesta
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombrePlaneta", nombre)
        intentDevolverParametros.putExtra("diametroPlaneta", diametro)
        intentDevolverParametros.putExtra("masaPlaneta", masa)
        intentDevolverParametros.putExtra("habitablePlaneta", habitable)
        intentDevolverParametros.putExtra("edadPlaneta", edad)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_crearPlaneta),
            texto,
            Snackbar.LENGTH_LONG
        )
        snack.show()
    }
}
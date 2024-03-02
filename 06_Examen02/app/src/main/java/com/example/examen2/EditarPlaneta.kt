package com.example.examen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class EditarPlaneta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_planeta)

        val planetaSeleccionado =findViewById<TextView>(R.id.txt_editar_planeta)
        planetaSeleccionado.setText(PlanetasDashboard.arregloPlanetas[PlanetasDashboard.posicion_planetas].nombre)

        val nombre = intent.getStringExtra("nombre")
        val diametro = intent.getDoubleExtra("diametro",0.0).toString().toDouble()
        val masa = intent.getDoubleExtra("masa",0.0).toString().toDouble()
        val habitable = intent.getBooleanExtra("habitable", false)
        val edad = intent.getLongExtra("edad",0).toString().toLong()

        val inputNombre = findViewById<EditText>(R.id.input_editar_nombre_planeta)
        inputNombre.hint = nombre
        val inputDiametro = findViewById<EditText>(R.id.input_editar_diametro_planeta)
        inputDiametro.hint = diametro.toString()
        val inputMasa = findViewById<EditText>(R.id.input_editar_masa_planeta)
        inputMasa.hint = masa.toString()
        val inputEdad = findViewById<EditText>(R.id.input_editar_edad_planeta)
        inputEdad.hint = edad.toString()

        val checkBoxHabitable = findViewById<CheckBox>(R.id.chb_editar_habitable_planeta)
        checkBoxHabitable.isChecked = habitable

        val botonAceptar = findViewById<Button>(R.id.btn_editar_aceptar_planeta)
        botonAceptar.setOnClickListener {

            val nombreModificado = if (inputNombre.text.isNotEmpty()) {
                inputNombre.text.toString()
            } else {
                nombre
            }
            val diametroModificado = if (inputDiametro.text.isNotEmpty()) {
                inputDiametro.text.toString().toDouble()
            } else {
                diametro
            }
            val masaModificada = if (inputMasa.text.isNotEmpty()) {
                inputMasa.text.toString().toDouble()
            } else {
                masa
            }
            val edadModificada = if (inputEdad.text.isNotEmpty()) {
                inputEdad.text.toString().toLong()
            } else {
                edad
            }


            val habitableModificado = checkBoxHabitable.isChecked

            if (nombreModificado != null && diametroModificado != null && masaModificada
                != null && habitableModificado != null && edadModificada != null
            ) {
                devolverRespuesta(
                    nombreModificado,
                    diametroModificado,
                    masaModificada,
                    habitableModificado,
                    edadModificada
                )
            }

        }
        val botonCancelar = findViewById<Button>(R.id.btn_editar_cancelar_planeta)
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
        diametro: Double,
        masa: Double,
        habitable: Boolean,
        edad: Long,
    ) {
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
}
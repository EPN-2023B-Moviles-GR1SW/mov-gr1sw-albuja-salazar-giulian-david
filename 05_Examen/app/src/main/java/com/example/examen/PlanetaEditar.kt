package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.examen.DAO.PlanetaDAO
import com.google.android.material.snackbar.Snackbar

class PlanetaEditar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planeta_editar)


        val intent = intent
        val id = intent.getIntExtra("id", 1)

        val planeta = PlanetaDAO().getById(id)!!


        val nombre = findViewById<EditText>(R.id.input_planeta)
        val galaxia = findViewById<EditText>(R.id.input_galaxia)
        val edad = findViewById<EditText>(R.id.input_edad)
        val radio = findViewById<EditText>(R.id.input_radio)
        val descubierto = findViewById<Switch>(R.id.input_descubierto)

        nombre.setText(planeta.nombre)
        galaxia.setText(planeta.galaxia)
        edad.setText(planeta.edad.toString())
        radio.setText(planeta.radio.toString())
        descubierto.isChecked = (planeta.descubierto == "Si")

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_planeta)
        botonActualizar.setOnClickListener {
            planeta.nombre = nombre.text.toString()
            planeta.galaxia = galaxia.text.toString()
            planeta.edad = edad.text.toString().toInt()
            planeta.radio = radio.text.toString().toDouble()
            planeta.descubierto = if (descubierto.isChecked) "Si" else "No"

            PlanetaDAO().update(planeta)
            mostrarSnackbar("Planeta Actualizado")
        }
        val botonVolver = findViewById<Button>(R.id.btn_volver_3)
        botonVolver.setOnClickListener {
            finish()
        }
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_planeta_editar),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }
}
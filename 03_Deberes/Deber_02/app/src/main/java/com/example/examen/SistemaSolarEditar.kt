package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.examen.DAO.SistemaSolarDAO
import com.google.android.material.snackbar.Snackbar

class SistemaSolarEditar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistema_solar_editar)


        val intent = intent
        val id = intent.getIntExtra("id", 1)

        val sistemaSolar = SistemaSolarDAO().getById(id)!!


        val nombre = findViewById<EditText>(R.id.input_nombre)
        val rotacion = findViewById<EditText>(R.id.input_rotacion)
        val edad = findViewById<EditText>(R.id.input_edad_sistema)
        val descubierto = findViewById<Switch>(R.id.input_descubierto_sistema)

        nombre.setText(sistemaSolar.nombre)
        rotacion.setText(sistemaSolar.rotacion)
        edad.setText(sistemaSolar.edad.toString())
        descubierto.isChecked = (sistemaSolar.descubierto)

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_sistema)
        botonActualizar.setOnClickListener {
            sistemaSolar.nombre = nombre.text.toString()
            sistemaSolar.rotacion = rotacion.text.toString()
            sistemaSolar.edad = edad.text.toString().toInt()
            sistemaSolar.descubierto = (descubierto.isChecked)

            SistemaSolarDAO().update(sistemaSolar)
            mostrarSnackbar("Sistema solar actualizado")
        }
        val botonVolver = findViewById<Button>(R.id.btn_volver)
        botonVolver.setOnClickListener {
            finish()
        }
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_sistema_editar),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }
}
package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.example.examen.DAO.SistemaSolarDAO
import com.example.examen.ENTIDAD.SistemaSolar
import com.google.android.material.snackbar.Snackbar

class SistemaSolaresVer : AppCompatActivity() {
    val arregloSistemas = SistemaSolarDAO().getAll()
    var posicionItemSeleccionado = 0
    var idSistemaSeleccionado = 0
    lateinit var listView: ListView
    lateinit var adaptador: ArrayAdapter<SistemaSolar>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistema_solares_ver)
        listView = findViewById<ListView>(R.id.lv_sistema_ver)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloSistemas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearSistema = findViewById<Button>(R.id.btn_crear_sistema)
        botonCrearSistema.setOnClickListener {
            crearSistema()
        }

        registerForContextMenu(listView)
    }

    fun crearSistema() {
        val sistemaSolar = SistemaSolar(
            null,
            "Nombre",
            "Rotacion",
            true,
            0
        )
        SistemaSolarDAO().create(sistemaSolar)
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_sistema, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion

        val sistemaSeleccionado = arregloSistemas.get(posicion)
        idSistemaSeleccionado = sistemaSeleccionado.id!!
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_sistema -> {
                irActividadConId(SistemaSolarEditar::class.java, idSistemaSeleccionado)
                return true
            }

            R.id.mi_eliminar_sistema -> {
                abrirDialogo()
                return true
            }

            R.id.mi_ver_planetas -> {
                irActividadConId(PlanetasVer::class.java, idSistemaSeleccionado)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun irActividadConId(
        clase: Class<*>,
        id: Int
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_sistema_ver),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Está seguro de eliminar?")
        builder.setPositiveButton("Aceptar") { _, _ ->
            SistemaSolarDAO().deleteById(idSistemaSeleccionado)
            mostrarSnackbar("id:$idSistemaSeleccionado ha sido eliminado")
            adaptador.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }
}
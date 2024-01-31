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
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.examen.DAO.PlanetaDAO
import com.example.examen.DAO.SistemaSolarDAO
import com.example.examen.ENTIDAD.Planeta
import com.example.examen.ENTIDAD.SistemaSolar
import com.google.android.material.snackbar.Snackbar

class PlanetasVer : AppCompatActivity() {

    var arregloPlanetas = arrayListOf<Planeta>()
    var sistemaSolar: SistemaSolar = SistemaSolar()
    var posicionItemSeleccionado = 0
    var idPlanetaSeleccionado = 0
    lateinit var listView: ListView
    lateinit var adaptador: ArrayAdapter<Planeta>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planetas_ver)

        val intent = intent
        val id = intent.getIntExtra("id", 1)
        sistemaSolar = SistemaSolarDAO().getById(id)!!
        arregloPlanetas = sistemaSolar.listaPlanetas

        val nombreSistema = findViewById<TextView>(R.id.tv_nombre_sistema)
        nombreSistema.text = "${sistemaSolar.nombre}"

        listView = findViewById<ListView>(R.id.lv_planeta_ver)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloPlanetas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearPlaneta = findViewById<Button>(R.id.btn_crear_planeta)
        botonCrearPlaneta.setOnClickListener {
            crearPlaneta()
        }

        registerForContextMenu(listView)
    }
    fun crearPlaneta() {
        val planeta = Planeta(
            null,
            "Planeta",
            "Galaxia",
            0,
            0.0,
            "Si",
            sistemaSolar
        )
        PlanetaDAO().create(planeta)
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_planeta, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion

        val planetaSeleccionado = arregloPlanetas[posicion]

        idPlanetaSeleccionado = planetaSeleccionado.id!!
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_planeta -> {
                irActividadConId(PlanetaEditar::class.java, idPlanetaSeleccionado)
                return true
            }

            R.id.mi_eliminar_planeta -> {
                abrirDialogo()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
        val botonVolver = findViewById<Button>(R.id.btn_volver_4)
        botonVolver.setOnClickListener {
            val intent = Intent(this, SistemaSolaresVer::class.java)
            startActivity(intent)
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
            findViewById(R.id.id_layout_planeta_ver),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Está seguro de eliminar?")
        builder.setPositiveButton(
            "Aceptar"
        ) { dialog, which ->
            PlanetaDAO().deleteById(idPlanetaSeleccionado)
            mostrarSnackbar(" id:${idPlanetaSeleccionado} ha sido eliminado")
            adaptador.notifyDataSetChanged()
        }
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }
}
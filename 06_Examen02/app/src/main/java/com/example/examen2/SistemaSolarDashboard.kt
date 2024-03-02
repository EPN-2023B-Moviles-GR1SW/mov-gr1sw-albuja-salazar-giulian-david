package com.example.examen2

import android.app.Activity
import android.content.DialogInterface
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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.examen2.firestore_database.FireStoreSistemaSolar
import com.example.examen2.modelos.SistemaSolar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SistemaSolarDashboard : AppCompatActivity() {
    private val firestoreSistema = FireStoreSistemaSolar()
    lateinit var adaptador: ArrayAdapter<SistemaSolar>
    val callbackCrearSistema =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    val nuevoSistema = SistemaSolar(
                        data?.getStringExtra("nombreSistema").toString(),
                        data?.getStringExtra("localizacionSistema").toString(),
                        data?.getLongExtra("edadSistema", 0).toString().toLong(),
                        data?.getStringExtra("tipoEstrellaSistema").toString(),
                        data?.getDoubleExtra("radioSistema",0.0).toString().toDouble(),
                        arrayListOf()
                    )
                    firestoreSistema.crearSistema(nuevoSistema)
                    arregloSistemas.add( nuevoSistema)
                    adaptador.notifyDataSetChanged()
                }
            }
        }
    val callbackContenidoIntentEditarSistema =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    //Lógica para modificar los datos del arreglo
                    val nombre = data?.getStringExtra("nombreModificado").toString()
                    val localizacion = data?.getStringExtra("localizacionModificada").toString()
                    val edad = data?.getLongExtra("edadModificada",0).toString().toLong()
                    val tipoEstrella = data?.getStringExtra("tipoEstrellaModificada").toString()
                    val radio = data?.getDoubleExtra("radioModificado",0.0).toString().toDouble()

                    firestoreSistema.editarSistema(
                        SistemaSolar(
                            nombre,
                            localizacion,
                            edad,
                            tipoEstrella,
                            radio,
                            arrayListOf()
                        )
                    )

                    arregloSistemas[posicion_sistema].nombre = data?.getStringExtra("nombreModificado").toString()
                    arregloSistemas[posicion_sistema].localizacion = data?.getStringExtra("localizacionModificada").toString()
                    arregloSistemas[posicion_sistema].edad = data?.getLongExtra("edadModificada",0).toString().toLong()
                    arregloSistemas[posicion_sistema].tipo_estrella = data?.getStringExtra("tipoEstrellaModificada").toString()
                    arregloSistemas[posicion_sistema].radio = data?.getDoubleExtra("radioModificado",0.0).toString().toDouble()
                    adaptador.notifyDataSetChanged()
                    mostrarSnackbar("Sistema modificado exitosamente")
                }
            }
        }
    val callbackPlanetas =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    adaptador.notifyDataSetChanged()
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistema_solar_dashboard)

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloSistemas
        )
        val listView = findViewById<ListView>(R.id.lv_sistema_solar)
        listView.adapter = adaptador

        val db = Firebase.firestore
        val sistemasExistentes = db.collection("sistemasSolares")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()

        sistemasExistentes
            .get()
            .addOnSuccessListener {result ->
                for (document in result) {
                    anadirAArregloSistema(document)
                    adaptador.notifyDataSetChanged()
                }

            }
            .addOnFailureListener {
                // Errores
            }
        adaptador.notifyDataSetChanged()



        val botonCrearSistema = findViewById<Button>(R.id.btn_crear_sistema)
        botonCrearSistema
            .setOnClickListener {
                anadirSistema(adaptador)
                mostrarSnackbar("El sistema solar ha sido creado")
            }
        registerForContextMenu(listView)

    }

    fun anadirSistema(
        adaptador: ArrayAdapter<SistemaSolar>
    ) {
        abrirActividadCrearSistema(
            CrearSistemaSolar::class.java
        )
        adaptador.notifyDataSetChanged()
    }
    fun anadirAArregloSistema(
        sistema: QueryDocumentSnapshot
    ){

        val id = sistema.id


        val nuevoSistema = SistemaSolar(
            id,
            sistema.data["nombre"] as String,
            sistema.data["localizacion"] as String,
            sistema.data["edad"] as Long,
            sistema.data["tipo_estrella"] as String,
            sistema.data["radio"] as Double,
            arrayListOf()
        )

        arregloSistemas.add(nuevoSistema)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_sistema_solar, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicion_sistema = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_sistema -> {
                abrirActividadEditarSistema(
                    EditarSistemaSolar::class.java
                )
                return true
            }

            R.id.mi_eliminar_sistema -> {
                confirmarEliminacionDialogo(adaptador)
                return true
            }

            R.id.mi_ver_planetas -> {
                abrirActividadPlanetas(
                    PlanetasDashboard::class.java
                )
                return true
            }


            else -> super.onContextItemSelected(item)
        }
    }
    fun confirmarEliminacionDialogo(adaptador: ArrayAdapter<SistemaSolar>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                firestoreSistema.eliminarSistema()
                mostrarSnackbar("Sistema eliminado exitosamente")
                adaptador.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.lv_sistema_solar),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }
    fun limpiarArreglo() {
        arregloSistemas.clear()
    }

    fun abrirActividadCrearSistema(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)

        callbackCrearSistema.launch(intent)
    }

    fun abrirActividadEditarSistema(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(this, clase)

        //Enviar parámetros (solamente variables primitivas)
        val sistemaSeleccionado = arregloSistemas[posicion_sistema]

        //val planetaSeleccionad = BaseDeDatos.tablaPlaneta!!.consultarPlanetaPorID(idItemSeleccionado)
        intentExplicito.putExtra("nombre", sistemaSeleccionado.nombre)
        intentExplicito.putExtra("localizacion", sistemaSeleccionado.localizacion)
        intentExplicito.putExtra("edad", sistemaSeleccionado.edad)
        intentExplicito.putExtra("tipo_estrella", sistemaSeleccionado.tipo_estrella)
        intentExplicito.putExtra("radio", sistemaSeleccionado.radio)

        callbackContenidoIntentEditarSistema.launch(intentExplicito)
    }
    fun abrirActividadPlanetas(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)

        callbackPlanetas.launch(intent)
    }

    companion object {
        var arregloSistemas = arrayListOf<SistemaSolar>()
        var posicion_sistema = 0
    }
}
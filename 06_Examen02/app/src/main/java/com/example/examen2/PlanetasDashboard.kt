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
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.examen2.firestore_database.FireStorePlaneta
import com.example.examen2.modelos.Planeta
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlanetasDashboard : AppCompatActivity() {
    private val firestorePlaneta = FireStorePlaneta()
    lateinit var adaptador: ArrayAdapter<Planeta>
    val callbackCrearPlaneta =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    val nuevoPlaneta = Planeta(
                        data?.getStringExtra("nombrePlaneta").toString(),
                        data?.getDoubleExtra("diametroPlaneta",0.0).toString().toDouble(),
                        data?.getDoubleExtra("masaPlaneta",0.0).toString().toDouble(),
                        data?.getLongExtra("edadPlaneta",0).toString().toLong(),
                        data?.getBooleanExtra("habitablePlaneta", false).toString().toBoolean()
                    )

                    firestorePlaneta.crearPlaneta(nuevoPlaneta)
                    SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema].arregloPlanetas.add(
                        nuevoPlaneta
                    )
                    arregloPlanetas.add(nuevoPlaneta)
                    adaptador.notifyDataSetChanged()
                }
            }
        }
    val callbackContenidoEditarPlaneta =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    val nombre =
                        data?.getStringExtra("nombrePlaneta").toString()
                    val diametro =
                        data?.getDoubleExtra("diametroPlaneta",0.0).toString().toDouble()
                    val masa =
                        data?.getDoubleExtra("masaPlaneta",0.0).toString().toDouble()
                    val habitable =
                        data?.getBooleanExtra("habitablePlaneta", true).toString().toBoolean()
                    val edad =
                        data?.getLongExtra("edadPlaneta",0).toString().toLong()


                    firestorePlaneta.editarPlaneta(
                        Planeta(
                            nombre,
                            diametro,
                            masa,
                            edad,
                            habitable
                        )
                    )
                    //Lógica para modificar los datos del arreglo
                    arregloPlanetas[posicion_planetas].nombre = nombre
                    arregloPlanetas[posicion_planetas].diametro = diametro
                    arregloPlanetas[posicion_planetas].masa = masa
                    arregloPlanetas[posicion_planetas].esHabitable = habitable
                    arregloPlanetas[posicion_planetas].edad = edad

                    SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema]
                        .arregloPlanetas[posicion_planetas] =
                        arregloPlanetas[posicion_planetas]

                    adaptador.notifyDataSetChanged()
                    mostrarSnackbar("Planeta modificado exitosamente")
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planetas_dashboard)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloPlanetas
        )

        val listView = findViewById<ListView>(R.id.lv_planetas)
        listView.adapter = adaptador


        val db = Firebase.firestore
        val planetasExistentes = db.collection("sistemasSolares")
            .document(SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema].id)
            .collection("planetas")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()

        planetasExistentes
            .get()
            .addOnSuccessListener {
                // it => eso (lo que llegue)
                for (planetas in it){
                    planetas.id
                    anadirAArregloPlaneta(planetas)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // Errores
            }
        adaptador.notifyDataSetChanged()
        val sistemaSeleccionado = findViewById<TextView>(R.id.txt_planetas_sistema_seleccionado)
        sistemaSeleccionado.setText(SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema].nombre)

        val botonCrearPlaneta = findViewById<Button>(R.id.btn_crearPlaneta)
        botonCrearPlaneta
            .setOnClickListener {
                anadirPlaneta(adaptador)
            }
        val botonRegresar = findViewById<Button>(R.id.btn_regresarPlaneta)
        botonRegresar
            .setOnClickListener {
                finish()
            }
        registerForContextMenu(listView)

    }

    fun anadirAArregloPlaneta(
        planeta: QueryDocumentSnapshot
    ){
        val id = planeta.id
        val nuevoPlaneta = Planeta(
            id,
            planeta.data.get("nombre") as String,
            planeta.data.get("diametro") as Double,
            planeta.data.get("masa") as Double,
            planeta.data.get("edad") as Long,
            planeta.data.get("esHabitable") as Boolean
        )
        arregloPlanetas.add(nuevoPlaneta)
        SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema]
            .arregloPlanetas.add(nuevoPlaneta)
        //MainActivity.arregloPlanetas.add(nuevoHabitante)
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
        posicion_planetas = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_planeta -> {
                abrirActividadEditarPlaneta(
                    EditarPlaneta::class.java
                )
                return true
            }

            R.id.mi_eliminar_planeta -> {
                confirmarEliminacionDialogo(adaptador)
                return true
            }


            else -> super.onContextItemSelected(item)
        }
    }

    fun anadirPlaneta(
        adaptador: ArrayAdapter<Planeta>
    ) {
        abrirActividadCrearPlaneta(
            CrearPlaneta::class.java
        )
        adaptador.notifyDataSetChanged()
    }

    fun confirmarEliminacionDialogo(adaptador: ArrayAdapter<Planeta>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                firestorePlaneta.eliminarPlaneta()
                adaptador.notifyDataSetChanged()
                mostrarSnackbar("Planeta eliminado exitosamente")
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }


    fun abrirActividadCrearPlaneta(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)

        callbackCrearPlaneta.launch(intent)

    }

    fun abrirActividadEditarPlaneta(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(this, clase)
        //Enviar parámetros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", arregloPlanetas[posicion_planetas].nombre)
        intentExplicito.putExtra("diametro", arregloPlanetas[posicion_planetas].diametro)
        intentExplicito.putExtra("masa", arregloPlanetas[posicion_planetas].masa)
        intentExplicito.putExtra("habitable", arregloPlanetas[posicion_planetas].esHabitable)
        intentExplicito.putExtra(
            "edad",
            arregloPlanetas[posicion_planetas].edad
        )


        callbackContenidoEditarPlaneta.launch(intentExplicito)
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_planetas),
            texto,
            Snackbar.LENGTH_LONG
        )
        snack.show()
    }
    fun limpiarArreglo() {
        arregloPlanetas.clear()
    }

    companion object {
        var arregloPlanetas = arrayListOf<Planeta>()
        var posicion_planetas = 0
    }
}
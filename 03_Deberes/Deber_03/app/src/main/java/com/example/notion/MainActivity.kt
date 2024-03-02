package com.example.notion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.notion.baseDatos.BDMemoria

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonLista = findViewById<ImageButton>(R.id.btn_lista_1)
        botonLista
            .setOnClickListener {
                irActividad(ListaElementos::class.java)
            }
        BDMemoria.inicializarNotificaciones(this)
        startRecyclerView()
    }
    fun startRecyclerView(){
        val rv_notificaciones = findViewById<RecyclerView>(R.id.rv_notifications)
        val adaptador = AdaptadorNotificaciones(
            this,
            BDMemoria.arregloNotificaciones,
            rv_notificaciones
        )

        rv_notificaciones.adapter = adaptador
        rv_notificaciones.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_notificaciones.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad(
        clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
package com.example.notion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.notion.baseDatos.BDMemoria

class ListaElementos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_elementos)

        BDMemoria.inicializarElementos(this)
        startRecyclerView()

        val botonPerfil = findViewById<ImageButton>(R.id.btn_perfil_2)
        botonPerfil
            .setOnClickListener {
                irActividad(MainActivity::class.java)
            }

    }

    fun startRecyclerView(){
        val rv_elementos = findViewById<RecyclerView>(R.id.rv_elementos)
        val adaptador = AdaptadorElementos(
            this,
            BDMemoria.arregloElementos,
            rv_elementos
        )

        rv_elementos.adapter = adaptador
        rv_elementos.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_elementos.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
    fun irActividad(
        clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
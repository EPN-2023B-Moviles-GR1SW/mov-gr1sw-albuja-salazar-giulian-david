package com.example.examen2.firestore_database

import com.example.examen2.SistemaSolarDashboard
import com.example.examen2.modelos.SistemaSolar
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreSistemaSolar {
    private val db = FirebaseFirestore.getInstance()
    private val sistemasCollection = db.collection("sistemasSolares")

    fun crearSistema(nuevoSistema: SistemaSolar){
        val datosSistema = hashMapOf(
            "nombre" to nuevoSistema.nombre,
            "localizacion" to nuevoSistema.localizacion,
            "edad" to nuevoSistema.edad,
            "tipo_estrella" to nuevoSistema.tipo_estrella,
            "radio" to nuevoSistema.radio,
        )
        sistemasCollection
            .add(datosSistema)
            .addOnSuccessListener { documentReference ->
                val sistemaId = documentReference.id
                nuevoSistema.id = sistemaId
            }
            .addOnFailureListener {  }
    }

    fun eliminarSistema(){

        sistemasCollection.document(SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema].id)
            .delete()
            .addOnSuccessListener {
                SistemaSolarDashboard.arregloSistemas.removeAt(SistemaSolarDashboard.posicion_sistema)
            }
            .addOnFailureListener{  }
    }
    fun editarSistema(nuevoSistema: SistemaSolar){
        val datosSistema = hashMapOf(
            "nombre" to nuevoSistema.nombre,
            "localizacion" to nuevoSistema.localizacion,
            "edad" to nuevoSistema.edad,
            "tipo_estrella" to nuevoSistema.tipo_estrella,
            "radio" to nuevoSistema.radio,
        )
        sistemasCollection.document(SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema].id)
            .set(datosSistema)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }
}
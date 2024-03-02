package com.example.examen2.firestore_database

import com.example.examen2.PlanetasDashboard
import com.example.examen2.SistemaSolarDashboard
import com.example.examen2.modelos.Planeta
import com.google.firebase.firestore.FirebaseFirestore

class FireStorePlaneta {
    private val db = FirebaseFirestore.getInstance()
    private val planetasCollection = db.collection("sistemasSolares")
        .document(SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema].id)
        .collection("planetas")
    fun crearPlaneta(nuevoPlaneta: Planeta){
        val datosPlaneta = hashMapOf(
            "nombre" to nuevoPlaneta.nombre,
            "diametro" to nuevoPlaneta.diametro,
            "masa" to nuevoPlaneta.masa,
            "edad" to nuevoPlaneta.edad,
            "esHabitable" to nuevoPlaneta.esHabitable
        )
        planetasCollection
            .add(datosPlaneta)
            .addOnSuccessListener { documentReference ->
                val habitanteId = documentReference.id
                nuevoPlaneta.id = habitanteId
            }
            .addOnFailureListener {  }

    }
    fun eliminarPlaneta(){

        planetasCollection.document(PlanetasDashboard.arregloPlanetas[PlanetasDashboard.posicion_planetas].id)
            .delete()
            .addOnSuccessListener {
                SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema]
                    .arregloPlanetas.removeAt(PlanetasDashboard.posicion_planetas)

                PlanetasDashboard.arregloPlanetas.removeAt(PlanetasDashboard.posicion_planetas)
            }
            .addOnFailureListener{  }
    }
    fun editarPlaneta(nuevoPlaneta: Planeta){
        val datosPlaneta = hashMapOf(
            "nombre" to nuevoPlaneta.nombre,
            "diametro" to nuevoPlaneta.diametro,
            "masa" to nuevoPlaneta.masa,
            "edad" to nuevoPlaneta.edad,
            "esHabitable" to nuevoPlaneta.esHabitable,
        )
        planetasCollection.document(
            SistemaSolarDashboard.arregloSistemas[SistemaSolarDashboard.posicion_sistema]
                .arregloPlanetas[PlanetasDashboard.posicion_planetas].id
        )
            .set(datosPlaneta)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }
}
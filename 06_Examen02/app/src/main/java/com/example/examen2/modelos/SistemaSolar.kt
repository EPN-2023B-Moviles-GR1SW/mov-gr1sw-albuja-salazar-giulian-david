package com.example.examen2.modelos

class SistemaSolar (
    var id: String,
    var nombre: String,
    var localizacion: String,
    var edad: Long,
    var tipo_estrella: String,
    var radio: Double,
    var arregloPlanetas: ArrayList<Planeta>
) {
    constructor(
        nombre: String,
        localizacion: String,
        edad: Long,
        tipoEstrella: String,
        radio: Double,
        arregloPlanetas: ArrayList<Planeta>
    ) : this(
        "",
        nombre,
        localizacion,
        edad,
        tipoEstrella,
        radio,
        arregloPlanetas
    )

    override fun toString(): String {
        return "${nombre} - ${localizacion}"
    }

}
package com.example.examen2.modelos

class Planeta(
    var id: String,
    var nombre: String,
    var diametro: Double,
    var masa: Double,
    var edad: Long,
    var esHabitable: Boolean
) {

    constructor(
        nombre: String,
        diametro: Double,
        masa: Double,
        edad: Long,
        esHabitable: Boolean
    ):this(
        "",
        nombre,
        diametro,
        masa,
        edad,
        esHabitable
    )
    override fun toString(): String {
        return "${nombre}"
    }
}
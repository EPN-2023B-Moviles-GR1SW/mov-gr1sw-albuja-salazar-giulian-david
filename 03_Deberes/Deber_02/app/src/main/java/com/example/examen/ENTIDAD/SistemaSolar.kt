package com.example.examen.ENTIDAD

class SistemaSolar(
    var id: Int?,
    var nombre: String,
    var rotacion: String,
    var descubierto: String,
    var edad: Int,
    var listaPlanetas: ArrayList<Planeta> = arrayListOf()
) {
    constructor() : this(null, "", "", "", 0)

    override fun toString(): String {
        return "$nombre"
    }
}

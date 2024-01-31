package com.example.examen.ENTIDAD

class SistemaSolar(
    var id: Int?,
    var nombre: String,
    var rotacion: String,
    var descubierto: Boolean,
    var edad: Int,
    var listaPlanetas: ArrayList<Planeta> = arrayListOf()
) {
    constructor() : this(null, "", "", false, 0)

    override fun toString(): String {
        return "$nombre"
    }
}

package com.example.examen.ENTIDAD

class Planeta(
    var id: Int?,
    var nombre: String,
    var galaxia: String,
    var edad: Int,
    var radio: Double,
    var descubierto: String,
    var sistemaSolar: SistemaSolar = SistemaSolar()
) {
    constructor() : this(null, "", "", 0, 0.0, "")

    override fun toString(): String {
        return "$nombre - $galaxia"
    }
}
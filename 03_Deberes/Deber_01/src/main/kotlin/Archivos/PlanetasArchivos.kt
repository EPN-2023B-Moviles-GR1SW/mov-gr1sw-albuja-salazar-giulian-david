package Archivos

import Entities.Planeta
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

class PlanetasArchivos : Archivo() {
    private val planetas: MutableMap<Int, Planeta> = mutableMapOf()
    override var rutaArchivo : String = "src/main/kotlin/Datos/planetas.txt"

    init{
        cargarDatos()
    }
    override fun cargarDatos(){
        val file = File("src/main/kotlin/Datos/planetas.txt")
        var i : Int = 0
        if (file.exists()) {
            file.forEachLine {
                val separacion = it.split(",")
                if (separacion.size >= 5) {
                    val nombre = separacion[0]
                    val descubierto = separacion[1].toBoolean()
                    val radio = separacion[2].toFloat()
                    val fechaRegistro = Date(separacion[3].toLong())
                    val edad = separacion[4].toInt()
                    planetas[i++] = Planeta(nombre, descubierto, radio, fechaRegistro, edad)
                }
            }
        }
    }
    override fun guardarDatos() {
        val file = File(rutaArchivo)
        FileWriter(file, false).use { fileWriter ->
            BufferedWriter(fileWriter).use { out ->
                planetas.values.forEach {
                    out.write("${it.nombre},${it.descubierto},${it.radio},${it.fechaRegistro.time},${it.edad}\n")
                }
            }
        }
    }

    fun agregarPlaneta(planeta: Planeta) {
        planetas[planetas.size] = planeta
        guardarDatos()
    }

    fun obtenerPlanetaPorNombre(nombre: String): Planeta? {
        return planetas.values.find { it.nombre == nombre }
    }

    fun eliminarPlanetaPorNombre(nombre: String) {
        val idEliminar = planetas.entries.find { it.value.nombre == nombre }?.key
        idEliminar?.let { planetas.remove(it) }
        guardarDatos()
    }

    fun actualizarPlaneta(nombre: String, planeta: Planeta) {
        val planetaActualizar  = planetas.entries.find { it.value.nombre == nombre }

        if (planetaActualizar != null) {
            planetas[planetaActualizar.key] = planeta
            guardarDatos()
        }
    }

    fun obtenerPlanetas(): List<Planeta> {
        return planetas.values.toList()
    }
}
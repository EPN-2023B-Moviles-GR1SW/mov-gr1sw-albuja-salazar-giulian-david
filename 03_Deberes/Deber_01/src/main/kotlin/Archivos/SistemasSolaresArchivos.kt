package Archivos

import Entities.Planeta
import Entities.SistemaSolar
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

class SistemasSolaresArchivos : Archivo() {
    private val sistemasSolares: MutableMap<Int, SistemaSolar> = mutableMapOf()
    private val planetasArchivo : PlanetasArchivos = PlanetasArchivos()
    override var rutaArchivo : String = "src/main/kotlin/Datos/sistemasSolares.txt"

    init{
        cargarDatos()
    }

    override fun cargarDatos() {
        val file = File("src/main/kotlin/Datos/sistemasSolares.txt")
        var counter: Int = 0
        if (file.exists()) {
            file.forEachLine {
                val separacion = it.split(",")
                if (separacion.size >= 5) {
                    val nombre = separacion[0]
                    val descubierto = separacion[1].toBoolean()
                    val radio = separacion[2].toFloat()
                    val fechaRegistro = Date(separacion[3].toLong())
                    val edad = separacion[4].toInt()
                    val sistemaSolar = SistemaSolar(nombre, descubierto, radio, fechaRegistro, edad)

                    sistemasSolares[counter++] = sistemaSolar

                    if (separacion.size > 5) {
                        for (i in 5 until separacion.size) {

                            val nombrePlaneta = separacion[i]

                            val planeta = planetasArchivo.obtenerPlanetaPorNombre(nombrePlaneta)
                            if (planeta != null) {
                                sistemaSolar.planetas += planeta
                            }
                        }
                    }
                }
            }
        }
    }

    override fun guardarDatos() {
        val file = File(rutaArchivo)
        FileWriter(file, false).use { fileWriter ->
            BufferedWriter(fileWriter).use { out ->
                sistemasSolares.values.forEach { sistemaSolar ->
                    val listaNombresPlanetas = sistemaSolar.planetas.joinToString(",") { it.nombre }
                    out.write("${sistemaSolar.nombre},${sistemaSolar.descubierto},${sistemaSolar.radio},${sistemaSolar.fechaRegistro.time},${sistemaSolar.edad},$listaNombresPlanetas\n")
                }
            }
        }
    }



    fun agregarSistemaSolar(sistemaSolar: SistemaSolar) {
        sistemasSolares[sistemasSolares.size] = sistemaSolar
        guardarDatos()
    }

    fun obtenerSistemaSolarPorNombre(nombre: String): SistemaSolar? {
        return sistemasSolares.values.find { it.nombre == nombre }
    }

    fun eliminarSistemaSolarPorNombre(nombre: String) {
        val idAEliminar = sistemasSolares.entries.find { it.value.nombre == nombre }?.key
        idAEliminar?.let { sistemasSolares.remove(it) }
        guardarDatos()
    }

    fun obtenerSistemasSolares(): List<SistemaSolar> {
        return sistemasSolares.values.toList()
    }

    fun actualizarSistemaSolar(nombre: String, sistemaSolar: SistemaSolar) {
        val sistemaActualizar  = sistemasSolares.entries.find { it.value.nombre == nombre }

        if (sistemaActualizar != null) {
            sistemasSolares[sistemaActualizar.key] = sistemaSolar
            guardarDatos()
        }
    }

    fun agregarPlaneta(sistemaSolar : String, planeta: Planeta){
        val sistemaActualizar  = sistemasSolares.entries.find { it.value.nombre == sistemaSolar }

        if(sistemaActualizar != null){
            sistemasSolares[sistemaActualizar.key]?.planetas?.add(planeta)
            guardarDatos()
        }
    }

    fun eliminarPlaneta(sistemaSolar : String, planeta: Planeta){
        val sistemaActualizar  = sistemasSolares.entries.find { it.value.nombre == sistemaSolar }

        if(sistemaActualizar != null){
            sistemasSolares[sistemaActualizar.key]?.planetas?.removeIf { it.nombre == planeta.nombre }
            guardarDatos()
        }
    }
}
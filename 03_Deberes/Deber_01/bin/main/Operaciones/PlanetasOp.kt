package Operaciones

import Entities.Planeta
import Archivos.PlanetasArchivos

class PlanetasOp {
    val archivo = PlanetasArchivos()

    fun agregarPlaneta(planeta: Planeta) : Boolean{

        if(archivo.obtenerPlanetaPorNombre(planeta.nombre) != null) {
            println("El planeta ya se encuentra registrado")
            return false
        }

        archivo.agregarPlaneta(planeta)
        println("Registro exitoso")
        return true
    }

    fun obtenerPlanetas(): List<Planeta> {
        return archivo.obtenerPlanetas()
    }

    fun eliminarPlanetaPorNombre(nombre: String) : Boolean {
        if(archivo.obtenerPlanetaPorNombre(nombre) == null) {
            println("Planeta no encontrado")
            return false
        }
        archivo.eliminarPlanetaPorNombre(nombre)
        println("Eliminacion exitosa")
        return true
    }

    fun actualizarPlaneta(nombre: String, dto : Planeta) : Boolean {
        if(archivo.obtenerPlanetaPorNombre(nombre) == null){
            println("Planeta no encontrado")
            return false
        }

        archivo.actualizarPlaneta(nombre, dto)
        println("Actualizacion exitosa")
        return true
    }
}
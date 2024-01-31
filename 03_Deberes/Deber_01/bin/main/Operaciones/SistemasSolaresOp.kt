package Operaciones

import  Entities.SistemaSolar
import Archivos.PlanetasArchivos
import Archivos.SistemasSolaresArchivos

class SistemasSolaresOp {

    val archivo = SistemasSolaresArchivos()
    val planetas = PlanetasArchivos()

    fun agregarSistemaSolar(sistemaSolar: SistemaSolar) : Boolean{

        if(archivo.obtenerSistemaSolarPorNombre(sistemaSolar.nombre) != null) {
            println("Sistema solar ya registrado")
            return false
        }

        archivo.agregarSistemaSolar(sistemaSolar)
        println("Registro agregado exitosamente")
        return true
    }

    fun obtenerSistemasSolares(): List<SistemaSolar> {
        return archivo.obtenerSistemasSolares()
    }

    fun eliminarSistemasSolaresPorNombre(nombre: String) : Boolean {
        if(archivo.obtenerSistemaSolarPorNombre(nombre) == null) {
            println("Sistema solar no encontrado")
            return false
        }
        archivo.eliminarSistemaSolarPorNombre(nombre)
        println("Eliminacion exitosa")
        return true
    }

    fun actualizarSistemaSolar(nombre: String, dto : SistemaSolar) : Boolean {
        if(archivo.obtenerSistemaSolarPorNombre(nombre) == null){
            println("Sistema solar no encontrado")
            return false
        }

        archivo.actualizarSistemaSolar(nombre, dto)
        println("Actualizacion exitosa")
        return true
    }

    fun agregarPlaneta(sistemaSolar: String, nombrePlaneta : String) : Boolean{
        if(archivo.obtenerSistemaSolarPorNombre(sistemaSolar) == null){
            println("Sistema solar no encontrado")
            return false
        }
        if(planetas.obtenerPlanetaPorNombre(nombrePlaneta) == null){
            println("Planeta no encontrado")
            return false
        }

        val planeta = planetas.obtenerPlanetaPorNombre(nombrePlaneta)
        if (planeta != null) {
            archivo.agregarPlaneta(sistemaSolar, planeta)
            println("Planeta agregado con exito")
            return true
        }
        return false
    }

    fun eliminarPlaneta(sistemaSolar: String, nombrePlaneta : String) : Boolean{
        if(archivo.obtenerSistemaSolarPorNombre(sistemaSolar) == null){
            println("Sistema solar no encontrado")
            return false
        }
        if(planetas.obtenerPlanetaPorNombre(nombrePlaneta) == null){
            println("Planeta no encontrado")
            return false
        }

        val planeta = planetas.obtenerPlanetaPorNombre(nombrePlaneta)
        if (planeta != null) {
            archivo.eliminarPlaneta(sistemaSolar, planeta)
            println("Eliminacion con exito")
            return true
        }
        return false
    }
}
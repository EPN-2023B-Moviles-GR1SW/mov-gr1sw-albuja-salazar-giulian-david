package com.example.examen.DAO

import com.example.examen.BD.BaseDatos
import com.example.examen.ENTIDAD.Planeta
import com.example.examen.ENTIDAD.SistemaSolar

class SistemaSolarDAO {

    fun getById(id: Int): SistemaSolar? {
        var sistemaEncontrado: SistemaSolar? = null
        getAll().forEach { sistemaSolar: SistemaSolar ->
            if (sistemaSolar.id == id) sistemaEncontrado = sistemaSolar
        }
        return sistemaEncontrado
    }

    fun getAll(): ArrayList<SistemaSolar> {
        return BaseDatos.listaDeSistemas
    }

    fun create(sistemaSolar: SistemaSolar) {
        val listaSistemas = getAll()
        if (listaSistemas.isEmpty()) {
            sistemaSolar.id = 0
        } else {
            sistemaSolar.id = listaSistemas.last().id?.plus(1)!!
        }
        listaSistemas.add(sistemaSolar)
    }

    fun update(sistemaActualizado: SistemaSolar) {
        val listaSistemas = getAll()
        listaSistemas.forEachIndexed { index, cine ->
            if (cine.id == sistemaActualizado.id) {
                listaSistemas[index] = sistemaActualizado
                return
            }
        }
    }

    fun deleteById(id: Int): Boolean {
        val planetaDAO = PlanetaDAO()
        getAll().forEach { cine: SistemaSolar ->
            if (cine.id == id) {
                cine.listaPlanetas.forEach { pelicula: Planeta ->
                    planetaDAO.deleteById(pelicula.id!!)
                }
            }
        }
        return getAll().removeIf { cine -> (cine.id == id) }
    }
}
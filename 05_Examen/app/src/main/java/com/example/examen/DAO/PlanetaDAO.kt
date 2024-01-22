package com.example.examen.DAO

import com.example.examen.BD.BaseDatos
import com.example.examen.ENTIDAD.Planeta

class PlanetaDAO {

    fun getById(id: Int): Planeta? {
        var planetaEncontrado: Planeta? = null
        getAll().forEach { planeta: Planeta ->
            if (planeta.id == id) planetaEncontrado = planeta
        }
        return planetaEncontrado
    }

    fun getAll(): ArrayList<Planeta> {
        return BaseDatos.listaDePlanetas
    }

    fun create(planeta: Planeta) {
        val listaPlanetas = getAll()
        if (listaPlanetas.isEmpty()) {
            planeta.id = 0
        } else {
            planeta.id = listaPlanetas.last().id?.plus(1)!!
        }
        listaPlanetas.add(planeta)
        planeta.sistemaSolar.listaPlanetas.add(planeta)
    }

    fun update(planetaActualizado: Planeta) {
        val listaPlanetas = getAll()
        listaPlanetas.forEachIndexed { index, planeta ->
            if (planeta.id == planetaActualizado.id) {
                listaPlanetas[index] = planetaActualizado
                return
            }
        }
    }

    fun deleteById(id: Int) {
        val planeta = getById(id)
        if (planeta != null) {
            planeta.sistemaSolar.listaPlanetas.remove(planeta)
            getAll().remove(planeta)
        }
    }

}
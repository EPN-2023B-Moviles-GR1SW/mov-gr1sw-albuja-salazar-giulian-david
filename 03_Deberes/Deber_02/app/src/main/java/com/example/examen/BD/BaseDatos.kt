package com.example.examen.BD

import com.example.examen.ENTIDAD.Planeta
import com.example.examen.ENTIDAD.SistemaSolar

class BaseDatos {

    companion object {
        var listaDeSistemas: ArrayList<SistemaSolar> = arrayListOf()
        var listaDePlanetas: ArrayList<Planeta> = arrayListOf()

        init {
            listaDePlanetas.add(
                Planeta(
                    0,
                    "Tierra",
                    "Vía Láctea",
                    80000,
                    300.25,
                    "Si"
                )
            )
            listaDePlanetas.add(
                Planeta(
                    1,
                    "Jupiter",
                    "Vía Láctea",
                    89550,
                    97098.0,
                    "Si"
                )
            )
            listaDePlanetas.add(
                Planeta(
                    2,
                    "Volmir",
                    "Azgard",
                    18946,
                    100000.0,
                    "No"
                )
            )

            listaDeSistemas.add(
                SistemaSolar(
                    0,
                    "Via Lactea",
                    "Sistemática",
                    true,
                    498415
                )
            )
            listaDeSistemas.add(
                SistemaSolar(
                    1,
                    "Azgard",
                    "Nula",
                    false,
                    489423
                )
            )
            listaDeSistemas.get(0).listaPlanetas.add(listaDePlanetas.get(0))
            listaDeSistemas.get(0).listaPlanetas.add(listaDePlanetas.get(1))
            listaDeSistemas.get(1).listaPlanetas.add(listaDePlanetas.get(2))
            listaDePlanetas.get(0).sistemaSolar = listaDeSistemas.get(0)
            listaDePlanetas.get(1).sistemaSolar = listaDeSistemas.get(0)
            listaDePlanetas.get(2).sistemaSolar = listaDeSistemas.get(1)
        }
    }
}
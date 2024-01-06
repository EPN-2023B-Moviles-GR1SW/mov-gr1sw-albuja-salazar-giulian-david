package Entities

import java.util.*
import kotlin.collections.ArrayList

class SistemaSolar(
    val nombre : String,
    var descubierto : Boolean,
    var radio : Float,
    var fechaRegistro : Date,
    var edad : Int,
)
{
    var planetas : ArrayList<Planeta> = ArrayList<Planeta>()

    override fun toString(): String {
        var resultadoPlanetas : String = ""
        if(planetas.size != 0)
            resultadoPlanetas = planetas.joinToString("; ") { it.nombre }

        return "Nombre: ${nombre} \nEsta descubierto?: ${descubierto} \nRadio: ${radio}" +
                "\nFecha de registro: ${fechaRegistro} \nEdad del sistema: ${edad}" +
                "\nPlanetas: [${resultadoPlanetas}]\n"
    }
}




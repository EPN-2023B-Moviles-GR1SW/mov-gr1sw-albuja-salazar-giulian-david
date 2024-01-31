package Entities
import java.util.*

class Planeta(
    val nombre : String,
    var descubierto : Boolean,
    var radio : Float,
    var fechaRegistro : Date,
    var edad : Int
)
{
    override fun toString(): String {
        return "Nombre: ${nombre} \nDescubierto: ${descubierto} \nRadio: ${radio}" +
                "\nRegistro: ${fechaRegistro} \nEdad: ${edad}\n"
    }
}
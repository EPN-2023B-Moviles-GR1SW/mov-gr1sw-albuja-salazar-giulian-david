import Entities.Planeta
import Entities.SistemaSolar
import Operaciones.PlanetasOp
import Operaciones.SistemasSolaresOp
import java.text.SimpleDateFormat
import java.util.*

val planetasOp = PlanetasOp()
val sistemasSolaresOp = SistemasSolaresOp()
val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

fun main() {
    var opcion: Int = -1
    while (opcion != 0) {
        println("------Ingresa a un registro------")
        println("1 --> Planetas <--")
        println("2 --> Sistemas Solares <--")
        println("0 --> Salir <--")
        println("----------------------------------")
        opcion = readLine()?.toIntOrNull() ?: 0
        when (opcion) {
            0 -> {
                println("------BYE-------")
            }
            1 -> {
                menuPlanetas()
            }
            2 -> {
                menuSistemasSolares()
            }
            else -> {
                println("Ingrese una opcion correcta")
            }
        }
    }
}

fun menuPlanetas() {
    var opcionMenu = -1

    println("---------------------------------")
    println("1 --> Ver Planetas <--")
    println("2 --> Agregar Planeta <--")
    println("3 --> Actualizar Registro <--")
    println("4 --> Eliminar Planeta por nombre <--")
    opcionMenu = readLine()?.toIntOrNull() ?: 0
    println(opcionMenu)

    when (opcionMenu) {
        1 -> {
            planetasOp.obtenerPlanetas().forEach {
                println(it.toString())
            }
        }
        2 -> {
            print("Nombre: ")
            val nombre: String = readLine() ?: ""

            print("Esta descubierto? ")
            val temp = readLine()
            val descubierto: Boolean = temp.equals("s", ignoreCase = true)

            print("Radio: ")
            val radio = readLine()?.toFloatOrNull() ?: 0.0f

            print("Fecha de registro: ")
            val fechaRegistro = readLine() ?: ""

            print("Edad: ")
            val edad = readLine()?.toIntOrNull() ?: 0

            planetasOp.agregarPlaneta(
                Planeta(
                    nombre,
                    descubierto,
                    radio,
                    formatoFecha.parse(fechaRegistro),
                    edad
                )
            )
        }
        3 -> {
            print("Elija un registro: ")
            val nombre: String = readLine() ?: ""
            print("esta descubierto? ")
            val temp = readLine()
            val descubierto: Boolean = temp.equals("s", ignoreCase = true)

            print("Radio: ")
            val radio = readLine()?.toFloatOrNull() ?: 0.0f

            print("Fecha registro: ")
            val fechaRegistro = readLine() ?: ""

            print("Edad: ")
            val Edad = readLine()?.toIntOrNull() ?: 0

            planetasOp.actualizarPlaneta(
                nombre,
                Planeta(
                    nombre,
                    descubierto,
                    radio,
                    formatoFecha.parse(fechaRegistro),
                    Edad
                )
            )
        }
        4 -> {
            print("Planeta a eliminar (nombre): ")
            planetasOp.eliminarPlanetaPorNombre(readLine() ?: "")
        }
        else -> {
            println("Opcion invalida.")
        }
    }
}

fun menuSistemasSolares() {
    var opcion = -1

    println("---------------------------------")
    println("1 --> Ver Sistemas solares <--")
    println("2 --> Agregar Sistema solar <--")
    println("3 --> Actualizar Sistema solar <--")
    println("4 --> Eliminar Sistema solar por nombre <--")
    println("5 --> Agregar un planeta <--")
    println("6 --> Eliminar un planeta <--")
    println("---------------------------------")
    opcion = readLine()?.toIntOrNull() ?: 0

    when (opcion) {
        1 -> {
            sistemasSolaresOp.obtenerSistemasSolares().forEach {
                println(it.toString())
            }
        }
        2 -> {
            print("Nombre: ")
            val nombre: String = readLine() ?: ""

            print("Descubierto? ")
            val temp = readLine()
            val descubierto: Boolean = temp.equals("s", ignoreCase = true)

            print("Radio: ")
            val radio = readLine()?.toFloatOrNull() ?: 0.0f

            print("Edad: ")
            val edad = readLine()?.toIntOrNull() ?: 0
            sistemasSolaresOp.agregarSistemaSolar(
                SistemaSolar(
                    nombre,
                    descubierto,
                    radio,
                    Date(),
                    edad
                )
            )
        }
        3 -> {
            print("Registro a actualizar: ")
            val nombre: String = readLine() ?: ""
            print("Descubierto?: ")
            val temp = readLine()
            val esPlatoTemporada: Boolean = temp.equals("s", ignoreCase = true)

            print("Radio: ")
            val radio = readLine()?.toFloatOrNull() ?: 0.0f

            print("Fecha de registro: ")
            val fechaRegistro = readLine() ?: ""

            print("Edad: ")
            val edad = readLine()?.toIntOrNull() ?: 0

            sistemasSolaresOp.actualizarSistemaSolar(
                nombre,
                SistemaSolar(
                    nombre,
                    esPlatoTemporada,
                    radio,
                    formatoFecha.parse(fechaRegistro),
                    edad
                )
            )
        }
        4 -> {
            print("Sistema solar a eliminar: ")
            sistemasSolaresOp.eliminarSistemasSolaresPorNombre(readLine() ?: "")
        }
        5 -> {
            println("Ingresar nombre de sistema solar:")
            var sistemaSolar: String = readLine() ?: ""
            println("Ingresar nombre del planeta:")
            var planeta: String = readLine() ?: ""
            sistemasSolaresOp.agregarPlaneta(sistemaSolar, planeta)
        }
        6 -> {
            println("Ingresar nombre de sistema solar:")
            var receta: String = readLine() ?: ""
            println("Ingresar nombre del planeta:")
            var ingrediente: String = readLine() ?: ""
            sistemasSolaresOp.eliminarPlaneta(receta, ingrediente)
        }
        else -> {
        }
    }
}

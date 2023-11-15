import java.util.*

fun main(args: Array<String>) {
    //No se reasignan
    val inmutable: String = "David";

    //Si se pueden reasignar
    var mutable: String = "Albuja";
    mutable = "Vicente";

    //Duck Typing
    var ejemploVariable = "David Albuja";
    val edadEjemplo: Int = 12;
    ejemploVariable.trim()


    //Variables primitivas
    val nombreEstudiante: String = "David Albuja";
    val sueldo: Double = 1.2;
    val estadoCivil: Char = 'S';
    val mayorEdad: Boolean = true;
    //Clases Java
    val fechaNacimiento: Date = Date();


    //Switch
    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado");
        }
        "S" -> {
            println("Soltero");
        }
        else -> {
            println("No sabemos");
        }
    }

    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No";


    //void -> Unit

    fun imprimirNombre(nombre: String): Unit{
        //template strings
        //"Bienvenido: " + nombre + ""
        println("Nombre: ${nombre}");
    }

    fun calcularSueldo(
        sueldo: Double,
        tasa: Double = 12.00,
        bonoEspecial: Double? = null,
    ): Double{
        if(bonoEspecial == null){
            return sueldo * (100/tasa);
        }else{
            bonoEspecial.dec();
            return sueldo * (100/tasa) + bonoEspecial;
        }
    }

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00)
    calcularSueldo(10.00, 12.00, 20.00)

    //Parametros nombrados
    calcularSueldo(sueldo=10.00, bonoEspecial = 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

}


//Clases
abstract class Numeros(
    protected  val numeroUno: Int,
    protected  val numeroDos: Int,
){
    init {
        this.numeroUno; this.numeroDos;
        numeroUno; numeroDos;
        println("Inicializando");
    }
}


//Herencia
class Suma(
    uno: Int,
    dos: Int
) : Numeros(uno, dos) {
    init {
        this.numeroUno; numeroDos;
        this.numeroDos; numeroDos;
    }

    constructor(
        uno: Int?,
        dos: Int
    ) : this(if (uno == null) 0 else uno,
        dos
    ){
        numeroUno;
    }
}


//Maneras de utilizar constructores






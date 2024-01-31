package Archivos

import java.io.File

abstract class Archivo() {
    abstract protected var rutaArchivo : String

    abstract protected fun cargarDatos()

    abstract protected fun guardarDatos()
}
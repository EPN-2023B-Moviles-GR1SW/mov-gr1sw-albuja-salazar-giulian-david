package com.example.examen.BD

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen.ENTIDAD.SistemaSolar

class SqliteHelper {
    class SqliteHelper (
        contexto: Context?,
    ) : SQLiteOpenHelper(
        contexto,
        "Deber02",
        null,
        5
    ){

        override fun onCreate(db: SQLiteDatabase?) {
            val scriptSQLCrearTablaSistema =
                """
                CREATE TABLE SISTEMA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT, 
                    nombre VARCHAR(50),
                    rotacion VARCHAR(50),
                    descubierto BOOLEAN,
                    edad INTEGER
                )
             """.trimIndent()
            db?.execSQL(scriptSQLCrearTablaSistema)

            val scriptSQLCrearTablaPlaneta =
                """
                CREATE TABLE PLANETA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT, 
                    nombrePlaneta VARCHAR(50),
                    radio DOUBLE,
                    edad INTEGER,
                    descubierto VARCHAR(2),
                    idSistema INTEGER,
                    FOREIGN KEY (idSistema) REFERENCES SISTEMA(id)
                )
            """.trimIndent()
            db?.execSQL(scriptSQLCrearTablaPlaneta)
        }

        override fun onUpgrade(db: SQLiteDatabase?,  oldVersion:Int, newVersion:Int) {
        }

        fun crearSistema(
            nombre: String,
            rotacion: String,
            descubierto: String,
            edad: Int
        ): Boolean {
            val baseDatosEscritura = writableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("nombre", nombre)
            valoresAGuardar.put("rotacion", rotacion)
            valoresAGuardar.put("descubierto", descubierto)
            valoresAGuardar.put("edad", edad)

            val resultadoGuardar = baseDatosEscritura
                .insert(
                    "SISTEMA",
                    null,
                    valoresAGuardar
                )
            baseDatosEscritura.close()
            return if (resultadoGuardar.toInt() === -1) false else true
        }

        fun eliminarSistemaFormulario(id: Int): Boolean {
            val conexionEscritura = writableDatabase
            val parametrosConsultaDelete = arrayOf(id.toString())
            val resultadoEliminacion = conexionEscritura
                .delete(
                    "SISTEMA", // Nombre tabla
                    "id=?", // Consulta Where
                    parametrosConsultaDelete
                )
            conexionEscritura.close()
            return if (resultadoEliminacion.toInt() == -1) false else true
        }

        fun actualizarSistemaFormulario(
            nombre: String,
            rotacion: String,
            descubierto: String,
            edad: Int,
            id: Int,
        ): Boolean {
            val conexionEscritura = writableDatabase
            val valoresAActualizar = ContentValues()
            valoresAActualizar.put("nombre", nombre)
            valoresAActualizar.put("rotacion", rotacion)
            valoresAActualizar.put("descubierto", descubierto)
            valoresAActualizar.put("edad", edad)

            val parametrosConsultaActualizar = arrayOf(id.toString())
            val resultadoActualizacion = conexionEscritura
                .update(
                    "SISTEMA",
                    valoresAActualizar,
                    "id=?",
                    parametrosConsultaActualizar
                )
            conexionEscritura.close()
            return if(resultadoActualizacion == -1) false else true
        }

        fun consultarSistemaPorID(id: Int): SistemaSolar {
            val baseDatosLectura = readableDatabase
            val scriptConsultaLectura = """
            SELECT * FROM SISTEMA WHERE ID = ?
        """.trimIndent()
            val parametrosConsultaLectura = arrayOf(id.toString())
            val resultadoConsultaLectura = baseDatosLectura.rawQuery(
                scriptConsultaLectura,
                parametrosConsultaLectura,
            )
            val existeTienda = resultadoConsultaLectura.moveToFirst()
            val sistemaEncontrado = SistemaSolar(0, "", "", true, 0)
            val arreglo = arrayListOf<SistemaSolar>()
            if (existeTienda) {
                do {
                    val id = resultadoConsultaLectura.getInt(0)
                    val nombre = resultadoConsultaLectura.getString(1)
                    val rotacion = resultadoConsultaLectura.getString(2)
                    val descubierto = resultadoConsultaLectura.getString(3)
                    val edad = resultadoConsultaLectura.getInt(4)
                    if (id != null) {
                        // llenar el arreglo con un nuevo
                        sistemaEncontrado.id = id
                        sistemaEncontrado.nombre = nombre
                        sistemaEncontrado.rotacion = rotacion
                        sistemaEncontrado.descubierto = descubierto
                        sistemaEncontrado.edad = edad
                    }
                } while (resultadoConsultaLectura.moveToNext())
            }
            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return sistemaEncontrado
        }


        fun consultarSistemas(): ArrayList<SistemaSolar> {
            val scriptConsultarSistemas = "SELECT * FROM SISTEMA"
            val baseDatosLectura = readableDatabase
            val resultadoConsultaLectura = baseDatosLectura.rawQuery(
                scriptConsultarSistemas,
                null
            )
            val existeSistema = resultadoConsultaLectura.moveToFirst()
            val arregloSistema = arrayListOf<SistemaSolar>()
            if (existeSistema) {
                do {
                    val id = resultadoConsultaLectura.getInt(0) // Indice 0
                    val nombre = resultadoConsultaLectura.getString(1)
                    val rotacion = resultadoConsultaLectura.getString(2)
                    val descubierto = resultadoConsultaLectura.getString(3)
                    val edad = resultadoConsultaLectura.getInt(4)
                    arregloSistema.add(
                        SistemaSolar(
                            id,
                            nombre,
                            rotacion,
                            descubierto,
                            edad
                        )
                    )
                } while (resultadoConsultaLectura.moveToNext())
            }
            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return arregloSistema
        }

    }
}
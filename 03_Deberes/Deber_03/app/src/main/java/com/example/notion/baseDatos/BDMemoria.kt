package com.example.notion.baseDatos

import android.content.Context
import com.example.notion.modelos.Elemento
import com.example.notion.R
import com.example.notion.modelos.Notificacion


class BDMemoria {


    companion object{
        var elementosInicializados = false
        var notificacionesInicializadas = false
        val arregloElementos = arrayListOf<Elemento>()
        val arregloNotificaciones = arrayListOf<Notificacion>()
        fun inicializarElementos(context: Context) {
            if (!elementosInicializados) {
                arregloElementos.add(
                    Elemento(
                        "Verificación y Validación",
                        Elemento.cargarImagen(context, R.drawable.bookss)
                    )
                )
                arregloElementos.add(
                    Elemento(
                        "Aplicaciones Móviles",
                        Elemento.cargarImagen(context, R.drawable.android)
                    )
                )
                arregloElementos.add(
                    Elemento(
                        "Usabilidad y Accesibilidad",
                        Elemento.cargarImagen(context, R.drawable.accessibility)
                    )
                )
                arregloElementos.add(
                    Elemento(
                        "Química",
                        Elemento.cargarImagen(context, R.drawable.atom)
                    )
                )
                arregloElementos.add(
                    Elemento(
                        "Historia",
                        Elemento.cargarImagen(context, R.drawable.clock)
                    )
                )
                arregloElementos.add(
                    Elemento(
                        "Inglés",
                        Elemento.cargarImagen(context, R.drawable.eng)
                    )
                )

                elementosInicializados = true
            }
        }
        fun inicializarNotificaciones(context: Context){
            if (!notificacionesInicializadas) {
                arregloNotificaciones.add(
                    Notificacion(
                        "Mobile push notifications",
                        "Receive push notifications on mentions and comments via your mobile app"
                    )
                )
                arregloNotificaciones.add(
                    Notificacion(
                        "Activity in your workspace",
                        "Receive emails when you get comments, mentions, page invites, reminder, access requests, and property changes"
                    )
                )
                arregloNotificaciones.add(
                    Notificacion(
                        "Always send email notifications",
                        "Receive emails about activity in your workspace, even when you're active on the app"
                    )
                )
                arregloNotificaciones.add(
                    Notificacion(
                        "Email digests",
                        "Receive email digests every 8 hours for changes to pages you're subscribed to"
                    )
                )
                arregloNotificaciones.add(
                    Notificacion(
                        "Slack Notifications",
                        "Receive notications in your Slack workspace when you're mentioned in a page, database prop"
                    )
                )
                notificacionesInicializadas = true
            }
        }
    }
}
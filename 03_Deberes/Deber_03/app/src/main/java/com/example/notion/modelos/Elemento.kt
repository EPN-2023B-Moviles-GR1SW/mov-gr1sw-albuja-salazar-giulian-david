package com.example.notion.modelos

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Elemento (
    var nombre: String,
    var icono: Bitmap
){

    companion object{
        fun cargarImagen(context: Context, imagenResourceId: Int): Bitmap {
            return BitmapFactory.decodeResource(context.resources, imagenResourceId)

        }
    }
}
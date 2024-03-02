package com.example.notion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notion.modelos.Elemento
import com.example.notion.modelos.Notificacion

class AdaptadorNotificaciones (
    private val contexto: MainActivity,
    private val lista: ArrayList<Notificacion>,
    private val recyclerView: RecyclerView

) : RecyclerView.Adapter<
        AdaptadorNotificaciones.MyViewHolder
        >()  {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        val tituloNotificacion: TextView
        val descripcionNotificacion: TextView
        val fondo: ConstraintLayout
        init {
            tituloNotificacion = view.findViewById(R.id.tv_notification_titulo)
            descripcionNotificacion = view.findViewById(R.id.tv_notification_description)
            fondo = view.findViewById(R.id.cl_notificaciones)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptadorNotificaciones.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.notifications,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: AdaptadorNotificaciones.MyViewHolder, position: Int) {
        val notificacionActual = this.lista[position]

        val layoutParams = holder.fondo.layoutParams as ViewGroup.MarginLayoutParams

        layoutParams.topMargin = 1
        holder.fondo.layoutParams = layoutParams
        if (position == lista.size - 1){
            layoutParams.bottomMargin = 1
        }

        holder.tituloNotificacion.text = notificacionActual.titulo
        holder.descripcionNotificacion.text = notificacionActual.descripcion
    }
    override fun getItemCount(): Int {
        return this.lista.size
    }
}
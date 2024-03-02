package com.example.notion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notion.modelos.Elemento

class AdaptadorElementos(
    private val contexto: ListaElementos,
    private val lista: ArrayList<Elemento>,
    private val recyclerView: RecyclerView

) : RecyclerView.Adapter<
        AdaptadorElementos.MyViewHolder
        >() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        val nombreElemento: TextView
        val iconoElemento: ImageView
        val fondo: LinearLayout
        init {
            nombreElemento = view.findViewById(R.id.tv_nombreElemento)
            iconoElemento = view.findViewById(R.id.iv_icono)
            fondo = view.findViewById(R.id.li_tarjeta)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptadorElementos.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.elemento,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdaptadorElementos.MyViewHolder, position: Int) {
        val elementoActual = this.lista[position]

        val layoutParams = holder.fondo.layoutParams as ViewGroup.MarginLayoutParams

        layoutParams.topMargin = 1
        holder.fondo.layoutParams = layoutParams
        if (position == lista.size - 1){
            layoutParams.bottomMargin = 1
        }

        holder.nombreElemento.text = elementoActual.nombre
        holder.iconoElemento.setImageBitmap(elementoActual.icono)


    }

    override fun getItemCount(): Int {
        return this.lista.size
    }
}
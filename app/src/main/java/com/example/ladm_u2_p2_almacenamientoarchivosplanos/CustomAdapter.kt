package com.example.ladm_u2_p2_almacenamientoarchivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dato:List<Datos>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){


    /*val nombres = arrayOf("EDGAR","Fabio","Luis","Joel")
    val edades = arrayOf("24","19","20","45")
    val fechas = arrayOf("20/12/2022","10/09/2022","12/11/2022","03/03/2022")
    val images = intArrayOf(R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground)*/


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)

    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        /*viewHolder.itemNombre.text = nombres[i]
        viewHolder.itemEdad.text = edades[i]
        viewHolder.itemFecha.text = fechas[i]
        viewHolder.itemImage.setImageResource(images[i])*/
        val item = dato[i]
        viewHolder.render(item)
    }

    override fun getItemCount(): Int {
        return dato.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage = itemView.findViewById<ImageView>(R.id.item_image)
        var itemNombre = itemView.findViewById<TextView>(R.id.item_nombre)
        var itemFecha = itemView.findViewById<TextView>(R.id.item_fecha)
        var itemEdad = itemView.findViewById<TextView>(R.id.item_edad)


        fun render(cita: Datos) {
            itemNombre.text = cita.nombre
            itemEdad.text = cita.edad.toString()
            itemFecha.text = cita.fecha
        }
    }
}
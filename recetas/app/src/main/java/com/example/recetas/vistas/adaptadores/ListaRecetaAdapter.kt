package com.example.recetas.vistas.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.recetas.R
import com.example.recetas.modelos.Receta
import com.example.recetas.vistas.HomeScreenActivity
import java.util.Locale

class ListaRecetaAdapter(private val context: HomeScreenActivity, val listaReceta: List<Receta>) :
    BaseAdapter(), Filterable {

    private var recetasFiltradas: List<Receta>? = null

    override fun getCount() = recetasFiltradas?.size ?: listaReceta.size

    override fun getItem(position: Int) = listaReceta[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.lista_receta_adapter, parent, false)

        view.setOnClickListener {
            context.recetaSeleccionada(
                recetasFiltradas?.get(position) ?: listaReceta[position]
            )
        }
        view.findViewById<TextView>(R.id.nombreReceta).text =
            recetasFiltradas?.get(position)?.nombre ?: listaReceta[position].nombre
        cargarImagen(
            recetasFiltradas?.get(position)?.imagen ?: listaReceta[position].imagen,
            view.findViewById(R.id.imagenReceta)
        )
        cargarImagen(
            recetasFiltradas?.get(position)?.origen?.banderaPais
                ?: listaReceta[position].origen.banderaPais,
            view.findViewById(R.id.banderaPais)
        )
        return view
    }

    private fun cargarImagen(url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val results = FilterResults()
                if (constraint.isEmpty()) {
                    results.count = listaReceta.size
                    results.values = listaReceta
                } else {
                    val resultsData: MutableList<Receta> = ArrayList()
                    val searchStr = constraint.toString().uppercase()
                    for (receta in listaReceta) {
                        if (receta.nombre.uppercase(Locale.getDefault())
                                .startsWith(searchStr) || receta.origen.nombrePais.uppercase(
                                Locale.ROOT
                            )
                                .startsWith(searchStr) || receta.ingredientes.uppercase(Locale.ROOT)
                                .contains(searchStr)
                        ) {
                            resultsData.add(receta)
                        }
                    }
                    results.count = resultsData.size
                    results.values = resultsData
                }
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                recetasFiltradas = results.values as List<Receta>
                notifyDataSetChanged()
            }
        }
    }
}
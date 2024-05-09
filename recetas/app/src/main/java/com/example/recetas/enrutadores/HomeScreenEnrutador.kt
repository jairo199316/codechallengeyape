package com.example.recetas.enrutadores

import android.content.Context
import android.content.Intent
import com.example.recetas.modelos.Receta
import com.example.recetas.vistas.DetailScreenActivity

class HomeScreenEnrutador(private val context: Context) {

    fun irADetalleReceta(receta: Receta) {
        val intent = Intent(context, DetailScreenActivity::class.java)
        intent.putExtra(DetailScreenActivity.EXTRA_RECETA, receta)
        context.startActivity(intent)
    }
}
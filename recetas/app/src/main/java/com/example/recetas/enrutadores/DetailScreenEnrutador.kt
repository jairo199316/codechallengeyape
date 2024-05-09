package com.example.recetas.enrutadores

import android.content.Context
import android.content.Intent
import com.example.recetas.modelos.Origen
import com.example.recetas.vistas.MapScreenActivity

class DetailScreenEnrutador(private val context: Context) {

    fun irAMapaOrigenReceta(origen: Origen) {
        val intent = Intent(context, MapScreenActivity::class.java)
        intent.putExtra(MapScreenActivity.EXTRA_ORIGEN, origen)
        context.startActivity(intent)
    }
}
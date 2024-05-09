package com.example.recetas.vistas

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recetas.databinding.ActivityDetailScreenBinding
import com.example.recetas.enrutadores.DetailScreenEnrutador
import com.example.recetas.modelos.Origen
import com.example.recetas.modelos.Receta

class DetailScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScreenBinding
    private var origen: Origen? = null
    private val enrutador = DetailScreenEnrutador(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val receta = intent.getSerializableExtra(EXTRA_RECETA) as Receta
        origen = receta.origen
        cargarUI(receta)
        cargarEventos()
    }

    private fun cargarUI(receta: Receta) {
        cargarImagen(receta.imagen, binding.imagenReceta)
        binding.nombreReceta.text = receta.nombre
        binding.ingredientes.text = Html.fromHtml(receta.ingredientes)
        binding.preparacion.text = Html.fromHtml(receta.preparacion)
    }

    private fun cargarEventos() {
        binding.ubicacion.setOnClickListener {
            origen?.let { origen -> enrutador.irAMapaOrigenReceta(origen) }
        }
        binding.irAtras.setOnClickListener { onBackPressed() }
    }

    private fun cargarImagen(url: String, imageView: ImageView) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    companion object {
        const val EXTRA_RECETA = "extra_receta"
    }
}
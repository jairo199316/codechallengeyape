package com.example.recetas.vistas

import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recetas.databinding.ActivityHomeScreenBinding
import com.example.recetas.enrutadores.HomeScreenEnrutador
import com.example.recetas.modelos.Receta
import com.example.recetas.viewmodel.RecetaViewModel
import com.example.recetas.vistas.adaptadores.ListaRecetaAdapter
import com.example.recetas.vistas.adaptadores.OnRecetaSeleccionada

class HomeScreenActivity : AppCompatActivity(), OnRecetaSeleccionada {

    private lateinit var binding: ActivityHomeScreenBinding
    private val viewModel: RecetaViewModel by viewModels()
    private val enrutador = HomeScreenEnrutador(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializarObserverViewModel()
        viewModel.consultarRecetas()
    }

    override fun recetaSeleccionada(receta: Receta) {
        enrutador.irADetalleReceta(receta)
    }

    private fun inicializarObserverViewModel() {
        viewModel.listaRecetas.observe(this) { listaRecetas ->
            val adapter = listaRecetas
                ?.let { ListaRecetaAdapter(this@HomeScreenActivity, it.recetas) }
            binding.contenedorRecetas.adapter = adapter
            inicializarEscuchaFiltro(adapter)
        }
    }

    private fun inicializarEscuchaFiltro(adapter: ListaRecetaAdapter?) {
        binding.filtro.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(textoFiltro: String?): Boolean {
                adapter?.filter?.filter(textoFiltro)
                return false
            }

        })
    }
}
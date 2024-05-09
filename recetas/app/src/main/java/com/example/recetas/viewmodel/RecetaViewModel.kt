package com.example.recetas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recetas.modelos.ListaReceta
import com.example.recetas.network.RecetaService
import com.example.recetas.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecetaViewModel : ViewModel() {

    private val recetas = MutableLiveData<ListaReceta>()

    val listaRecetas: LiveData<ListaReceta>
        get() = recetas

    private var service = RetrofitClient.retrofit.create(RecetaService::class.java)

    fun init(recetaService: RecetaService) {
        this.service = recetaService
    }

    fun consultarRecetas() {
        CoroutineScope(Dispatchers.IO).launch {
            val listaRecetasResponse = service.consultarRecetas()
            recetas.postValue(listaRecetasResponse)
        }
    }
}
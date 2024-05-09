package com.example.recetas.network

import com.example.recetas.modelos.ListaReceta
import retrofit2.http.GET

interface RecetaService {

    @GET("obtenerrecetas")
    suspend fun consultarRecetas(): ListaReceta
}
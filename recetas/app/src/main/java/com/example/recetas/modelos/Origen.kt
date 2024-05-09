package com.example.recetas.modelos

import java.io.Serializable

data class Origen(
    val nombrePais: String,
    val banderaPais: String,
    val latitud: String,
    val longitud: String
) : Serializable
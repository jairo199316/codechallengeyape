package com.example.recetas.modelos

import java.io.Serializable

data class Receta(
    val nombre: String,
    val ingredientes: String,
    val preparacion: String,
    val imagen: String,
    val origen: Origen
) : Serializable
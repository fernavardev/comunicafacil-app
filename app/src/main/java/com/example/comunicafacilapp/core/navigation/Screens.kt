package com.example.comunicafacilapp.core.navigation

import kotlinx.serialization.Serializable

// definicion de destinos serializables usados para navegacion entre vistas
@Serializable
object Login

@Serializable
object Registro

@Serializable
object Recuperar

@Serializable
data class Principal(
    val correo: String
)
package com.example.comunicafacilapp

data class Usuario(
    val correo: String,
    val nombre: String,
    val apellido: String,
    var contrasena: String,
    val zonaResidencia: String,
    val gradoDiscapacidad: String,
    val aceptaDatosAnonimos: Boolean
)
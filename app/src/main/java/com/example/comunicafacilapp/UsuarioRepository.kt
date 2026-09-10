package com.example.comunicafacilapp
import androidx.compose.runtime.mutableStateListOf

// centraliza coleccion de usuarios y operaciones asociadas a su gestion
class UsuarioRepository {

    companion object {

        val usuarios = mutableStateListOf(
            Usuario(
                correo = "fernando@correo.cl",
                nombre = "Fernando",
                apellido = "Navarro",
                contrasena = "1234",
                zonaResidencia = "Santiago, Chile",
                gradoDiscapacidad = "Medio",
                aceptaDatosAnonimos = true
            ),
            Usuario(
                correo = "ana@correo.cl",
                nombre = "Ana",
                apellido = "Torres",
                contrasena = "1234",
                zonaResidencia = "Otra region de Chile",
                gradoDiscapacidad = "Bajo",
                aceptaDatosAnonimos = true
            ),
            Usuario(
                correo = "gloria@correo.cl",
                nombre = "Gloria",
                apellido = "Helena",
                contrasena = "1234",
                zonaResidencia = "Santiago, Chile",
                gradoDiscapacidad = "Alto",
                aceptaDatosAnonimos = false
            ),
            Usuario(
                correo = "marcela@correo.cl",
                nombre = "Marcela",
                apellido = "Campos",
                contrasena = "1234",
                zonaResidencia = "Otro pais (Extranjero)",
                gradoDiscapacidad = "Medio",
                aceptaDatosAnonimos = true
            ),
            Usuario(
                correo = "pedro@correo.cl",
                nombre = "Pedro",
                apellido = "Andres",
                contrasena = "1234",
                zonaResidencia = "Otra region de Chile",
                gradoDiscapacidad = "Bajo",
                aceptaDatosAnonimos = false
            )
        )

        fun autenticar(correo: String, contrasena: String): Boolean {
            return usuarios.any {
                it.correo == correo && it.contrasena == contrasena
            }
        }

        // verificacion si el correo ingresado ya existe dentro de la coleccion de la app
        fun existeCorreo(correo: String): Boolean {
            return usuarios.any {
                it.correo == correo
            }
        }

        fun registrar(usuario: Usuario) {
            usuarios.add(usuario)
        }

        fun buscarUsuario(correo: String): Usuario? {
            return usuarios.find {
                it.correo == correo
            }
        }

        fun actualizarContrasena(usuario: Usuario, nuevaContrasena: String) {
            usuario.contrasena = nuevaContrasena
        }
    }
}
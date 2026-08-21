package com.example.comunicafacilapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comunicafacilapp.LoginScreen
import com.example.comunicafacilapp.RegistroScreen
import com.example.comunicafacilapp.RecuperarScreen
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.comunicafacilapp.Usuario
import androidx.navigation.toRoute
import com.example.comunicafacilapp.PrincipalScreen

@Composable
fun NavigationWrapper(modifier: Modifier) {

    // se crea el controlador encargado de gestion de navegacion entre las vistas
    val navController = rememberNavController()

    // inicializacion local de 5 usuarios precargados dentro de la app
    val usuarios = remember {
        mutableStateListOf(
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
    }

    // se define vistas disponibles para navegacion, establece login como vista de inicio
    NavHost(
        navController = navController,
        startDestination = Login
    ) {

        composable<Login> {
            LoginScreen(
                onRegistroClick = {
                    navController.navigate(Registro)
                },
                onRecuperarClick = {
                    navController.navigate(Recuperar)
                },
                onLoginClick = { correo, contrasena ->
                    val usuarioEncontrado = usuarios.find {
                        it.correo == correo && it.contrasena == contrasena
                    }

                    if (usuarioEncontrado != null) {
                        navController.navigate(
                            Principal(correo = usuarioEncontrado.correo)
                        ) {
                            // el usuario al iniciar sesion, se elimina login de backstack para evitar volver desde la vista principal
                            popUpTo(Login) {
                                inclusive = true
                            }
                        }
                        true
                    } else {
                        false
                    }
                },
                modifier = modifier
            )
        }

        composable<Registro> {
            RegistroScreen(
                onRegistrarClick = { usuario ->
                    usuarios.add(usuario)
                    navController.popBackStack()
                },
                onVolverClick = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable<Recuperar> {
            RecuperarScreen(
                onCambiarContrasenaClick = { correo, nuevaContrasena, confirmarContrasena ->

                    val usuarioEncontrado = usuarios.find {
                        it.correo == correo
                    }

                    if (
                        usuarioEncontrado != null &&
                        nuevaContrasena.isNotEmpty() &&
                        nuevaContrasena == confirmarContrasena
                    ) {
                        usuarioEncontrado.contrasena = nuevaContrasena
                        navController.popBackStack()
                        true
                    } else {
                        false
                    }
                },
                onVolverClick = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable<Principal> { backStackEntry ->
            val principal = backStackEntry.toRoute<Principal>()

            val usuario = usuarios.find {
                it.correo == principal.correo
            }

            if (usuario != null) {
                PrincipalScreen(
                    usuario = usuario,
                    onCerrarSesionClick = {
                        navController.navigate(Login) {
                            popUpTo<Principal> {
                                inclusive = true
                            }
                        }
                    },
                    modifier = modifier
                )
            }
        }
    }
}
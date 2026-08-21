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

    val usuarios = remember {
        mutableStateListOf<Usuario>()
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
                        )
                    }
                },
                modifier = modifier
            )
        }

        composable<Registro> {
            RegistroScreen(
                onRegistrarClick = { usuario ->
                    usuarios.add(usuario)
                    navController.navigate(Login)
                },
                onVolverClick = {
                    navController.navigate(Login)
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
                        navController.navigate(Login)
                    }
                },
                onVolverClick = {
                    navController.navigate(Login)
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
                    modifier = modifier
                )
            }
        }
    }
}
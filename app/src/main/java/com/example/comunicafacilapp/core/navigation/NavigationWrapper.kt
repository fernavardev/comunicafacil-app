package com.example.comunicafacilapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comunicafacilapp.LoginScreen
import com.example.comunicafacilapp.RegistroScreen
import com.example.comunicafacilapp.RecuperarScreen
import androidx.navigation.toRoute
import com.example.comunicafacilapp.PrincipalScreen
import com.example.comunicafacilapp.UsuarioRepository

@Composable
fun NavigationWrapper(modifier: Modifier) {

    // se crea el controlador encargado de gestion de navegacion entre las vistas
    val navController = rememberNavController()

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
                onLoginClick = { correo ->
                    navController.navigate(
                        Principal(correo = correo)
                    ) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
                modifier = modifier
            )
        }

        composable<Registro> {
            RegistroScreen(
                onRegistrarClick = { usuario ->
                    UsuarioRepository.registrar(usuario)
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
                onCambiarContrasenaClick = {
                    navController.popBackStack()
                },
                onVolverClick = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable<Principal> { backStackEntry ->
            val principal = backStackEntry.toRoute<Principal>()

            val usuario = UsuarioRepository.buscarUsuario(principal.correo)

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
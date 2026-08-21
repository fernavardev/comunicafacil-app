package com.example.comunicafacilapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comunicafacilapp.LoginScreen
import com.example.comunicafacilapp.RegistroScreen
import com.example.comunicafacilapp.RecuperarScreen

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
                modifier = modifier
            )
        }

        composable<Registro> {
            RegistroScreen(
                onRegistrarClick = {
                },
                onVolverClick = {
                    navController.navigate(Login)
                },
                modifier = modifier
            )
        }

        composable<Recuperar> {
            RecuperarScreen(
                onCambiarContrasenaClick = {
                },
                onVolverClick = {
                    navController.navigate(Login)
                },
                modifier = modifier
            )
        }
    }
}
package com.example.comunicafacilapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Boolean,
    onRegistroClick: () -> Unit,
    onRecuperarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // se almacenan temporalmente los datos ingresados en campo del usuario (correo) y contraseña
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    // controlador de estado de error para alertar visualmente a los usuarios de la app (credenciales incorrectas)
    var errorLogin by remember { mutableStateOf(false) }

    // se organiza de manera vertical los elementos que forman la vista del inicio
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = buildAnnotatedString {
                append("ComunicaFácil👂")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("App")
                }
            },
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ingresa tus datos para continuar",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            isError = errorLogin,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = errorLogin,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorLogin) {
            Text(
                text = "Correo o contraseña incorrectos",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val loginCorrecto = onLoginClick(correo, contrasena)
                errorLogin = !loginCorrecto
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesion")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // posibilita navegar hacia vistas de recuperacion de la contraseña y el registro
        TextButton(
            onClick = onRecuperarClick
        ) {
            Text("¿Olvidaste tu contraseña?")
        }

        TextButton(
            onClick = onRegistroClick
        ) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}

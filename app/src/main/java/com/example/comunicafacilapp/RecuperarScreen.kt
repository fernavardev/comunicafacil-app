package com.example.comunicafacilapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp

@Composable
fun RecuperarScreen(
    onCambiarContrasenaClick: (String, String, String) -> Boolean,
    onVolverClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // guardar de manera temporal correo y nuevas contraseñas ingresadas por el usuario en esta vista
    var correo by remember { mutableStateOf("") }
    var nuevaContrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }

    // controlador para los estados de error, para informar visualmente de problemas a los usuarios (correo o contraseña)
    var errorCorreo by remember { mutableStateOf(false) }
    var errorContrasena by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Recuperar contraseña",
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Recupera el acceso a tu cuenta",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            isError = errorCorreo,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorCorreo) {
            Text(
                text = "Correo no encontrado",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nuevaContrasena,
            onValueChange = { nuevaContrasena = it },
            label = { Text("Nueva contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = errorContrasena,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmarContrasena,
            onValueChange = { confirmarContrasena = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = errorContrasena,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorContrasena) {
            Text(
                text = "Las contraseñas no coinciden",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ejecutar accion para cambiar la contraseña del usuario asociado con los nuevos datos ingresados
        Button(
            onClick = {
                if (
                    nuevaContrasena.isNotEmpty() &&
                    nuevaContrasena == confirmarContrasena
                ) {
                    val cambioCorrecto = onCambiarContrasenaClick(
                        correo,
                        nuevaContrasena,
                        confirmarContrasena
                    )

                    errorCorreo = !cambioCorrecto
                    errorContrasena = false
                } else {
                    errorContrasena = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cambiar contraseña")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = onVolverClick
        ) {
            Text("Volver al inicio de sesion")
        }
    }
}
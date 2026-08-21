package com.example.comunicafacilapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrincipalScreen(
    usuario: Usuario,
    modifier: Modifier = Modifier
) {
    // control sobre la visibilidad de los datos del usuario que inicia sesion en la app
    var mostrarDatos by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Bienvenido, ${usuario.nombre}",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { mostrarDatos = !mostrarDatos },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (mostrarDatos) {
                    "Ocultar mis datos"
                } else {
                    "Ver mis datos"
                }
            )
        }

        if (mostrarDatos) {

            Spacer(modifier = Modifier.height(24.dp))

            FilaDato("Nombre", usuario.nombre)
            FilaDato("Apellido", usuario.apellido)
            FilaDato("Correo", usuario.correo)
            FilaDato("Zona de residencia", usuario.zonaResidencia)
            FilaDato("Grado discapacidad", usuario.gradoDiscapacidad)
            FilaDato(
                "Datos anonimos",
                if (usuario.aceptaDatosAnonimos) "Si" else "No"
            )
        }
    }
}

// crea filas de una tabla general con los datos del usuario para su visualizacion respectiva
@Composable
fun FilaDato(
    etiqueta: String,
    valor: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = etiqueta,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = valor,
            modifier = Modifier.weight(1f)
        )
    }

    HorizontalDivider()
}
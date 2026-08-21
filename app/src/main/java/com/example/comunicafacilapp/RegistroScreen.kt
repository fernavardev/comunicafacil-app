package com.example.comunicafacilapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    onRegistrarClick: (Usuario) -> Unit,
    onVolverClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // almacenamiento de datos ingresados por el usuario y tambien opciones seleccionadas en la etapa de registro
    var correo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    var menuExpandido by remember { mutableStateOf(false) }
    var zonaResidencia by remember { mutableStateOf("") }

    var errorRegistro by remember { mutableStateOf(false) }

    val opcionesResidencia = listOf(
        "Santiago, Chile",
        "Otra region de Chile",
        "Otro pais (Extranjero)"
    )

    var gradoDiscapacidad by remember { mutableStateOf("") }

    val opcionesGrado = listOf(
        "Alto",
        "Medio",
        "Bajo"
    )

    var aceptaDatosAnonimos by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Registro",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Crea tu cuenta para continuar",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            isError = errorRegistro && correo.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            isError = errorRegistro && nombre.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            isError = errorRegistro && apellido.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = errorRegistro && contrasena.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorRegistro) {
            Text(
                text = "Completa todos los campos requeridos",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Zona de residencia",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // permitir que el usuario pueda seleccionar una zona de residencia entre las opciones proporcionadas
        ExposedDropdownMenuBox(
            expanded = menuExpandido,
            onExpandedChange = { menuExpandido = !menuExpandido }
        ) {
            OutlinedTextField(
                value = zonaResidencia,
                onValueChange = { },
                readOnly = true,
                label = { Text("Selecciona una opcion") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = menuExpandido
                    )
                },
                modifier = Modifier
                    .menuAnchor(
                        ExposedDropdownMenuAnchorType.PrimaryNotEditable
                    )
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = menuExpandido,
                onDismissRequest = { menuExpandido = false }
            ) {
                opcionesResidencia.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            zonaResidencia = opcion
                            menuExpandido = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Grado de discapacidad auditiva",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // permitir que el usuario pueda seleccionar su grado de discapacidad auditiva por medio de radiobuttons
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        ) {
            items(opcionesGrado) { opcion ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = opcion,
                        fontWeight = FontWeight.Bold
                    )

                    RadioButton(
                        selected = gradoDiscapacidad == opcion,
                        onClick = {
                            gradoDiscapacidad = opcion
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // permitir que el usuario pueda elegir voluntariamente compartir sus datos de manera anonima para mejora de aplicacion
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = aceptaDatosAnonimos,
                onCheckedChange = { aceptaDatosAnonimos = it }
            )

            Text(
                text = "Acepto el compartir mis datos de forma anonima para contribuir a futuras mejoras de esta aplicacion."
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (
                    correo.isNotBlank() &&
                    nombre.isNotBlank() &&
                    apellido.isNotBlank() &&
                    contrasena.isNotBlank()
                ) {
                    onRegistrarClick(
                        Usuario(
                            correo = correo,
                            nombre = nombre,
                            apellido = apellido,
                            contrasena = contrasena,
                            zonaResidencia = zonaResidencia,
                            gradoDiscapacidad = gradoDiscapacidad,
                            aceptaDatosAnonimos = aceptaDatosAnonimos
                        )
                    )
                } else {
                    errorRegistro = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = onVolverClick
        ) {
            Text("Volver al inicio de sesion")
        }
    }
}

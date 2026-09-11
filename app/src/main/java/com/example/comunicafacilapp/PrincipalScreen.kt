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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExposedDropdownMenuAnchorType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    usuario: Usuario,
    onCerrarSesionClick: () -> Unit,
    modifier: Modifier = Modifier
){
    // control sobre la visibilidad de los datos del usuario que inicia sesion en la app
    var mostrarDatos by remember { mutableStateOf(false) }

    // control del usuario para la categoria seleccionada y obtiene desde su repository los consejos correspondientes
    var categoriaSeleccionada by remember {
        mutableStateOf("Comunicación")
    }

    val consejosFiltrados =
        ConsejoRepository.obtenerConsejosPorCategoria(categoriaSeleccionada)

    val categorias = listOf(
        "Comunicación",
        "Emergencias",
        "Accesibilidad"
    )

    var menuCategoriasExpandido by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        Spacer(modifier = Modifier.height(32.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Consejos útiles 💡",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Selecciona una categoría para consultar consejos que podrian ayudarte en situaciones cotidianas"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // seleccion de una categoria por medio de un menu desplegable de material design
        ExposedDropdownMenuBox(
            expanded = menuCategoriasExpandido,
            onExpandedChange = {
                menuCategoriasExpandido = !menuCategoriasExpandido
            }
        ) {
            OutlinedTextField(
                value = categoriaSeleccionada,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoría") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = menuCategoriasExpandido
                    )
                },
                modifier = Modifier
                    .menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable
                    )
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = menuCategoriasExpandido,
                onDismissRequest = {
                    menuCategoriasExpandido = false
                }
            ) {
                categorias.forEach { categoria ->
                    DropdownMenuItem(
                        text = { Text(categoria) },
                        onClick = {
                            categoriaSeleccionada = categoria
                            menuCategoriasExpandido = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // presentacion de consejos filtrados por medio de tarjetas al usuario amigables visualmente para facilitar su lectura
        consejosFiltrados.forEach { consejo ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "${consejo.emoji} ${consejo.titulo}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = consejo.descripcion,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onCerrarSesionClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesion")
        }

        Spacer(modifier = Modifier.height(16.dp))
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
package com.universidad.ev_final_compose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.universidad.ev_final_compose.data.Medicion
import com.universidad.ev_final_compose.ui.theme.ListaMedicionesViewModel
import java.time.LocalDate

@Composable
fun FormScreen(vm: ListaMedicionesViewModel, onDone: () -> Unit) {
    var valorText by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("luz") } // default selected
    val fechaDefault = LocalDate.now()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Registro medidor")

        OutlinedTextField(
            value = valorText,
            onValueChange = { valorText = it.filter { ch -> ch.isDigit() } }, // sólo números
            label = { Text("Valor de la medición") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fechaDefault.toString(),
            onValueChange = { /* read-only for now */ },
            label = { Text("Fecha (por defecto)") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Text(text = "Medidor de:")

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            RadioButtonWithLabel("agua", tipo == "agua") { tipo = "agua" }
            RadioButtonWithLabel("luz", tipo == "luz") { tipo = "luz" }
            RadioButtonWithLabel("gas", tipo == "gas") { tipo = "gas" }
        }

        Button(onClick = {
            if (valorText.isNotBlank()) {
                val valorLong = valorText.toLong()
                val nueva = Medicion(
                    valor = valorLong,
                    tipoServicio = tipo,
                    fecha = fechaDefault
                )
                vm.insertarMedicion(nueva)
                onDone()
            }
        }) {
            Text("Agregar medición")
        }
    }
}

@Composable
fun RadioButtonWithLabel(label: String, selected: Boolean, onSelect: () -> Unit) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = onSelect)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = label)
    }
}

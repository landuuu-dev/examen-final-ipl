package com.universidad.ev_final_compose.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.GasMeter
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.universidad.ev_final_compose.data.Medicion
import com.universidad.ev_final_compose.ui.theme.ListaMedicionesViewModel
import java.time.format.DateTimeFormatter
@Composable
fun ListScreen(vm: ListaMedicionesViewModel, onNavigateToForm: () -> Unit) {
    val lista = vm.medicion
    val fmt = DateTimeFormatter.ISO_LOCAL_DATE
    LaunchedEffect(Unit) { vm.obtenerMedicion() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(lista) { m ->
                MedicionCard(m = m)
            }
        }
        Button(
            onClick = { vm.borrarTodo() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9534F)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text("Eliminar todos", color = Color.White)
        }
    }
}

@Composable
fun MedicionCard(m: Medicion) {
    val fmt = DateTimeFormatter.ISO_LOCAL_DATE
    val icon = when (m.tipoServicio.lowercase()) {
        "agua" -> Icons.Default.WaterDrop
        "luz" -> Icons.Default.Bolt
        "gas" -> Icons.Default.GasMeter
        else -> Icons.Default.Bolt
    }
    val color = when (m.tipoServicio.lowercase()) {
        "agua" -> Color(0xFF000000)     // Negro
        "luz"  -> Color(0xFF000000)
        "gas"  -> Color(0xFF000000)
        else   -> Color(0xFF424242)     // Gris oscuro elegante
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = m.tipoServicio.uppercase(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Fecha: ${m.fecha.format(fmt)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
            Text(
                text = m.valor.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = color
            )
        }
    }
}

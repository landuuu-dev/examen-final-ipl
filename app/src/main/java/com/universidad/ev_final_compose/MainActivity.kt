package com.universidad.ev_final_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.universidad.ev_final_compose.ui.theme.ListaMedicionesViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import com.universidad.ev_final_compose.data.Medicion
import java.time.LocalDate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMedicionesUI()
        }
    }
}

@Composable

fun AppMedicionesUI(
    vmListaMediciones: ListaMedicionesViewModel = viewModel(factory = ListaMedicionesViewModel.Factory)
){

    LaunchedEffect(Unit) {
        vmListaMediciones.obtenerMedicion()
    }
    LazyColumn {
        items(vmListaMediciones.medicion) { medicion ->
            Text(text = medicion.valor.toString())
        }

        item{
            Button(onClick = {
                vmListaMediciones.insertarMedicion(
                    Medicion(
                        valor = 302L,
                        tipoServicio = "luz",
                        fecha = LocalDate.now()
                    )
                )


            }){
                Text("Agregar medición")

            }
        }
    }

}
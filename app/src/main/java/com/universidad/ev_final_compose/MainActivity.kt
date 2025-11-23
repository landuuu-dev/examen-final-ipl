package com.universidad.ev_final_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.universidad.ev_final_compose.ui.screens.ListScreen
import com.universidad.ev_final_compose.ui.screens.FormScreen
import com.universidad.ev_final_compose.ui.theme.ListaMedicionesViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val vm: ListaMedicionesViewModel = viewModel(factory = ListaMedicionesViewModel.Factory)

            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { navController.navigate("form") }) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar")
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "list",
                    modifier = Modifier.padding(innerPadding)   // 👈 aquí se usa
                ) {
                    composable("list") {
                        ListScreen(vm = vm, onNavigateToForm = { navController.navigate("form") })
                    }
                    composable("form") {
                        FormScreen(vm = vm, onDone = { navController.popBackStack() })
                    }
                }
            }
        }}}

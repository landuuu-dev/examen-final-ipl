package com.universidad.ev_final_compose.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.universidad.ev_final_compose.Aplicacion
import com.universidad.ev_final_compose.data.Medicion
import com.universidad.ev_final_compose.data.MedicionesDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaMedicionesViewModel(private val medicionesDAO: MedicionesDAO) : ViewModel() {

    var medicion by mutableStateOf<List<Medicion>>(emptyList())
        private set

    fun insertarMedicion(m: Medicion){
        viewModelScope.launch(Dispatchers.IO){
            medicionesDAO.insertar(m)
            medicion = medicionesDAO.obtenerTodos()
        }
    }

    fun obtenerMedicion(){
        viewModelScope.launch(Dispatchers.IO){
            medicion = medicionesDAO.obtenerTodos()
        }
    }

    fun borrarTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            medicionesDAO.eliminarTodo()
            medicion = emptyList()
        }
    }


    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as Aplicacion)
                ListaMedicionesViewModel(app.medicionDao)
            }
        }
    }
}

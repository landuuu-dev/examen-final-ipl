package com.universidad.ev_final_compose

import android.app.Application
import androidx.room.Room
import com.universidad.ev_final_compose.data.BaseDatos

class Aplicacion : Application() {
    val db by lazy {Room.databaseBuilder(this, BaseDatos::class.java, "Mediciones.db").build()}
    val medicionDao by lazy {db.medicionesDAO()}
}
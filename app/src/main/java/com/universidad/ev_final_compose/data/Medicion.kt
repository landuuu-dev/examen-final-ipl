package com.universidad.ev_final_compose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Medicion(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val valor: Long,
    val tipoServicio: String,
    val fecha: LocalDate
)



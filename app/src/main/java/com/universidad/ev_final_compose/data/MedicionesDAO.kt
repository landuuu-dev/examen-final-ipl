package com.universidad.ev_final_compose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MedicionesDAO {
    @Query("SELECT * FROM Medicion ORDER BY fecha DESC")
    suspend fun obtenerTodos(): List<Medicion>

    @Query("DELETE FROM Medicion")
    suspend fun eliminarTodo()

    @Insert
    suspend fun insertar(medicion: Medicion)
    @Update
    suspend fun modificar(medicion: Medicion)
    @Delete
    suspend fun eliminar(medicion: Medicion)
}
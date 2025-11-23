package com.universidad.ev_final_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Medicion::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class BaseDatos : RoomDatabase(){
    abstract fun medicionesDAO(): MedicionesDAO
}
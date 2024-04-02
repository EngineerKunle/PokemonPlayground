package com.ekotech.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ekotech.core_database.doa.PokemonDao
import com.ekotech.core_database.entity.Pokemon

@Database(
    entities = [Pokemon::class],
    version = 1
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
package com.ekotech.core_database.doa

import androidx.room.Dao
import androidx.room.Query
import com.ekotech.core_database.entity.Pokemon

@Dao
interface PokemonDao : BaseDao<Pokemon> {

    @Query("SELECT * FROM Pokemon")
    suspend fun getAllPokemon(): List<Pokemon>
}

package com.ekotech.poketech.data.api

import com.ekotech.poketech.data.model.PokemonAllDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("limit")
        limit: Int = 150
    ): Response<PokemonAllDTO>
}


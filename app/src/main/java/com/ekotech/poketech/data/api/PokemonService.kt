package com.ekotech.poketech.data.api

import com.ekotech.poketech.data.model.PokemonAllDTO
import com.ekotech.poketech.data.model.PokemonDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("limit")
        limit: Int = 150
    ): Response<PokemonAllDTO>

    //https://pokeapi.co/api/v2/pokemon/2/
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id:Int): Response<PokemonDetailDTO>
}


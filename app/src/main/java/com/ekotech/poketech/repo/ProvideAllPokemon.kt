package com.ekotech.poketech.repo

import com.ekotech.poketech.data.model.PokemonAllDTO
import com.ekotech.poketech.util.Resource

interface ProvideAllPokemon {
    suspend fun getAllPokemon(): Resource<PokemonAllDTO>
}
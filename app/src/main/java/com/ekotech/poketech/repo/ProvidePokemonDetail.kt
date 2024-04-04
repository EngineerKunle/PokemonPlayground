package com.ekotech.poketech.repo

import com.ekotech.poketech.data.model.PokemonDetailDTO
import com.ekotech.poketech.util.Resource

interface ProvidePokemonDetail {
    suspend fun getPokemon(id: Int): Resource<PokemonDetailDTO>
}
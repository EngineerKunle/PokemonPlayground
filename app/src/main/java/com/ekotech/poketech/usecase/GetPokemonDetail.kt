package com.ekotech.poketech.usecase

import com.ekotech.poketech.repo.PokemonDetailRepo
import javax.inject.Inject

class GetPokemonDetail @Inject constructor(private val repo: PokemonDetailRepo) {
    suspend operator fun invoke(id: Int) = repo.getPokemon(id)
}
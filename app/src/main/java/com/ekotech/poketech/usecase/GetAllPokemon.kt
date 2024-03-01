package com.ekotech.poketech.usecase

import com.ekotech.poketech.repo.AllPokemonRepo
import javax.inject.Inject

class GetAllPokemon @Inject constructor(private val repo: AllPokemonRepo){
    suspend operator fun invoke() = repo.getAllPokemon()
}
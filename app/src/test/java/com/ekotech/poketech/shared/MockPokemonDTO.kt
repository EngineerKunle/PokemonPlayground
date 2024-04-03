package com.ekotech.poketech.shared

import com.ekotech.poketech.data.model.PokemonDTO

object MockPokemonDTO {
    fun mockPokemon() = listOf(
        PokemonDTO("Bulbasaur", "www"),
        PokemonDTO("Charmander", "www"),
        PokemonDTO("Pikachu", "www"),
        PokemonDTO("Squirtle", "www"),
    )
}
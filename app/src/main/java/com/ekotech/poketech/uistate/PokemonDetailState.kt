package com.ekotech.poketech.uistate

data class PokemonDetailState(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val baseExperience: Int,
    val imageURL: String,
)
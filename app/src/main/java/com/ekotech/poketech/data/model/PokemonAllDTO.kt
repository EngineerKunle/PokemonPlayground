package com.ekotech.poketech.data.model

import com.google.gson.annotations.SerializedName

data class PokemonAllDTO(
    @SerializedName("results")
    val results: List<PokemonDTO>
)

data class PokemonDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
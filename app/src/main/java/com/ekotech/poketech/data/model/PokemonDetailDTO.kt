package com.ekotech.poketech.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailDTO(
    @SerializedName("name")
    val name:String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("base_experience")
    val baseExperience: Int,
)
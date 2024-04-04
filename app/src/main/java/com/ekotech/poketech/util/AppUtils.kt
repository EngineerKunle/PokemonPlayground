package com.ekotech.poketech.util

object AppUtils {
    const val BASE_URL = "https://pokeapi.co/api/v2/"

    const val POKEMON_ID = "POKEMON_ID"

    fun provideOfficialArtWork(id: Int): String {
        return "https://raw.githubusercontent.com" +
                "/PokeAPI/sprites/master/sprites/pokemon" +
                "/other/official-artwork/$id.png"
    }
}
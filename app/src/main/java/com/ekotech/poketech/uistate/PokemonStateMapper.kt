package com.ekotech.poketech.uistate

import com.ekotech.poketech.data.model.PokemonDTO
import com.ekotech.poketech.util.AppUtils
import com.ekotech.poketech.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonStateMapper @Inject constructor() {

    fun transform(items: List<PokemonDTO>?): Resource<List<PokemonState>> {
        var states = mutableListOf<PokemonState>()
        items?.let { data ->
            data.forEachIndexed { index, dto ->
                val pokemonId = index.inc()
                states.add(
                    PokemonState(
                        id = pokemonId,
                        name = dto.name.replaceFirstChar { it.uppercaseChar() },
                        imageURL = AppUtils.provideOfficialArtWork(pokemonId)
                    )
                )
            }

        } ?: run {
            states = arrayListOf()
        }

        return Resource.Success(states)
    }
}
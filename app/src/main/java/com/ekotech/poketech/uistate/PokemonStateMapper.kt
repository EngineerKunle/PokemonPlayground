package com.ekotech.poketech.uistate

import androidx.compose.ui.text.capitalize
import com.ekotech.poketech.data.model.PokemonAllDTO
import com.ekotech.poketech.util.AppUtils
import com.ekotech.poketech.util.Resource
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonStateMapper @Inject constructor() {

    fun transform(items: Resource<PokemonAllDTO>?): Resource<List<PokemonState>> {
        var states = mutableListOf<PokemonState>()
        items?.let { dto ->
            dto.data?.results?.forEachIndexed { index, dto ->
                val pokemonId = index.inc()
                println("Pokemon id is $pokemonId at id $dto")
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
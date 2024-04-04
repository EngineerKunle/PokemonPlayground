package com.ekotech.poketech.uistate

import com.ekotech.poketech.data.model.PokemonDetailDTO
import com.ekotech.poketech.util.AppUtils
import com.ekotech.poketech.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDetailStateMapper @Inject constructor() {

    fun transform(id: Int, item: PokemonDetailDTO): Resource<PokemonDetailState> = Resource.Success(
        PokemonDetailState(
            id = id,
            name = item.name,
            height = item.height,
            weight = item.weight,
            baseExperience = item.baseExperience,
            imageURL = AppUtils.provideOfficialArtWork(id)
        )
    )
}
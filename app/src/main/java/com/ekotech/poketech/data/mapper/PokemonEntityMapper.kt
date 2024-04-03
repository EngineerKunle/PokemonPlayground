package com.ekotech.poketech.data.mapper

import com.ekotech.core_database.entity.Pokemon
import com.ekotech.poketech.data.model.PokemonDTO

object PokemonEntityMapper : EntityMapper<List<PokemonDTO>, List<Pokemon>> {

    override fun asEntity(domain: List<PokemonDTO>): List<Pokemon> = domain.mapIndexed { index, dto ->
        val pokemonId = index.inc()
        Pokemon(
            id = pokemonId,
            name = dto.name,
            url = dto.url
        )
    }

    override fun asDomain(entity: List<Pokemon>): List<PokemonDTO> = entity.map {
        PokemonDTO(
            name = it.name,
            url = it.url
        )
    }
}

fun List<PokemonDTO>.asEntity(): List<Pokemon> = PokemonEntityMapper.asEntity(this)

fun List<Pokemon>.asDomain(): List<PokemonDTO> = PokemonEntityMapper.asDomain(this)
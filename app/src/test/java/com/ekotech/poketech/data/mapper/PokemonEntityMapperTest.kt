package com.ekotech.poketech.data.mapper

import com.ekotech.core_database.entity.Pokemon
import com.ekotech.poketech.data.model.PokemonDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonEntityMapperTest {

    @Test
    fun `given a list of pokemon dto when converted then provide a list of entity`() {
        val sut = pokemonDTOs()

        val result = sut.asEntity()

        assertEquals(result.size, 2)

        assertEquals(result[0].id, 1)
        assertEquals(result[1].id, 2)

        assertEquals(result[0].name, sut[0].name)
        assertEquals(result[1].name, sut[1].name)
    }

    @Test
    fun `given a list of pokemon when converted to domain objects then provide a list of domains`() {
        val sut = pokemon()

        val result = sut.asDomain()

        assertEquals(result.size, 2)

        assertEquals(result[0].name, sut[0].name)
        assertEquals(result[1].name, sut[1].name)
    }

    private fun pokemonDTOs() = listOf(
        PokemonDTO(
            name = "Pikachu",
            url = "pika.chu"
        ),
        PokemonDTO(
            name = "Raichu",
            url = "rai.chu",
        )
    )

    private fun pokemon() = listOf(
        Pokemon(
            id = 1,
            name = "Pikachu",
            url = "pika.chu",
        ),

        Pokemon(
            id = 2,
            name = "Raichu",
            url = "rai.chu",
        )
    )
}
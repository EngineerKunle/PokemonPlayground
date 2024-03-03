package com.ekotech.poketech.repo

import com.ekotech.poketech.data.api.PokemonService
import com.ekotech.poketech.data.model.PokemonAllDTO
import com.ekotech.poketech.data.model.PokemonDTO
import com.ekotech.poketech.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class AllPokemonRepoTest {

    private lateinit var allPokemon: AllPokemonRepo

    private val service: PokemonService = mockk(relaxed = true)

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        allPokemon = AllPokemonRepo(service)
    }

    @Test
    fun `given a successful call then data should contain all pokemon`() = runTest {

        coEvery {
            service.getAllPokemon()
        } returns mockData()

        val result = allPokemon.getAllPokemon()

        assertEquals(true, result is Resource.Success)
        assertEquals(4, result.data?.results?.size)
    }

    @Test
    fun `given an 401 error then result should be an error and no data`() = runTest {

        coEvery {
            service.getAllPokemon()
        } returns  Response.error(401, "Unauthorized".toResponseBody())

        val result = allPokemon.getAllPokemon()

        assertEquals(true, result is Resource.Success)
    }

    private fun mockData(): Response<PokemonAllDTO> {
        val mockResult = PokemonAllDTO(mockPokemon())
        return Response.success(mockResult)
    }

    private fun mockPokemon() = listOf(
        PokemonDTO("Bulbasaur", "www"),
        PokemonDTO("Charmander", "www"),
        PokemonDTO("Pikachu", "www"),
        PokemonDTO("Squirtle", "www"),
    )

}

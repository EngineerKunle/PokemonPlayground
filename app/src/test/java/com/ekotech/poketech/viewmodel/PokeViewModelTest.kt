package com.ekotech.poketech.viewmodel

import com.ekotech.poketech.data.model.PokemonAllDTO
import com.ekotech.poketech.shared.MockPokemonDTO.mockPokemon
import com.ekotech.poketech.uistate.PokemonState
import com.ekotech.poketech.uistate.PokemonStateMapper
import com.ekotech.poketech.usecase.GetAllPokemon
import com.ekotech.poketech.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.math.exp

@OptIn(ExperimentalCoroutinesApi::class)
class PokeViewModelTest {

    private lateinit var viewModel: PokeViewModel
    private val getAllPokemon: GetAllPokemon = mockk(relaxed = true)
    private val stateMapper: PokemonStateMapper = mockk(relaxed = true)
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `given a success call then viewmodel should provide all pokemon data`() = runTest {
        coEvery {
            getAllPokemon.invoke()
        } returns mockGetAllPokemon()

        coEvery {
            stateMapper.transform(any())
        } returns mockTransformList()

        viewModel = PokeViewModel(getAllPokemon, stateMapper)

        coVerify(exactly = 1) {
            stateMapper.transform(any())
        }

        assertTrue(viewModel.allPokeState.value is Resource.Success)
    }

    @Test
    fun `given a fail call then viewmodel should not provide all pokemon data`() = runTest {

        coEvery {
            getAllPokemon.invoke()
        } throws Exception("Something went wrong")

        viewModel = PokeViewModel(getAllPokemon, stateMapper)

        coVerify(exactly = 0) {
            stateMapper.transform(any())
        }

        assertTrue(viewModel.allPokeState.value is Resource.Error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val mockPokemonDTO: PokemonAllDTO = PokemonAllDTO(
        mockPokemon()
    )

    private fun mockGetAllPokemon(): Resource<PokemonAllDTO> = Resource.Success(mockPokemonDTO)

    private fun mockTransformList():Resource<List<PokemonState>> = Resource.Success(emptyList())

}
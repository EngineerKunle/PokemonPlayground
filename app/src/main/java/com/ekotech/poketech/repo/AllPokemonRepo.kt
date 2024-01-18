package com.ekotech.poketech.repo

import com.ekotech.poketech.data.api.PokemonService
import com.ekotech.poketech.data.model.PokemonAllDTO
import com.ekotech.poketech.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AllPokemonRepo @Inject constructor(
    private val service: PokemonService
) : ProvideAllPokemon {
    override suspend fun getAllPokemon(): Resource<PokemonAllDTO> = withContext(Dispatchers.IO) {
        responseToResource(service.getAllPokemon())
    }

    private fun responseToResource(response: Response<PokemonAllDTO>): Resource<PokemonAllDTO> {
        if (response.isSuccessful) {
            response.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(response.message())
    }
}
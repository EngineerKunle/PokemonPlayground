package com.ekotech.poketech.repo

import com.ekotech.poketech.data.api.PokemonService
import com.ekotech.poketech.data.model.PokemonDetailDTO
import com.ekotech.poketech.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonDetailRepo @Inject constructor(
    private val service: PokemonService
) : ProvidePokemonDetail {

    override suspend fun getPokemon(id: Int): Resource<PokemonDetailDTO> = withContext(Dispatchers.IO) {
        responseToResource(service.getPokemon(id))
    }

    private fun responseToResource(response: Response<PokemonDetailDTO>): Resource<PokemonDetailDTO> {
        if (response.isSuccessful) {
            response.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(response.message())
    }
}
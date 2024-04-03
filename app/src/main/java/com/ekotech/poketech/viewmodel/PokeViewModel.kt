package com.ekotech.poketech.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotech.core_database.doa.PokemonDao
import com.ekotech.poketech.data.mapper.asDomain
import com.ekotech.poketech.data.mapper.asEntity
import com.ekotech.poketech.uistate.PokemonState
import com.ekotech.poketech.uistate.PokemonStateMapper
import com.ekotech.poketech.usecase.GetAllPokemon
import com.ekotech.poketech.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val getAllPokemon: GetAllPokemon,
    private val stateMapper: PokemonStateMapper,
    private val pokemonDao: PokemonDao,
) : ViewModel() {

    private val _allPokeState: MutableState<Resource<List<PokemonState>>> =
        mutableStateOf(Resource.Loading())
    val allPokeState = _allPokeState

    init {
        getAllPokemonData()
    }

    private fun getAllPokemonData() {
        viewModelScope.launch {
            try {
                var pokemon = pokemonDao.getAllPokemon().asDomain()
                if (pokemon.isEmpty()) {
                    val data = getAllPokemon.invoke().data
                    data?.let {
                        pokemonDao.insertAll(it.results.asEntity())
                        pokemon = data.results
                        _allPokeState.value = stateMapper.transform(pokemon)
                    }
                } else {
                    _allPokeState.value = stateMapper.transform(pokemon)
                }

            } catch (e: Exception) {
                _allPokeState.value = Resource.Error(e.message.toString())
            }
        }
    }
}
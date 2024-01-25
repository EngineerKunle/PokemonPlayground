package com.ekotech.poketech.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotech.poketech.data.model.PokemonAllDTO
import com.ekotech.poketech.usecase.GetAllPokemon
import com.ekotech.poketech.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val getAllPokemon: GetAllPokemon
) : ViewModel() {

    private val _allPokemon: MutableState<Resource<PokemonAllDTO>> =
        mutableStateOf(Resource.Loading())

    val allPokemon = _allPokemon

    init {
        getAllPokemonData()
    }

    private fun getAllPokemonData() {
        viewModelScope.launch {
            try {
                val result = getAllPokemon.invoke()
                _allPokemon.value = result
            } catch (e: Exception) {
                _allPokemon.value = Resource.Error(e.message.toString())
            }
        }
    }
}
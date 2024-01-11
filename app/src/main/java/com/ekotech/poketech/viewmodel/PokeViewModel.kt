package com.ekotech.poketech.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotech.poketech.uistate.PokemonState
import com.ekotech.poketech.uistate.data.UIResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class PokeViewModel : ViewModel() {
    private val _uiState: MutableState<UIResult<PokemonState>> = mutableStateOf(UIResult.Loading)
    val state: State<UIResult<PokemonState>> = _uiState

    init {
        viewModelScope.launch {
            delay(2L)
            mockData()
        }
    }

    private fun mockData() {
        _uiState.value = UIResult.Success(PokemonState(1, "Bulbasaur"))
    }
}
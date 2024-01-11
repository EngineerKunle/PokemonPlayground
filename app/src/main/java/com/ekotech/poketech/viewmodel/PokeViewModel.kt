package com.ekotech.poketech.viewmodel

import androidx.lifecycle.ViewModel
import com.ekotech.poketech.uistate.PokemonState
import com.ekotech.poketech.uistate.data.UIResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class PokeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UIResult.Loading)
    val state: StateFlow<UIResult<PokemonState>> = _uiState

    init {

    }
}
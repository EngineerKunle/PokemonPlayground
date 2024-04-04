package com.ekotech.poketech.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotech.poketech.uistate.PokemonDetailState
import com.ekotech.poketech.uistate.PokemonDetailStateMapper
import com.ekotech.poketech.usecase.GetPokemonDetail
import com.ekotech.poketech.util.AppUtils.POKEMON_ID
import com.ekotech.poketech.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(
    private val getPokemonDetail: GetPokemonDetail,
    private val detailStateMapper: PokemonDetailStateMapper,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<Int>(POKEMON_ID))
    private val _detailPokeState: MutableState<Resource<PokemonDetailState>> =
        mutableStateOf(Resource.Loading())
    val detailPokeState = _detailPokeState

    init {
        getPokemonDetail()
    }

    private fun getPokemonDetail() {
        viewModelScope.launch {
            try {
                val result = getPokemonDetail.invoke(id)
                result.data?.let { dto ->
                    _detailPokeState.value = detailStateMapper.transform(id, dto)
                }
            } catch (e: Exception) {
                _detailPokeState.value = Resource.Error(e.message.toString())
            }
        }
    }
}
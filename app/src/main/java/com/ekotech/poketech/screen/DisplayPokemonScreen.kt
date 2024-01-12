package com.ekotech.poketech.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ekotech.poketech.uistate.PokemonState

@Composable
fun DisplayPokemonScreen(data: PokemonState) {
    Column {
        Text(text = data.message)
    }
}
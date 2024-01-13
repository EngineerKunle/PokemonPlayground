package com.ekotech.poketech.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ekotech.poketech.uistate.PokemonState

@Composable
fun DisplayPokemonScreen(data: PokemonState) {
    LazyColumn {
        items(data.message) {
            PokemonCard(name = it)
        }
    }
}

@Composable
private fun PokemonCard(name: String) {
    val cardModifier = Modifier
        .padding(8.dp)
        .fillMaxSize()

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = cardModifier
    ) {
        Text(text = name, modifier = Modifier.padding(16.dp))
    }
}
package com.ekotech.poketech.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ekotech.poketech.R
import com.ekotech.poketech.ui.PokemonProgressBar
import com.ekotech.poketech.uistate.PokemonState
import com.ekotech.poketech.util.Resource
import com.ekotech.poketech.viewmodel.PokeViewModel

@Composable
fun PokemonHome(
    viewModel: PokeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val state by remember { viewModel.allPokeState }

    when (state) {
        is Resource.Loading -> {
            PokemonProgressBar()
        }

        is Resource.Error -> {
            PokemonErrorScreenGeneric()
        }

        is Resource.Success -> {
            DisplayPokemonScreen(data = state.data, navController)
        }
    }
}

@Composable
private fun DisplayPokemonScreen(
    data: List<PokemonState>?,
    navController: NavHostController,
) {

    data?.let { allDto ->
        if (allDto.isNotEmpty()) {
            LazyColumn {
                items(allDto) { pokemonDto ->
                    PokemonCard(
                        pokemonDto.id,
                        pokemonDto.name,
                        pokemonDto.imageURL
                    ) {
                        navController.navigate("${PokemonScreen.PokemonDetail.route}/${pokemonDto.id}")
                    }
                }
            }
        }
    }
}

@Composable
private fun PokemonCard(
    id: Int,
    name: String,
    url: String,
    itemClick: (Int) -> Unit
) {
    val cardModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()

    val imageModifier = Modifier
        .size(150.dp)

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = cardModifier
            .clickable { itemClick(id) },
    ) {
        Row {
            AsyncImage(
                model = url,
                modifier = imageModifier,
                contentDescription = stringResource(id = R.string.pokemon_item_image),//replace with stringRes
                placeholder = painterResource(id = R.drawable.pokemon_default),
                error = painterResource(id = R.drawable.pokemon_default),
            )

            Box(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 16.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = id.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp, top = 8.dp)
                    )
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp, top = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "DisplayPokemon")
@Composable
fun DisplayPokemonPreview() {
    PokemonCard(1, "Hello", "fake", {})
}
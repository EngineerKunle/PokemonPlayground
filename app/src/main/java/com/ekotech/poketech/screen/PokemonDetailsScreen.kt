package com.ekotech.poketech.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ekotech.poketech.R
import com.ekotech.poketech.ui.PokemonProgressBar
import com.ekotech.poketech.uistate.PokemonDetailState
import com.ekotech.poketech.util.Resource
import com.ekotech.poketech.viewmodel.PokeDetailViewModel

@Composable
fun PokemonDetail(
    viewModel: PokeDetailViewModel = hiltViewModel(),
) {
    val state by remember { viewModel.detailPokeState }

    when (state) {
        is Resource.Loading -> {
            PokemonProgressBar()
        }

        is Resource.Error -> {
            PokemonErrorScreenGeneric()
        }

        is Resource.Success -> DetailsScreen(state.data)
    }
}

@Composable
private fun DetailsScreen(data: PokemonDetailState?) {

    Column(
        Modifier.fillMaxWidth(),
    ) {
        data?.let { it ->
            AsyncImage(
                model = it.imageURL,
                modifier = Modifier.size(500.dp),
                contentDescription = stringResource(id = R.string.pokemon_detail_image),
                placeholder = painterResource(id = R.drawable.pokemon_default),
                error = painterResource(id = R.drawable.pokemon_default),
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = it.name.replaceFirstChar { char -> char.uppercaseChar() },
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(0.dp,20.dp,0.dp, 0.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Height: ${it.height} cm")
                Text(text = "Weight: ${it.weight} kg")
                Text(text = "Exp: " + it.baseExperience.toString())

            }
        }
    }

}
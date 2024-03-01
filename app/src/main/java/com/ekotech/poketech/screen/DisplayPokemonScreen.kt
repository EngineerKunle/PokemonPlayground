package com.ekotech.poketech.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ekotech.poketech.R
import com.ekotech.poketech.uistate.PokemonState
import org.w3c.dom.Text

@Composable
fun DisplayPokemonScreen(data: List<PokemonState>?) {
    data?.let { allDto ->
        if (allDto.isNotEmpty()) {
            LazyColumn {
                items(allDto) { pokemonDto ->
                    PokemonCard(
                        pokemonDto.id, pokemonDto.name,
                        pokemonDto.imageURL
                    )
                }
            }
        }
    }
}

@Composable
private fun PokemonCard(id: Int, name: String, url: String) {
    val cardModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()

    val imageModifier = Modifier
        .size(150.dp)

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = cardModifier,
    ) {
        Row {
            AsyncImage(
                model = url,
                modifier = imageModifier,
                contentDescription = "Pokemon Image",//replace with stringRes
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
                    Text(text = id.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp, top = 8.dp))
                    Text(text = name,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp, top = 8.dp))
                }
            }
        }
    }
}

@Preview(name = "DisplayPokemon")
@Composable
fun DisplayPokemonPreview() {
    PokemonCard(1, "Hello", "fake")
}
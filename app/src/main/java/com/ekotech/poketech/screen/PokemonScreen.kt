package com.ekotech.poketech.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ekotech.poketech.R
import com.ekotech.poketech.ui.PokemonProgressBar
import com.ekotech.poketech.ui.theme.appRed
import com.ekotech.poketech.ui.theme.appWhite
import com.ekotech.poketech.util.Resource
import com.ekotech.poketech.viewmodel.PokeViewModel
import org.w3c.dom.Text

enum class PokemonScreen(@StringRes val title: Int) {
    Pokemon(title = R.string.pokemon)
}

@Composable
fun PokemonDexApp(
    viewModel: PokeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val state by remember { viewModel.allPokeState }

    val currentScreen = PokemonScreen.valueOf(
        backStackEntry?.destination?.route ?: PokemonScreen.Pokemon.name
    )

    Scaffold(
        topBar = {
            PokemonDexAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = PokemonScreen.Pokemon.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = PokemonScreen.Pokemon.name) {
                when (state) {
                    is Resource.Loading -> {
                        PokemonProgressBar()
                    }

                    is Resource.Error -> TODO()
                    is Resource.Success -> {
                        DisplayPokemonScreen(data = state.data)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDexAppBar(
    currentScreen: PokemonScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title), fontWeight = FontWeight.Bold) },
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = appRed, titleContentColor = appWhite),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


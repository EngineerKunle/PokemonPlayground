package com.ekotech.poketech.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ekotech.poketech.R
import com.ekotech.poketech.ui.theme.appRed
import com.ekotech.poketech.ui.theme.appWhite
import com.ekotech.poketech.util.AppUtils.POKEMON_ID

enum class PokemonScreen(val route: String) {
    PokemonHome(route = "PokemonHome"),
    PokemonDetail(route = "PokemonDetail"),
}

@Composable
fun PokemonDexApp(
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        topBar = {
            PokemonDexAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = PokemonScreen.PokemonHome.route,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = PokemonScreen.PokemonHome.route) {
                PokemonHome(navController = navController)
            }
            composable(
                route = "${PokemonScreen.PokemonDetail.route}/{POKEMON_ID}",
                arguments = listOf(navArgument(POKEMON_ID) { type = NavType.IntType })
            ) {
                PokemonDetail()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDexAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.toolbar_title), fontWeight = FontWeight.Bold) },
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = appRed, titleContentColor = appWhite),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


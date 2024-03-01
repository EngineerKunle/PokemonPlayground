package com.ekotech.poketech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ekotech.poketech.screen.PokemonDexApp
import com.ekotech.poketech.ui.theme.PokemonDexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = getColor(R.color.app_red)
            PokemonDexTheme {
                PokemonDexApp()
            }
        }
    }
}
package com.ekotech.poketech.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.ekotech.poketech.R

@Composable
fun PokemonErrorScreenGeneric() {
    val shouldDisplay = remember {
        mutableStateOf(true)
    }

    if (shouldDisplay.value) {
        AlertDialog(
            title = {
                R.string.generic_error_title
            },
            text = { R.string.app_name },
            onDismissRequest = {},
            confirmButton = {},
            dismissButton = {
                TextButton(
                    onClick = {
                        shouldDisplay.value =false
                    }
                ) {
                    Text(stringResource(id = R.string.generic_error_dismiss))
                }
            },
        )
    }

}
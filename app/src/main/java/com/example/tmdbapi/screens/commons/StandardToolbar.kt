package com.example.tmdbapi.screens.commons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tmdbapi.ui.theme.primaryDark
import com.example.tmdbapi.ui.theme.primaryGray
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun StandardToolbar(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    showBackArrow: Boolean = false,
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = title,
        navigationIcon = if (showBackArrow) {
            {
                IconButton(onClick = {
                    navigator.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = primaryGray
                    )
                }
            }
        } else null,
        actions = navActions,
        backgroundColor = primaryDark,
        elevation = 5.dp
    )
}
package com.example.tmdbapi.model

import com.example.tmdbapi.R
import com.example.tmdbapi.screens.destinations.FavoritesScreenDestination
import com.example.tmdbapi.screens.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination

sealed class BottomNavItem(var title: String, var icon: Int,
                           var destination: Destination
) {
    data object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = Destination(HomeScreenDestination.route)
    )
    data object Favorites: BottomNavItem(
        title = "Favorites",
        icon = R.drawable.ic_star,
        destination = Destination(FavoritesScreenDestination.route)
    )
}
package com.example.tmdbapi.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbapi.data.remote.responses.MovieDetailsResponse
import com.example.tmdbapi.screens.commons.StandardToolbar
import com.example.tmdbapi.screens.details.commons.MovieImageBanner
import com.example.tmdbapi.screens.details.commons.MovieInfo
import com.example.tmdbapi.screens.favorites.FavoritesViewModel
import com.example.tmdbapi.util.Constants
import com.example.tmdbapi.util.Resource
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MovieDetailsScreen(
    movieId: Int,
    navigator: DestinationsNavigator,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
) {
    val scrollState = rememberLazyListState()

    val details = produceState<Resource<MovieDetailsResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieDetails(movieId)
    }.value


    Box {
        if (details is Resource.Success) {
            Column {
                StandardToolbar(
                    navigator = navigator,
                    title = {
                        Text(
                            text = "Details",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Box {
                    MovieInfo(
                        scrollState = scrollState,
                        overview = details.data?.overview.toString(),
                        releaseDate = details.data?.releaseDate.toString(),
                        navigator = navigator
                    )
                    MovieImageBanner(
                        scrollState = scrollState,
                        posterUrl = "${Constants.IMAGE_BASE_URL}/${details.data?.posterPath}",
                        movieName = details.data?.title.toString(),
                        movieId = details.data?.id!!,
                        movieType = "movie",
                        releaseDate = details.data.releaseDate.toString(),
                        rating = details.data.voteAverage?.toFloat()!!,
                        navigator = navigator,
                        viewModel = favoritesViewModel
                    )
                }
            }
        } else {
            Column {
                StandardToolbar(
                    navigator = navigator,
                    title = {
                        Text(
                            text = "Details",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    showBackArrow = true
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }
        }
    }
}
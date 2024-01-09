package com.example.tmdbapi.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.tmdbapi.R
import com.example.tmdbapi.model.Movie
import com.example.tmdbapi.screens.commons.FilterItem
import com.example.tmdbapi.screens.commons.MovieItem
import com.example.tmdbapi.screens.commons.StandardToolbar
import com.example.tmdbapi.screens.destinations.MovieDetailsScreenDestination
import com.example.tmdbapi.screens.destinations.SearchScreenDestination
import com.example.tmdbapi.ui.theme.primaryGray
import com.example.tmdbapi.util.Constants.IMAGE_BASE_URL
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val filterItemsList = listOf("Popular", "Now Playing", "Top Rated", "Upcoming")

    val nowPlayingMovies = viewModel.nowPlayingMovies.value.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.value.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.value.collectAsLazyPagingItems()
    val upcomingMovies = viewModel.upcomingMovies.value.collectAsLazyPagingItems()

    val selectedItemIndex by viewModel.selectedItemIndex

    val listState = rememberLazyStaggeredGridState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        StandardToolbar(
            navigator = navigator,
            title = {
                Column {
                    Image(
                        painterResource(
                            id = R.drawable.muviz
                        ),
                        contentDescription = "App logo",
                        modifier = Modifier
                            .size(width = 90.dp, height = 90.dp)
                            .padding(8.dp)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
            navActions = {
                IconButton(onClick = {
                    navigator.navigate(SearchScreenDestination)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = primaryGray
                    )
                }
            }
        )

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(filterItemsList.size) { index ->
                       FilterItem(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            selected = (filterItemsList[index] == filterItemsList[selectedItemIndex]),
                            onClick = {
                                viewModel.setSelectedItemIndex(index)
                                coroutineScope.launch {
                                    listState.scrollToItem(0)
                                }
                            },
                            content = {
                                Text(
                                    text = filterItemsList[index],
                                    color = if (filterItemsList[index] == filterItemsList[selectedItemIndex])
                                    Color.White else Color.Black,
                                )
                            },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            state = listState
        ) {

            val movies: LazyPagingItems<Movie>

            when (filterItemsList[selectedItemIndex]) {
                "Popular" -> {
                    movies = popularMovies
                }

                "Now Playing" -> {
                    movies = nowPlayingMovies
                }

                "Top Rated" -> {
                    movies = topRatedMovies
                }

                else -> {
                    movies = upcomingMovies
                }
            }

            items(movies.itemCount) { index ->
                MovieItem(
                    modifier = Modifier
                        .height(200.dp)
                        .width(100.dp)
                        .clickable {
                            navigator.navigate(
                                MovieDetailsScreenDestination(
                                    movies[index]?.id ?: -1
                                )
                            )
                        },
                    imageUrl = "$IMAGE_BASE_URL/${movies[index]?.posterPath}"
                )
            }
        }
    }
}
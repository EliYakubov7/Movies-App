package com.example.tmdbapi.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbapi.data.repository.GenresRepository
import com.example.tmdbapi.model.Movie
import com.example.tmdbapi.data.repository.MoviesRepository
import com.example.tmdbapi.model.Genre
import com.example.tmdbapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val genresRepository: GenresRepository
) : ViewModel() {

    private val _popularMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val popularMovies: State<Flow<PagingData<Movie>>> = _popularMovies

    private val _nowPlayingMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val nowPlayingMovies: State<Flow<PagingData<Movie>>> = _nowPlayingMovies

    private val _topRatedMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val topRatedMovies: State<Flow<PagingData<Movie>>> = _topRatedMovies

    private val _upcomingMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val upcomingMovies: State<Flow<PagingData<Movie>>> = _upcomingMovies

    private val _moviesGenres = mutableStateOf<List<Genre>>(emptyList())
    val moviesGenres: State<List<Genre>> = _moviesGenres

    private val _selectedItemIndex = mutableIntStateOf(0)
    val selectedItemIndex: State<Int> get() = _selectedItemIndex

    init {
        getPopularMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
        getUpcomingMovies()
        getMoviesGenres()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = moviesRepository.getPopularMovies().cachedIn(viewModelScope)
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            _nowPlayingMovies.value = moviesRepository.getNowPlayingMovies().cachedIn(viewModelScope)
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            _topRatedMovies.value = moviesRepository.getTopRatedMovies().cachedIn(viewModelScope)
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            _upcomingMovies.value = moviesRepository.getUpcomingMovies().cachedIn(viewModelScope)
        }
    }

    private fun getMoviesGenres() {
        viewModelScope.launch {
            when (val result = genresRepository.getMoviesGenres()) {
                is Resource.Success -> {
                    result.data?.genres?.let { genres ->
                        _moviesGenres.value = genres
                    }
                }
                is Resource.Error -> {
//                    loadingError.value = result.message.toString()
                }
                else -> {}
            }
        }
    }

    fun setSelectedItemIndex(index: Int) {
        _selectedItemIndex.intValue = index
    }

//            _popularMovies.value = if (genreId != null) {
//                moviesRepository.getPopularMovies().map { pagingData ->
//                    pagingData.filter {
//                        it.genreIds.contains(genreId)
//                    }
//                }.cachedIn(viewModelScope)
//            } else {
//                moviesRepository.getPopularMovies().cachedIn(viewModelScope)
//            }

}